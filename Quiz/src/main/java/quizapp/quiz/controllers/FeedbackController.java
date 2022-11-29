package quizapp.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.quiz.services.FeedbackService;

import javax.servlet.http.HttpSession;

@Controller
public class FeedbackController {
    FeedbackService feedbackService;
    @Autowired
    public FeedbackController(FeedbackService feedbackService) {this.feedbackService = feedbackService;}

    @GetMapping("/feedback")
    public String getFeedback() {return "feedback";}

    @PostMapping("/feedback")
    public String submitFeedback(@RequestParam(name = "rating") String choice,
                                 @RequestParam(name = "feedback") String content,
                                 HttpSession session) {
        int rating = Integer.parseInt(choice);
        feedbackService.addFeedback(content, rating);
        session.setAttribute("msg", "Submitted! rating: " + rating + ", comment: " + content);
        return "feedback";
    }
}
