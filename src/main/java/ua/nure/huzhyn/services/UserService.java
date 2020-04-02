package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.User;

import java.util.List;

public interface UserService {

    User isValidUser(String login, String password);

    String registr(User user);

    List<User> getUserInfo(String userRole);

    void updateBlocked(String idUser, boolean blockStatus);


}
