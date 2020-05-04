package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.OrderRepository;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.db.dao.utils.DbUtils;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger LOGGER = Logger.getLogger(OrderRepositoryImpl.class);
    private static final String ADD_ORDER = "INSERT INTO final_project.railway_system.order (order_id, train_number, car_type, price, arrival_date, dispatch_date, user_id, order_date, order_status, count_of_seats, arrival_station, dispatch_station, travel_time, routs_id, car_number, seat_number, seats_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM final_project.railway_system.order as o JOIN final_project.railway_system.user as u on o.user_id = u.user_id WHERE order_id = ?";
    private static final String UPDATE_ORDER_STATUS = "UPDATE final_project.railway_system.order as o SET order_status = ? WHERE order_id = ?";
    private static final String GET_ALL_ORDER = "SELECT * FROM final_project.railway_system.order as o JOIN final_project.railway_system.user as u on o.user_id = u.user_id";
    private static final String GET_ORDER_BY_USER_ID = "SELECT * FROM final_project.railway_system.order as o JOIN final_project.railway_system.user as u on o.user_id = u.user_id WHERE o.user_id = ?";


    @Override
    public String create(Order entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, entity.getTrainNumber());
            preparedStatement.setString(3, entity.getCarType().name());
            preparedStatement.setBigDecimal(4, entity.getPrice());
            preparedStatement.setObject(5, entity.getArrivalDate());
            preparedStatement.setObject(6, entity.getDispatchDate());
            preparedStatement.setString(7, entity.getUser().getUserId());
            preparedStatement.setObject(8, entity.getOrderDate());
            preparedStatement.setString(9, entity.getOrderStatus().name());
            preparedStatement.setInt(10, entity.getCountOfSeats());
            preparedStatement.setString(11, entity.getArrivalStation());
            preparedStatement.setString(12, entity.getDispatchStation());
            preparedStatement.setString(13, entity.getTravelTime());
            preparedStatement.setString(14, entity.getRoutsId());
            preparedStatement.setString(15, entity.getCarNumber());
            preparedStatement.setString(16, entity.getSeatNumber());
            preparedStatement.setString(17, entity.getSeatId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't create order.", e);
        }
        return uid;
    }

    @Override
    public Order read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Order order;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            order = extract(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't read car. ID = " + id, e);
        }
        return order;
    }

    @Override
    public boolean update(Order entity) {
        LOGGER.error("Unsupported operation exception");
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(String id) {
        LOGGER.error("Unsupported operation exception");
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateOrderStatus(String orderId, OrderStatus status) {
        Connection connection = ConnectionManager.getConnection();
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            ps.setString(1, status.toString());
            ps.setString(2, orderId);
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t update order. ID = " + orderId, e);
        }
        return result;
    }

    private Order extract(ResultSet rs) {
        Order order = new Order();
        try {
            order.setOrderId(rs.getString("order_id"));
            order.setTrainNumber(rs.getString("train_number"));
            order.setCarType(CarType.valueOf(rs.getString("car_type")));
            order.setPrice(rs.getBigDecimal("price"));
            order.setArrivalDate(rs.getObject("arrival_date", LocalDateTime.class));
            order.setDispatchDate(rs.getObject("dispatch_date", LocalDateTime.class));
            order.setUser(DbUtils.extract(rs));
            order.setOrderDate(rs.getObject("order_date", LocalDateTime.class));
            order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
            order.setCountOfSeats(rs.getInt("count_of_seats"));
            order.setArrivalStation(rs.getString("arrival_station"));
            order.setDispatchStation(rs.getString("dispatch_station"));
            order.setTravelTime(rs.getString("travel_time"));
            order.setRoutsId(rs.getString("routs_id"));
            order.setCarNumber(rs.getString("car_number"));
            order.setSeatNumber(rs.getString("seat_number"));
            order.setSeatId(rs.getString("seats_id"));
        } catch (SQLException | IllegalArgumentException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract order.", e);
        }
        return order;
    }

    @Override
    public List<Order> getAllOrderList() {
        List<Order> order = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDER)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get all order list.", e);
        }
        return order;
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        List<Order> order = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_USER_ID)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get order list by user ID. ID = " + userId, e);
        }
        return order;
    }

    @Override
    public Order getOrderById(String orderId) {
        Order order = new Order();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID)) {
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = extract(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get order by ID. ID = " + orderId, e);
        }
        return order;
    }
}