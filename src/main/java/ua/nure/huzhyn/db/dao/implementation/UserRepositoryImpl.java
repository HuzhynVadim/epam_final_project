package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.UserRepository;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.db.dao.utils.DbUtils;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM final_project.railway_system.user WHERE email = ? LIMIT 1";
    private static final String ADD_USER = "INSERT INTO final_project.railway_system.user (user_id, email, password, first_name, last_name, phone, birth_date, role, blocked) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM final_project.railway_system.user WHERE user_id = ?";
    private static final String DELETE_USER = "DELETE FROM final_project.railway_system.user WHERE user_id = ?";
    private static final String UPDATE_USER = "UPDATE final_project.railway_system.user SET email = ?, password = ?, first_name = ?, last_name = ?, phone = ?, birth_date = ?, role = ?, blocked = ? WHERE user_id = ?";
    private static final String GET_USER_FULL_INFO = "SELECT * FROM final_project.railway_system.user WHERE role = ? ORDER BY email";
    private static final String BLOCK_USER = "UPDATE final_project.railway_system.user SET blocked = ? WHERE user_id = ?";


    @Override
    public String create(User entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setString(6, entity.getPhone());
            preparedStatement.setObject(7, entity.getBirthDate());
            preparedStatement.setString(8, entity.getRole().toString());
            preparedStatement.setBoolean(9, entity.isBlocked());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t create user", e);
        }
        return uid;
    }

    @Override
    public User read(String id) {
        Connection connection = ConnectionManager.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = DbUtils.extract(rs);
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t read user. ID = " + id, e);
        }
        return user;
    }

    @Override
    public boolean update(User entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getFirstName());
            ps.setString(4, entity.getLastName());
            ps.setString(5, entity.getPhone());
            ps.setObject(6, entity.getBirthDate());
            ps.setString(7, entity.getRole().toString());
            ps.setBoolean(8, entity.isBlocked());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t update user. ID = " + entity.getUserId(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setString(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t delete user. ID = " + id, e);
        }
        return result;
    }

    @Override
    public boolean isUserExist(String email) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("User doesn't exist. Email = " + email, e);
        }
        return result;
    }

    @Override
    public User getByEmail(String email) {
        User user = new User();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = DbUtils.extract(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t get user by email. Email = " + email, e);
        }
        return user;
    }

    @Override
    public List<User> getUserInfo(String role) {
        List<User> fullUserList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_FULL_INFO)) {
            preparedStatement.setString(1, role);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                fullUserList.add(DbUtils.extract(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get all user information. Role = " + role, e);
        }
        return fullUserList;
    }

    @Override
    public void updateBlocked(String idUser, boolean blockStatus) {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(BLOCK_USER)) {
            ps.setBoolean(1, blockStatus);
            ps.setString(2, idUser);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't update blocked in table \"user\". ID user = " + idUser + " block status = " + blockStatus, e);
        }
    }
}