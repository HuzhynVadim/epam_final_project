package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

/**
 * Provides service which contains methods for provide information about {@link Order}
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface OrderService {

    /**
     * Get list all order
     *
     * @return list all order
     */
    List<Order> getAllOrderList();

    /**
     * Get order by order id
     *
     * @param orderId {@link Order} order id
     * @return order by order id
     */
    Order getOrderById(String orderId);

    /**
     * Updates order status
     *
     * @param orderId {@link Order} order id
     * @param status {@link Order} order status
     * @return true if the order is updated or false if not updated
     */
    boolean updateOrderStatus(String orderId, OrderStatus status);

    /**
     * Gets a list of all user orders
     *
     * @param userId {@link Order} user id
     * @return list {@link Order}  by user id
     */
    List<Order> getOrderByUserId(String userId);

    /**
     * Add {@link Order}
     *
     * @param order {@link Order}
     * @param routsId routs id
     * @param seats list seat
     */
    void addOrder(Order order, String routsId, List<Seat> seats);

    /**
     * Cancellation of order
     *
     * @param orderId {@link Order} order id
     */
    void cancelOrder(String orderId);

    /**
     * Counts the price of an order
     *
     * @param carType car type
     * @param countOfSeats count of seats
     * @return price for the indicated count of seats
     */
    BigDecimal getPrice(String carType, String countOfSeats);

    BigDecimal getPriceOfSuccessfulOrders(String userId);
}
