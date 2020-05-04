package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.CarRepository;
import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.enums.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarRepositoryImpl implements CarRepository {

    private static final Logger LOGGER = Logger.getLogger(CarRepositoryImpl.class);
    private static final String ADD_CAR = "INSERT INTO final_project.railway_system.car " +
            "(car_id, car_type, car_number, train_id) VALUES (?,?,?,?);";
    private static final String GET_CAR_BY_ID = "SELECT * FROM final_project.railway_system.car WHERE car_id = ?";
    private static final String GET_CAR_BY_TRAIN_ID = "SELECT * FROM final_project.railway_system.car " +
            "WHERE train_id = ?";
    private static final String DELETE_CAR = "DELETE FROM final_project.railway_system.car WHERE car_id = ?";
    private static final String UPDATE_CAR = "UPDATE final_project.railway_system.car SET car_type = ?, " +
            "car_number = ?, train_id = ? WHERE car_id = ?";
    private static final String GET_ALL_CAR = "SELECT * FROM final_project.railway_system.car as c " +
            "LEFT OUTER JOIN final_project.railway_system.train as t ON c.train_id = t.train_id  " +
            "ORDER BY train_number, car_number";
    private static final String GET_CAR_BY_TRAIN_ID_AND_CAR_TYPE = "SELECT * FROM final_project.railway_system.car " +
            "WHERE train_id = ? AND car_type = ? ORDER BY car_number";

    @Override
    public String create(Car entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CAR)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, entity.getCarType().toString());
            preparedStatement.setString(3, entity.getCarNumber());
            preparedStatement.setString(4, entity.getTrainId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't create car.", e);
        }
        return uid;
    }

    @Override
    public Car read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Car car;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            car = (extract(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't read car. ID = " + id, e);
        }
        return car;
    }

    @Override
    public boolean update(Car entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CAR)) {
            ps.setString(1, entity.getCarType().toString());
            ps.setString(2, entity.getCarNumber());
            ps.setString(3, entity.getTrainId());
            ps.setString(4, entity.getCarId());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t update car. ID = " + entity.getCarId(), e);
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
            throw new DataBaseException("Can`t delete car. ID = " + id, e);
        }
        return result;
    }

    private Car extract(ResultSet resultSet) {
        Car car = new Car();
        try {
            car.setCarId(resultSet.getString("car_id"));
            car.setCarType(CarType.valueOf(resultSet.getString("car_type")));
            car.setCarNumber(resultSet.getString("car_number"));
            car.setTrainId(resultSet.getString("train_id"));
        } catch (IllegalArgumentException | SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract car", e);
        }
        return car;
    }

    private CarDto extractCarDto(ResultSet resultSet) {
        CarDto car = new CarDto();
        try {
            car.setCarId(resultSet.getString("car_id"));
            car.setCarType(CarType.valueOf(resultSet.getString("car_type")));
            car.setCarNumber(resultSet.getString("car_number"));
            car.setTrainId(resultSet.getString("train_id"));
            car.setTrainNumber(resultSet.getString("train_number"));
        } catch (IllegalArgumentException | SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract CarDto", e);
        }
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
            LOGGER.error(e);
            throw new DataBaseException("Can't get car by ID. ID = " + carId, e);
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
            LOGGER.error(e);
            throw new DataBaseException("Can't get car list by train ID. ID = " + trainId, e);
        }
        return cars;
    }

    @Override
    public List<CarDto> getAllCarList() {
        List<CarDto> carDtoList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CAR)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                carDtoList.add(extractCarDto(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get cars list.", e);
        }
        return carDtoList;
    }

    @Override
    public List<Car> getCarByTrainIdAndCarType(String trainId, String carType) {
        List<Car> cars = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_CAR_BY_TRAIN_ID_AND_CAR_TYPE)) {
            ps.setString(1, trainId);
            ps.setString(2, carType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get car list by train ID and car type. ID = " + trainId + "CarType = " + carType, e);
        }
        return cars;
    }
}