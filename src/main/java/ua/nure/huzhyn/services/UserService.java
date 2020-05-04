package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.User;

import java.util.List;

/**
 * Provides service which contains methods for provide information about {@link User}
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface UserService {

    /**
     * Checks if the user is in the database
     *
     * @param login    {@link User} login
     * @param password {@link User} password
     * @return is there a user in the database
     */
    User isValidUser(String login, String password);

    /**
     * registers a new {@link User}
     *
     * @param user {@link User}
     * @return registered {@link User}
     */
    String registr(User user);

    /**
     * Return list of users information
     *
     * @param userRole {@link User} role of user
     * @return list of {@link User}
     */
    List<User> getUserInfo(String userRole);

    /**
     * Block user by id
     *
     * @param idUser      {@link User} user id
     * @param blockStatus user block status
     */
    void updateBlocked(String idUser, boolean blockStatus);

    /**
     * Read {@link User} by user id
     *
     * @param userId {@link User} user id
     * @return {@link User}
     */
    User read(String userId);
}
