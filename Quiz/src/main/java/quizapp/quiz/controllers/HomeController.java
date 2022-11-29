package quizapp.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quizapp.quiz.daos.Option;
import quizapp.quiz.daos.Question;
import quizapp.quiz.daos.Quiz;
import quizapp.quiz.daos.QuizDao;
import quizapp.quiz.services.QuizService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    QuizService quizService;

    @Autowired
    public HomeController(QuizService quizService) {this.quizService = quizService;}

    @GetMapping("/home")
    public String getHome() {
        System.out.println("From HomeController: ");
        return "home";
    }


    @PostMapping("/home")
    public String submitQuestion(@RequestParam(name = "quizSelection") String choice,
                                 HttpSession session)  {

        int userChoice = Integer.parseInt(choice);
        if (quizService.exist(userChoice)) {
            session.setAttribute("CurrentQuiz", quizService.getQuiz(userChoice).get());
            session.setAttribute("msg", "You are taking Quiz " + userChoice);
            session.setAttribute("num", 0);
            session.setAttribute("QuestionDescription", new Question(-1,-1,"We collect your submission."));
            session.setAttribute("QuestionOption", new ArrayList<Option>(Arrays.asList(new Option(-1, -1, "Yes, I agree.", false))));
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy E HH:mm:ss");
            session.setAttribute("StartTime", myDateObj.format(myFormatObj));
            quizService.getShuffledQuestionsByQuizId(userChoice);
            return "quiz";
        } else {
            session.setAttribute("msg", "No quizzes in selected category");
            return "home";
        }

    }
}
