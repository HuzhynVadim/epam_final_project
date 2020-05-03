package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.TrainRepository;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainRepositoryImpl implements TrainRepository {
    private static final Logger LOGGER = Logger.getLogger(StationRepositoryImpl.class);
    private static final String ADD_TRAIN = "INSERT INTO final_project.railway_system.train (train_id, train_number) VALUES (?,?)";
    private static final String GET_TRAIN_BY_ID = "SELECT * FROM final_project.railway_system.train WHERE train_id = ?";
    private static final String DELETE_TRAIN = "DELETE FROM final_project.railway_system.train WHERE train_id = ?";
    private static final String UPDATE_TRAIN = "UPDATE final_project.railway_system.train SET train_number = ? WHERE train_id = ?";
    private static final String GET_ALL_TRAIN_NUMBER = "SELECT * FROM final_project.railway_system.train ORDER BY train_number";


    @Override
    public String create(Train entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TRAIN)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, entity.getTrainNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t create train.", e);
        }

        return uid;
    }

    @Override
    public Train read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Train train;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TRAIN_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            train = extract(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t read train. ID = " + id, e);
        }
        return train;
    }

    @Override
    public boolean update(Train entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TRAIN)) {
            ps.setString(1, entity.getTrainNumber());
            ps.setString(2, entity.getTrainId());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t update train. ID = " + entity.getTrainId(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRAIN)) {
            preparedStatement.setString(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t delete train because he is on the route. ID = " + id, e);
        }
        return result;
    }

    private Train extract(ResultSet rs) {
        Train train = new Train();
        try {
            train.setTrainId(rs.getString("train_id"));
            train.setTrainNumber(rs.getString("train_number"));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract train.", e);
        }
        return train;
    }

    @Override
    public Train getTrainById(String trainId) {
        Train train = new Train();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_TRAIN_BY_ID)) {
            ps.setString(1, trainId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                train = extract(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get Station by train ID. ID = " + trainId, e);
        }
        return train;
    }

    @Override
    public List<Train> getAllTrainList() {
        List<Train> Train = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_TRAIN_NUMBER)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Train.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t get all train list.", e);
        }
        return Train;
    }

}
