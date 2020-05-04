package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;

import java.util.List;

/**
 * DAO for the {@link Order} objects.
 * Besides the basic CRUD methods it provides methods to update the status of an order, receive a list of orders,
 * receive an order by identifier, receive an order by user identifier.
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface OrderRepository extends CRUD<Order, String> {

    /**
     * Update order status {@link Order} by id in db
     *
     * @param orderId {@link Order} order id
     * @param status {@link Order} order status
     * @return true if order status update or false is down
     */
    boolean updateOrderStatus(String orderId, OrderStatus status);

    /**
     * Get list all {@link Order}
     *
     * @return list of {@link Order}
     */
    List<Order> getAllOrderList();

    /**
     * Get {@link Order} by id
     *
     * @param orderId {@link Order} order id
     * @return {@link Order}
     */
    Order getOrderById(String orderId);

    /**
     * Get list of {@link Order} by user id
     *
     * @param userId {@link Order} user id
     * @return list of {@link Order}
     */
    List<Order> getOrderByUserId(String userId);
}
