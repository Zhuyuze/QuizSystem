package quizapp.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.quiz.daos.User;
import quizapp.quiz.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RegisterController {
    private final UserService userService;
    @Autowired
    public RegisterController(UserService userService) {this.userService = userService;}

    @GetMapping("/register")
    public String getRegister() {return "register";}

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        if (userService.exist(username, password)) {
            HttpSession old = request.getSession(false);
            if (old != null) old.invalidate();
            return "register";
        } else {
            userService.register(username, password);
            return "login";
        }
    }
}
