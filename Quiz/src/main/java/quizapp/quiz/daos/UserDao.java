package quizapp.quiz.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import quizapp.quiz.HibernateUtil;
import quizapp.quiz.daos.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    private static final List<User> users;

    static {

        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Query q = hibernateSession.createQuery("from User");
        users = q.list();
        hibernateSession.close();
//        users = new ArrayList<>();
//        users.add(new User(1, "user1", "pass1", false));
//        users.add(new User(2, "user2", "pass2", false));
//        users.add(new User(3, "3", "3", false));
//        users.add(new User(4, "4", "4", true));
    }
    public User getUserById(int id) {
        return users.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(new User(-1, "N/A", "N/A", false, false));
    }
    public User getUserByName(String name) {
        return users.stream()
                .filter(a -> a.getUsername().equals(name))
                .findFirst()
                .orElse(new User(-1, "N/A", "N/A", false, false));
    }
    public void createNewUser(User user) {
        users.add(user);
    }
    public List<User> getAll() {
        return users;
    }
    public Optional<User> validateLogin(String name, String password) {
        return users.stream()
                .filter(a -> a.getUsername().equals(name) && a.getPassword().equals(password))
                .findAny();
    }

    public void register(String username, String password) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        User user = new User(users.size()+1, username, password, false, true);
        Transaction tx =null;
        try {
            tx = hibernateSession.beginTransaction();
            hibernateSession.save(user);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        hibernateSession.close();
        users.add(user);
    }

    public void disableEnableUser(int id) {
        for (User user: users) {
            if (user.getId() == id) user.setAvailable(!user.isAvailable());
        }
    }
}
