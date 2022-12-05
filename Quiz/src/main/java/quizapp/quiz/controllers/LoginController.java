package quizapp.quiz.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.quiz.daos.Quiz;
import quizapp.quiz.services.FeedbackService;
import quizapp.quiz.services.QuizService;
import quizapp.quiz.services.UserService;
import quizapp.quiz.daos.User;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final UserService userService;
    private final QuizService quizService;
    private final FeedbackService feedbackService;

    @Autowired
    public LoginController(UserService userService, QuizService quizService, FeedbackService feedbackService) {
        this.userService = userService;
        this.quizService = quizService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/login")
    public String getLogin() {return "login";}

    @PostMapping("/login")
    public String postLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        Optional<User> someUser = userService.validateLogin(username, password);
        if (someUser.isPresent() && someUser.get().isAvailable()) {
            HttpSession old = request.getSession(false);
            if (old != null) old.invalidate();
            HttpSession newSession = request.getSession(true);
            User user = someUser.get();
            if (user.isAdmin()) {
                newSession.setAttribute("User", user);
                newSession.setAttribute("AllUsers", userService.getAll());
                newSession.setAttribute("AllFeedback", feedbackService.getAll());
                newSession.setAttribute("AllSubmission", quizService.getAllSubmission());
                newSession.setAttribute("AllQuestion", quizService.getAllQuestion());
                return "admin";
            } else {
                newSession.setAttribute("User", user);
                List<Quiz> qzl = quizService.getAllQuizzes();
                newSession.setAttribute("QuizList", qzl);
                return "home";
            }
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession old = request.getSession(false);
        if (old != null) old.invalidate();
        return "login";
    }

}
