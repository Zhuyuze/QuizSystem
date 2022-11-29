package quizapp.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.quiz.daos.*;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizDao quizDao;

    @Autowired
    public QuizService(QuizDao quizDao) {this.quizDao = quizDao;}

    public Optional<Quiz> getQuiz(int id) {
        return quizDao.quizById(id);
    }

    public boolean exist(int id) {
        return quizDao.quizById(id).isPresent();
    }

    public void submitQuestion(int UID, int QID, int OID) {
        quizDao.submitAQuestion(UID, QID, OID);
    }
    public void cancelQuestion(int QID) {quizDao.cancelAQuestion(QID);}

    public int getScore(int UID, int QZID, String startDate, String endDate) {
        return quizDao.createNewSubmission(UID, QZID, startDate, endDate);
    }

    public List<Question> getCurrentQuestions() {
        return quizDao.getCurrentQuestions();
    }

    public void getShuffledQuestionsByQuizId(int QZID) {
        quizDao.questionsByQuizIdShuffled(QZID);
    }

    public List<SubmissionDetail> getCurrentSubmissionDetails() {
        return quizDao.getCurrentSubmissionDetails();
    }

    public List<Option> getOptionsByQuestionId(int QID) {
        return quizDao.optionsByQuestionId(QID);
    }

    public List<Quiz> getAllQuizzes() {
        return quizDao.allQuizzes();
    }
}
