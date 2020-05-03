package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrderList();

    Order getOrderById(String orderId);

    boolean updateOrderStatus(String orderId, OrderStatus status);

    List<Order> getOrderByUserId(String userId);

    void addOrder(Order order, String routsId, List<Seat> seats);

    void cancelOrder(String orderId);

    BigDecimal getPrice(String carType, String countOfSeats);
}
