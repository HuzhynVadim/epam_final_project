package ua.nure.huzhyn.db.dao.utils;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.User;
import ua.nure.huzhyn.model.entity.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class DbUtils {
    private static final Logger LOGGER = Logger.getLogger(DbUtils.class);

    private DbUtils() {
    }

    public static User extract(ResultSet rs) {
        User user = new User();
        try {
            user.setUserId(rs.getString("user_id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName((rs.getString("last_name")));
            user.setPhone(rs.getString("phone"));
            user.setBirthDate(rs.getObject("birth_date", LocalDate.class));
            user.setRole(UserRole.valueOf(rs.getString("role")));
            user.setBlocked(rs.getBoolean("blocked"));
        } catch (SQLException | IllegalArgumentException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract User.", e);
        }
        return user;
    }
}
