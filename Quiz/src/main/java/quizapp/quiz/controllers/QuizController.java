package quizapp.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import quizapp.quiz.daos.*;
import quizapp.quiz.services.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class QuizController {
    QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {this.quizService = quizService;}

    @ResponseBody
    @GetMapping("/test")
    public String test() {return "hello ";}

    @GetMapping("/quiz")
    public String getHome() {
        return  "quiz";
    }

    @PostMapping("/quiz")
    public String submitQuestion(@RequestParam(name = "math", required = false, defaultValue = "N") List<String> choices, @RequestParam(name = "btn") String button,
                                 HttpSession session)  {
        //session.setAttribute("choice", choice);
        int current = Integer.parseInt(session.getAttribute("num").toString());

        List<Option> previousOption = (List<Option>) session.getAttribute("QuestionOption");
        Question previousQuestion = (Question) session.getAttribute("QuestionDescription");
        User user = (User) session.getAttribute("User");

        int QID = previousQuestion.getId();
        int UID = user.getId();
        quizService.cancelQuestion(QID);
        for (String choice : choices) if (!choice.equals("N")){
            int OID = Integer.parseInt(choice);
            if (previousQuestion.getId() != -1) {
                quizService.submitQuestion(UID, QID, OID);
            }
        }



        Quiz quiz = (Quiz) session.getAttribute("CurrentQuiz");
        int QZID = quiz.getId();
        List<Question> questionList = quizService.getCurrentQuestions();




        if (button.equals("next") && current < questionList.size()) {

            session.setAttribute("num", current+1);


            session.setAttribute("msg", "Taking Quiz " + QZID + "Question " + (current + 1));



            session.setAttribute("QuestionDescription", questionList.get(current));
            int questionId = questionList.get(current).getId();
            List<Option> options = quizService.getOptionsByQuestionId(questionId);
            session.setAttribute("QuestionOption", options);
            return "quiz";
        } else if (button.equals("submit")){
            System.out.println("Submitted!");
            String startTime = session.getAttribute("StartTime").toString();


            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy E HH:mm:ss");
            String endTime = myDateObj.format(myFormatObj);

            List<SubmissionDetail> submissionDetailList = quizService.getCurrentSubmissionDetails();
            List<Option> optionList = new ArrayList<>();
            for (Question q : questionList) {
                optionList.addAll(quizService.getOptionsByQuestionId(q.getId()));
            }
            session.setAttribute("SubmissionDetailList", submissionDetailList);
            session.setAttribute("QuestionList", questionList);
            session.setAttribute("OptionList", optionList);
            int score = quizService.getScore(UID, QZID, startTime, endTime);
            session.setAttribute("EndTime", endTime);
            session.setAttribute("score", score);
            return "result";
        } else if (button.equals("previous")){
            session.setAttribute("num", current - 1);


            session.setAttribute("msg", "Taking Quiz " + QZID + "Question" + (current - 1));



            session.setAttribute("QuestionDescription", questionList.get(current-2));
            int questionId = questionList.get(current-2).getId();
            List<Option> options = quizService.getOptionsByQuestionId(questionId);
            session.setAttribute("QuestionOption", options);
            return "quiz";
        } else {
            int selection = Integer.parseInt(button);
            session.setAttribute("num", selection);


            session.setAttribute("msg", "Taking Quiz " + QZID + "Question" + selection);



            session.setAttribute("QuestionDescription", questionList.get(selection-1));
            int questionId = questionList.get(selection-1).getId();
            List<Option> options = quizService.getOptionsByQuestionId(questionId);
            session.setAttribute("QuestionOption", options);

            return "quiz";
        }

    }
}
