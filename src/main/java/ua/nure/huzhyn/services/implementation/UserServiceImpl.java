package ua.nure.huzhyn.services.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.UserRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.exception.UnauthorizedException;
import ua.nure.huzhyn.exception.UserAlreadyExistException;
import ua.nure.huzhyn.model.entity.User;
import ua.nure.huzhyn.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private TransactionManager transactionManager;


    public UserServiceImpl(UserRepository userRepository, TransactionManager transactionManager) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public User isValidUser(String email, String password) {
        User user = transactionManager.execute(() -> userRepository.getByEmail(email));
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        LOGGER.error("You are not registered or you are blocked");
        throw new UnauthorizedException("You are not registered or you are blocked");
    }

    @Override
    public String registr(User user) {
        boolean exist = transactionManager.execute(() -> userRepository.isUserExist(user.getEmail()));
        if (!exist) {
            return transactionManager.execute(() -> userRepository.create(user));
        } else {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public void updateBlocked(String idUser, boolean blockStatus) {
        transactionManager.execute(() -> {
            userRepository.updateBlocked(idUser, blockStatus);
            return null;
        });
    }

    @Override
    public List<User> getUserInfo(String userRole) {
        return transactionManager.execute(() -> userRepository.getUserInfo(userRole));
    }
}
