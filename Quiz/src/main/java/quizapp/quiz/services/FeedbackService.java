package quizapp.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.quiz.daos.Feedback;
import quizapp.quiz.daos.FeedbackDao;

import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackDao feedbackDao;

    @Autowired
    public FeedbackService(FeedbackDao feedbackDao) {this.feedbackDao = feedbackDao;}

    public void addFeedback(String comment, int rating) {
        feedbackDao.addNewFeedback(new Feedback(feedbackDao.getSize() + 1, comment, rating));
    }

    public List<Feedback> getAll() {
        return feedbackDao.getAll();
    }
}
