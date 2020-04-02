package ua.nure.huzhyn.db.dao.utils;

import ua.nure.huzhyn.model.entity.User;
import ua.nure.huzhyn.model.entity.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class DbUtils {

    public static User extract(ResultSet rs) throws SQLException {

        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName((rs.getString("last_name")));
        user.setPhone(rs.getString("phone"));
        user.setBirthDate(rs.getObject("birth_date", LocalDate.class));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        user.setBlocked(rs.getBoolean("blocked"));
        return user;
    }


    private DbUtils() {
    }
}
