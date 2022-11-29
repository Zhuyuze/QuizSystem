package quizapp.quiz.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import quizapp.quiz.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FeedbackDao {
    private static final List<Feedback> feedbacks;
    static {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        feedbacks = hibernateSession.createQuery("from Feedback ").list();
        hibernateSession.close();
        //feedbacks = new ArrayList<>();
    }
    public List<Feedback> getAll() {
        return feedbacks;
    }

    public int getSize() {return feedbacks.size();}
    public void addNewFeedback(Feedback fb) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = hibernateSession.beginTransaction();
        hibernateSession.save(fb);
        tx.commit();
        hibernateSession.close();
        feedbacks.add(fb);
    }
}
