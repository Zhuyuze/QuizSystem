package quizapp.quiz.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import quizapp.quiz.HibernateUtil;
import quizapp.quiz.daos.Option;
import quizapp.quiz.daos.Question;
import quizapp.quiz.daos.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class QuizDao {
    public static List<Submission> submissions;
    public static List<SubmissionDetail> submissionDetails;
    public static List<SubmissionDetail> currentSubmissionDetails;
    public static int currentSubmission;
    public static List<Question> questions;
    public static List<Option> options;

    public static List<Question> currentQuestions;
    public static List<Quiz> quizzes;
    static {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        questions = hibernateSession.createQuery("from Question ").list();
        options = hibernateSession.createQuery("from Option ").list();
        quizzes = hibernateSession.createQuery("from  Quiz ").list();
        submissions = hibernateSession.createQuery("from Submission ").list();
        submissionDetails = hibernateSession.createQuery("from SubmissionDetail ").list();
        currentSubmissionDetails = new ArrayList<>();
        currentSubmission = submissions.size()+1;
        hibernateSession.close();
//        questions = new ArrayList<>();
//        options = new ArrayList<>();
//        quizzes = new ArrayList<>();
//        submissions = new ArrayList<>();
//        submissionDetails = new ArrayList<>();
//        currentSubmission = 0;
//
//        questions.add(new Question(1, 1, "1+2=?"));
//        options.add(new Option(1, 1, "3", true));
//        options.add(new Option(2, 1, "3.0", true));
//        options.add(new Option(3, 1, "2", false));
//        options.add(new Option(4, 1, "2+1", true));
//
//        questions.add(new Question(2, 1, "2+2=?"));
//        options.add(new Option(5, 2, "4", true));
//        options.add(new Option(6, 2, "3.0", false));
//        options.add(new Option(7, 2, "2", false));
//        options.add(new Option(8, 2, "2*2", true));
//
//        questions.add(new Question(3, 1, "3+2=?"));
//        options.add(new Option(9, 3, "5", true));
//        options.add(new Option(10, 3, "3.0", false));
//        options.add(new Option(11, 3, "2", false));
//        options.add(new Option(12, 3, "6-1", true));
//
//        quizzes.add(new Quiz(1, 1, "Math"));
//
//
//
//
//        questions.add(new Question(4, 2, "ABCD_FG"));
//        options.add(new Option(13, 4, "e", true));
//        options.add(new Option(14, 4, "a", false));
//        options.add(new Option(15, 4, "E", true));
//
//        questions.add(new Question(5, 2, "HI_KLMN"));
//        options.add(new Option(16, 5, "j", true));
//        options.add(new Option(17, 5, "a", false));
//        options.add(new Option(18, 5, "y", false));
//        options.add(new Option(19, 5, "b", false));
//
//        questions.add(new Question(6, 2, "OPQ_S_"));
//        options.add(new Option(20, 6, "RT", true));
//        options.add(new Option(21, 6, "Rt", true));
//        options.add(new Option(22, 6, "fg", false));
//        options.add(new Option(23, 6, "rT", true));
//        options.add(new Option(24, 6, "rt", true));
//
//        quizzes.add(new Quiz(2, 2, "Alphabet"));
//
//
//
//
//        questions.add(new Question(7, 3, "cat"));
//        options.add(new Option(25, 7, "puss", true));
//        options.add(new Option(26, 7, "cuss", false));
//        options.add(new Option(27, 7, "mog", true));
//        options.add(new Option(28, 7, "dog", false));
//
//        questions.add(new Question(8, 3, "eagle"));
//        options.add(new Option(29, 8, "hawk", true));
//        options.add(new Option(30, 8, "owl", false));
//        options.add(new Option(31, 8, "chicken", false));
//        options.add(new Option(32, 8, "falcon", true));
//
//        questions.add(new Question(9, 3, "Holland"));
//        options.add(new Option(33, 9, "England", false));
//        options.add(new Option(34, 9, "Dutch", true));
//        options.add(new Option(35, 9, "Netherlands", true));
//        options.add(new Option(36, 9, "India", false));
//
//        questions.add(new Question(10, 3, "liquid"));
//        options.add(new Option(37, 10, "fluid", true));
//        options.add(new Option(38, 10, "earth", false));
//        options.add(new Option(39, 10, "juice", true));
//        options.add(new Option(40, 10, "stone", false));
//
//        quizzes.add(new Quiz(3, 3, "Synonyms"));



    }
    public Optional<Quiz> quizById(int id) {
        return quizzes.stream().filter(a -> a.getId() == id).findAny();
    }

    public void questionsByQuizIdShuffled(int QZID) {
        currentSubmissionDetails.clear();
        Collections.shuffle(questions);
        currentQuestions = questions.stream().filter(a -> a.getQuizId() == QZID).limit(10).collect(Collectors.toList());
    }

    public List<Question> getCurrentQuestions() {
        return currentQuestions;
    }

    public List<Option> optionsByQuestionId(int QID) {
        return options.stream().filter(a ->a.getQuestionId() == QID).collect(Collectors.toList());
    }

    public List<Quiz> allQuizzes() {
        return quizzes;
    }

    public int createNewSubmission(int userId, int quizId, String startDate, String endDate) {
        int correct = 0;
        for (SubmissionDetail s : currentSubmissionDetails) {
            if (s.getQuestionId() != -1) {
                if (options.stream().filter(a -> a.getId() == s.getUserChoice()).findAny().get().isCorrect()) {
                    correct++;
                } else if (correct > 1) correct -= 2;
                else if (correct == 1) correct--;
            }
        }
        //to do: store submission details

        int answer = 0;
        Quiz qz = quizzes.stream().filter(a -> a.getId() == quizId).findAny().get();
        List<Question> ql = questions.stream().filter(a -> a.getQuizId() == qz.getId()).limit(10).collect(Collectors.toList());
        for (Question q : ql) {
            answer += options.stream().filter(a -> a.getQuestionId() == q.getId() && a.isCorrect()).count();
        }

        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = hibernateSession.beginTransaction();
        for (SubmissionDetail sd : currentSubmissionDetails) {
            hibernateSession.save(sd);
        }

        int score = correct * 100 / answer;
        submissionDetails.addAll(currentSubmissionDetails);
        //currentSubmissionDetails.clear();

        Submission submission = new Submission(currentSubmission, userId, quizId, score, startDate, endDate);
        hibernateSession.save(submission);
        tx.commit();
        submissions.add(submission);

        currentSubmission = submissions.size()+1;
        return score;
    }
    public List<SubmissionDetail> getCurrentSubmissionDetails() {
        return currentSubmissionDetails;
    }

    public void submitAQuestion(int userId, int questionId, int optionId) {
        int submissionDetailId = submissionDetails.size() + currentSubmissionDetails.size() + 1;
        int submissionId = currentSubmission;
        SubmissionDetail sd = new SubmissionDetail(submissionDetailId, userId, submissionId, questionId, optionId);

        currentSubmissionDetails.add(sd);
    }
    public void cancelAQuestion(int questionId) {
        for (SubmissionDetail sd : currentSubmissionDetails) {
            if (sd.getQuestionId() == questionId) sd.setQuestionId(-1);
        }
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }
    public List<Question> getQuestions() {
        return questions;
    }
}
