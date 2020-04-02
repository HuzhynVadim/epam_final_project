package ua.nure.huzhyn.db.dao;


import ua.nure.huzhyn.model.entity.User;

import java.util.List;


public interface UserRepository extends CRUD<User, String> {

    User getByEmail(String email);

    boolean isUserExist(String email);

    List<User> getUserInfo(String userRole);

    void updateBlocked(String idUser, boolean blockStatus);


}
