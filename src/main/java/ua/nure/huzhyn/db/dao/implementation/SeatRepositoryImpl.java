package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.SeatRepository;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.CarType;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
public class SeatRepositoryImpl implements SeatRepository {

    private static final Logger LOGGER = Logger.getLogger(SeatRepositoryImpl.class);
    private static final String ADD_SEAT = "INSERT INTO final_project.railway_system.seat (seat_id, car_id, seat_number, busy) VALUES (?,?,?,?)";
    private static final String GET_SEATS_BY_ID = "SELECT * FROM final_project.railway_system.seat WHERE seat_id = ?";
    private static final String DELETE_SEAT = "DELETE FROM final_project.railway_system.seat WHERE seat_id = ?";
    private static final String DELETE_ALL_SEAT_BY_CAR_ID = "DELETE FROM final_project.railway_system.seat WHERE car_id = ?";
    private static final String UPDATE_SEAT = "UPDATE final_project.railway_system.seat SET car_id = ?, seat_number = ?, busy = ? WHERE seat_id = ?";
    private static final String GET_COUNT_SEATS = "SELECT COUNT(*) FROM final_project.railway_system.seat WHERE car_id = ?";
    private static final String GET_COUNT_SEATS_BUSY = "SELECT COUNT(*) FROM final_project.railway_system.seat WHERE car_id = ? AND busy = true";
    private static final String GET_COUNT_SEATS_BY_TRAIN_ID_AND_CAR_TYPE = "SELECT count(seat_number) FROM final_project.railway_system.car as c JOIN final_project.railway_system.seat as s ON c.car_id = s.car_id JOIN final_project.railway_system.train as t ON c.train_id = t.train_id WHERE c.train_id = ? AND car_type = ? AND busy = false";
    private static final String GET_SEAT_BY_CAR_ID = "SELECT * FROM final_project.railway_system.seat WHERE car_id = ? AND busy = false ORDER BY seat_number, seat_id";
    private static final String GET_SEAT_NUMBER_BY_ID_BATCH = "SELECT * FROM final_project.railway_system.seat WHERE seat_id = ANY (?)";
    private static final String UPDATE_SEAT_BUSY = "UPDATE final_project.railway_system.seat SET busy = true WHERE seat_id = ?";
    private static final String UPDATE_SEAT_BUSY_CANCEL = "UPDATE final_project.railway_system.seat SET busy = false WHERE seat_id = ?";

    @Override
    public String create(Seat entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_SEAT)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, entity.getCarId());
            preparedStatement.setInt(3, entity.getSeatNumber());
            preparedStatement.setBoolean(4, entity.isBusy());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t create seats", e);
        }
        return uid;
    }

    @Override
    public Seat read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Seat seat = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SEATS_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                seat = extract(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't seats car. ID = " + id, e);
        }
        return seat;
    }

    private Seat extract(ResultSet resultSet) {
        Seat seat = new Seat();
        try {
            seat.setSeatId(resultSet.getString("seat_id"));
            seat.setCarId(resultSet.getString("car_id"));
            seat.setSeatNumber(resultSet.getInt("seat_number"));
            seat.setBusy(resultSet.getBoolean("busy"));
        } catch (IllegalArgumentException | SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract seat", e);
        }
        return seat;
    }

    private int extractCount(ResultSet resultSet) {
        int count;
        try {
            count = resultSet.getInt("count");
        } catch (IllegalArgumentException | SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract seat", e);
        }
        return count;
    }


    @Override
    public boolean update(Seat entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_SEAT)) {
            ps.setString(1, entity.getCarId());
            ps.setInt(2, entity.getSeatNumber());
            ps.setBoolean(3, entity.isBusy());
            ps.setString(4, entity.getSeatId());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t update seat. ID = " + entity.getCarId(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SEAT)) {
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

    @Override
    public boolean deleteAllSeatsByCarId(String carId) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_SEAT_BY_CAR_ID)) {
            preparedStatement.setString(1, carId);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t delete seats. Car ID = " + carId, e);
        }
        return result;
    }

    @Override
    public int getCountSeat(String carId) {
        Connection connection = ConnectionManager.getConnection();
        int count = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_SEATS)) {
            preparedStatement.setString(1, carId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = extractCount(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t get count seats for car. Car ID = " + carId, e);
        }
        return count;
    }

    @Override
    public int getCountSeatBusy(String carId) {
        Connection connection = ConnectionManager.getConnection();
        int count = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_SEATS_BUSY)) {
            preparedStatement.setString(1, carId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = extractCount(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t get count busy seats for car. Car ID = " + carId, e);
        }
        return count;
    }

    @Override
    public int getCountSeatByCarType(String trainId, CarType carType) {
        Connection connection = ConnectionManager.getConnection();
        int count = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_SEATS_BY_TRAIN_ID_AND_CAR_TYPE)) {
            preparedStatement.setString(1, trainId);
            preparedStatement.setString(2, String.valueOf(carType));
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = extractCount(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t get count seats for car. Train ID = " + trainId + "Car type = " + carType, e);
        }
        return count;
    }

    @Override
    public List<Seat> getSeatByCarId(String carId) {
        List<Seat> seat = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_SEAT_BY_CAR_ID)) {
            ps.setString(1, carId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                seat.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get seat list by car ID. ID = " + carId, e);
        }
        return seat;
    }

    @Override
    public List<Seat> getSeatsByIdBatch(List<String> seatsNumber) {
        List<Seat> seat = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_SEAT_NUMBER_BY_ID_BATCH)) {
            Array idsArray = connection.createArrayOf("VARCHAR", seatsNumber.toArray(new String[seatsNumber.size()]));
            ps.setArray(1, idsArray);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                seat.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get seat by ID batch. ID = " + seatsNumber, e);
        }
        return seat;
    }

    @Override
    public void takeTheSeat(String seatId) {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_SEAT_BUSY)) {
            ps.setString(1, seatId);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Seat busy" + seatId, e);
        }
    }

    @Override
    public void freeSeat(String seatId) {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_SEAT_BUSY_CANCEL)) {
            ps.setString(1, seatId);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Freed up a seat" + seatId, e);
        }
    }
}
