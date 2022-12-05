package quizapp.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.quiz.daos.User;
import quizapp.quiz.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    UserService userService;
    @Autowired
    public AdminController(UserService userService) {this.userService = userService;}

    @GetMapping("/admin")
    public String getAdmin(HttpSession session) {
        User user = (User) session.getAttribute("User");
        if (!user.isAdmin()) return "login";
        return "admin";
    }

    @PostMapping("/admin")
    public String operate(@RequestParam(name = "disable_user_btn", defaultValue = "-1", required = false) String s, HttpSession session) {

        int UID = Integer.parseInt(s);
        session.setAttribute("msg", "Updated!" + s);
        userService.disableEnableUser(UID);
        session.setAttribute("AllUsers", userService.getAll());
        return "admin";
    }
}
