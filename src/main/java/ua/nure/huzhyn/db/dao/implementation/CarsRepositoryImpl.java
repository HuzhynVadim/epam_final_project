package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.CarsRepository;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DBException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.enums.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class CarsRepositoryImpl implements CarsRepository {
    private static final Logger LOGGER = Logger.getLogger(CarsRepositoryImpl.class);
    private static final String ADD_CAR = "INSERT INTO final_project.railway_system.car (car_id, car_type, car_number, train_id, seats, price) VALUES (?,?,?,?,?,?);";
    private static final String GET_CAR_BY_ID = "SELECT * FROM final_project.railway_system.car WHERE car_id = ?";
    private static final String DELETE_CAR = "DELETE FROM final_project.railway_system.car WHERE car_id = ?";
    private static final String UPDATE_CAR = "UPDATE final_project.railway_system.car SET car_type = ?, car_number = ?, train_id = ?, seats = ?, price = ? WHERE car_id = ?";

    @Override
    public String create(Car entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CAR)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, entity.getCarType().toString());
            preparedStatement.setString(3, entity.getCarNumber());
            preparedStatement.setString(4, entity.getTrainId());
            preparedStatement.setInt(5, entity.getSeats());
            preparedStatement.setBigDecimal(6, entity.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }

        return uid;
    }

    @Override
    public Optional<Car> read(String id) {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return Optional.of(extract(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }
    }

    @Override
    public boolean update(Car entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CAR)) {
            ps.setString(1, entity.getCarType().toString());
            ps.setString(2, entity.getCarNumber());
            ps.setString(3, entity.getTrainId());
            ps.setInt(4, entity.getSeats());
            ps.setBigDecimal(5, entity.getPrice());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Update car " + entity.getCarId(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR)) {
            preparedStatement.setString(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Delete car " + id, e);
        }
        return result;
    }

    private Car extract(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setCarId(resultSet.getString("car_id"));
        car.setCarType(CarType.valueOf(resultSet.getString("car_type")));
        car.setCarNumber(resultSet.getString("car_number"));
        car.setTrainId(resultSet.getString("train_id"));
        car.setSeats(resultSet.getInt("seats"));
        car.setPrice(resultSet.getBigDecimal("price"));
        return car;
    }
}