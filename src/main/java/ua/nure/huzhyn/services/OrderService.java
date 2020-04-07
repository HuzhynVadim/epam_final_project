package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrderList();

    Order getOrderById(String orderId);
}
