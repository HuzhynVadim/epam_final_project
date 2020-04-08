package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrderList();

    Order getOrderById(String orderId);

    void updateOrderStatus(String orderId, OrderStatus status);
}
