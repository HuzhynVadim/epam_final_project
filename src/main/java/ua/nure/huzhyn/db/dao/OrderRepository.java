package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;

import java.util.List;

public interface OrderRepository extends CRUD<Order, String> {

    boolean updateOrderStatus(String orderId, OrderStatus status);

    List<Order> getAllOrderList();

    Order getOrderById(String orderId);

    List<Order> getOrderByUserId(String userId);
}
