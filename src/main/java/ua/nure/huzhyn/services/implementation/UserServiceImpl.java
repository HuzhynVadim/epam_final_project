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
        if (user.getEmail() != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        } else {
            UnauthorizedException e = new UnauthorizedException("You are not registered");
            LOGGER.error(e);
            throw e;
        }
        UnauthorizedException e = new UnauthorizedException("Incorrect password or you are blocked");
        LOGGER.error(e);
        throw e;
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

    @Override
    public User read(String userId) {
        return transactionManager.execute(() -> userRepository.read(userId).get());
    }
}
