package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.CarRepository;
import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DBException;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.enums.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarRepositoryImpl implements CarRepository {
    private static final Logger LOGGER = Logger.getLogger(CarRepositoryImpl.class);
    private static final String ADD_CAR = "INSERT INTO final_project.railway_system.car (car_id, car_type, car_number, train_id, seats, price) VALUES (?,?,?,?,?,?);";
    private static final String GET_CAR_BY_ID = "SELECT * FROM final_project.railway_system.car WHERE car_id = ?";
    private static final String GET_CAR_BY_TRAIN_ID = "SELECT * FROM final_project.railway_system.car WHERE train_id = ?";
    private static final String DELETE_CAR = "DELETE FROM final_project.railway_system.car WHERE car_id = ?";
    private static final String UPDATE_CAR = "UPDATE final_project.railway_system.car SET car_type = ?, car_number = ?, train_id = ?, seats = ?, price = ? WHERE car_id = ?";
    private static final String GET_ALL_CAR = "SELECT * FROM final_project.railway_system.car as c LEFT OUTER JOIN final_project.railway_system.train as t ON c.train_id = t.train_id ORDER BY train_number, car_number ASC";

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
            ps.setString(6, entity.getCarId());
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

    private CarDto extractCarDto(ResultSet resultSet) throws SQLException {
        CarDto car = new CarDto();
        car.setCarId(resultSet.getString("car_id"));
        car.setCarType(CarType.valueOf(resultSet.getString("car_type")));
        car.setCarNumber(resultSet.getString("car_number"));
        car.setTrainId(resultSet.getString("train_id"));
        car.setSeats(resultSet.getInt("seats"));
        car.setPrice(resultSet.getBigDecimal("price"));
        car.setTrainNumber(resultSet.getString("train_number"));
        return car;
    }

    @Override
    public Car getCarById(String carId) {
        Car car = new Car();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_CAR_BY_ID)) {
            ps.setString(1, carId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                car = extract(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            String message = String.format("Can't get car by id. Id = %s", carId);
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return car;
    }

    @Override
    public List<Car> getCarByTrainId(String trainId) {
        List<Car> cars = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_CAR_BY_TRAIN_ID)) {
            ps.setString(1, trainId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            String message = String.format("Can't get car by train id. Id = %s", trainId);
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return cars;
    }

    @Override
    public List<CarDto> getAllCarList() {
        List<CarDto> Car = new ArrayList<CarDto>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CAR)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Car.add(extractCarDto(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            String message = String.format("Can't get Car");
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return Car;
    }
}