package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.model.entity.Order;

import java.util.List;

public interface OrderRepository extends CRUD<Order, String> {

    List<Order> getAllOrder();
}