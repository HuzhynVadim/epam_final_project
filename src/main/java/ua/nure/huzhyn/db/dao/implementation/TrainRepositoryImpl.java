package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.TrainRepository;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DBException;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrainRepositoryImpl implements TrainRepository {
    private static final Logger LOGGER = Logger.getLogger(StationRepositoryImpl.class);
    private static final String ADD_TRAIN = "INSERT INTO final_project.railway_system.train (train_id, train_number) VALUES (?,?)";
    private static final String GET_TRAIN_BY_ID = "SELECT * FROM final_project.railway_system.train WHERE train_id = ?";
    private static final String DELETE_TRAIN = "DELETE FROM final_project.railway_system.train WHERE train_id = ?";
    private static final String UPDATE_TRAIN = "UPDATE final_project.railway_system.train SET train_number = ? WHERE train_id = ?";
    private static final String GET_ALL_TRAIN_NUMBER = "SELECT * FROM final_project.railway_system.train";


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
            throw new DBException("", e);
        }

        return uid;
    }

    @Override
    public Optional<Train> read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Optional<Train> train;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TRAIN_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            train = Optional.of(extract(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }
        return train;
    }

    @Override
    public boolean update(Train entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TRAIN)) {
            ps.setString(1, entity.getTrainId());
            ps.setString(2, entity.getTrainNumber());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Update station " + entity.getTrainId(), e);
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
            throw new DBException("Delete train " + id, e);
        }
        return result;
    }

    private Train extract(ResultSet rs) throws SQLException {
    Train train = new Train();
    train.setTrainId(rs.getString("train_id"));
    train.setTrainNumber(rs.getString("train_number"));
    return train;
    }

    @Override
    public List<Train> getAllTrain() {
        List<Train> Train = new ArrayList<Train>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_TRAIN_NUMBER)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Train.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            String message = String.format("Can't get train");
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return Train;
    }
}
