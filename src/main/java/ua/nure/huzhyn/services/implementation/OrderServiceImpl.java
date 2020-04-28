package ua.nure.huzhyn.services.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.OrderRepository;
import ua.nure.huzhyn.db.dao.RoutsRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.Rout;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;
import ua.nure.huzhyn.services.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);
    private OrderRepository orderRepository;
    private RoutsRepository routsRepository;
    private TransactionManager transactionManager;

    public OrderServiceImpl(OrderRepository orderRepository, TransactionManager transactionManager, RoutsRepository routsRepository) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
        this.routsRepository = routsRepository;
    }

    @Override
    public void addOrder(Order order, String routsId) {
        order.setPrice(order.getCarType().getPrice().multiply(new BigDecimal(order.getCountOfSeats())));
        Rout rout = transactionManager.execute(() -> routsRepository.read(routsId).orElseThrow(() -> {
            IncorrectDataException e = new IncorrectDataException("Сan`t create an order because can't find the route");
            LOGGER.error(e);
            return e;
        }));
        if (CarType.RESERVED_SEAT == order.getCarType()) {
            int diff = rout.getReservedFreeSeatsCount() - order.getCountOfSeats();
            validateFreeSeats(diff);
            rout.setReservedFreeSeatsCount(diff);
        }
        if (CarType.COMPARTMENT == order.getCarType()) {
            int diff = rout.getCompartmentFreeSeatsCount() - order.getCountOfSeats();
            validateFreeSeats(diff);
            rout.setCompartmentFreeSeatsCount(diff);
        }
        if (CarType.COMMON == order.getCarType()) {
            int diff = rout.getCommonFreeSeatsCount() - order.getCountOfSeats();
            validateFreeSeats(diff);
            rout.setCommonFreeSeatsCount(diff);
        }
        transactionManager.execute(() -> {
            routsRepository.update(rout);
            orderRepository.create(order);
            return null;
        });

    }

    @Override
    public void cancelOrder(String orderId) {
        Order order = getOrderById(orderId);
        String routsId = order.getRoutsId();
        LocalDateTime now = LocalDateTime.now();
        order.setOrderStatus(OrderStatus.ORDER_CANCELED);
        Rout rout = transactionManager.execute(() -> routsRepository.read(routsId).orElseThrow(() -> {
            IncorrectDataException e = new IncorrectDataException("Сan`t create an order because can't find the route");
            LOGGER.error(e);
            return e;
        }));
        if (CarType.RESERVED_SEAT == order.getCarType()) {
            int diff = rout.getReservedFreeSeatsCount() + order.getCountOfSeats();
            validateDate(order, now);
            rout.setReservedFreeSeatsCount(diff);

        }
        if (CarType.COMPARTMENT == order.getCarType()) {
            int diff = rout.getCompartmentFreeSeatsCount() + order.getCountOfSeats();
            validateDate(order, now);
            rout.setCompartmentFreeSeatsCount(diff);
        }
        if (CarType.COMMON == order.getCarType()) {
            int diff = rout.getCommonFreeSeatsCount() + order.getCountOfSeats();
            validateDate(order, now);
            rout.setCommonFreeSeatsCount(diff);
        }
        transactionManager.execute(() -> {
            routsRepository.update(rout);
            orderRepository.updateOrderStatus(order.getOrderId(), order.getOrderStatus());
            return null;
        });
    }

    private void validateDate(Order order, LocalDateTime now) {
        if (now.isAfter(order.getArrivalDate()) || now.isEqual(order.getArrivalDate())) {
            IncorrectDataException e = new IncorrectDataException("Can`t cancel the order because the cancellation period has been reached");
            LOGGER.error(e);
            throw e;
        }
    }

    private void validateFreeSeats(int freeSeatsCount) {
        if (freeSeatsCount < 0) {
            IncorrectDataException e = new IncorrectDataException("Can`t create an order because no free places");
            LOGGER.error(e);
            throw e;
        }
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        return transactionManager.execute(() -> orderRepository.getOrderByUserId(userId));
    }

    @Override
    public boolean updateOrderStatus(String orderId, OrderStatus status) {
        return transactionManager.execute(() -> orderRepository.updateOrderStatus(orderId, status));
    }

    @Override
    public Order getOrderById(String orderId) {
        return transactionManager.execute(() -> orderRepository.getOrderById(orderId));
    }

    @Override
    public List<Order> getAllOrderList() {
        List<Order> result = transactionManager.execute(() -> orderRepository.getAllOrderList());
        for (Order order : result) {
            order.setPrice(order.getCarType().getPrice());
        }
        return result;
    }
}
