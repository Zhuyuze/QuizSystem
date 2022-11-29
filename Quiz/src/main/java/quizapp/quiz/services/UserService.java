package quizapp.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapp.quiz.daos.User;
import quizapp.quiz.daos.UserDao;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {this.userDao = userDao;}

    public Optional<User> validateLogin(String username, String password) {
        return userDao.validateLogin(username, password);
    }

    public void register(String username, String password) {
        userDao.register(username, password);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public boolean exist(String username, String password) {
        return userDao.validateLogin(username, password).isPresent();
    }
}
