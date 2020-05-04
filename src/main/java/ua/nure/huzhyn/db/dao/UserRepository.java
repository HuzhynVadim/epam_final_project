package ua.nure.huzhyn.db.dao;


import ua.nure.huzhyn.model.entity.User;

import java.util.List;


/**
 * DAO for the {@link User} objects.
 * Besides the basic CRUD methods it provides methods receive the user by his e-mail, check if the registered user is
 * valid by the given e-mail, receives full information about the user, updates the status of the user’s blocking
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface UserRepository extends CRUD<User, String> {

    /**
     * Return {@link User} by email
     *
     * @param email {@link User} email
     * @return {@link User} by email
     */
    User getByEmail(String email);

    /**
     * Check that {@link User} exist
     *
     * @param email {@link User} email
     * @return boolean result of the user's existence check
     */
    boolean isUserExist(String email);

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


}
