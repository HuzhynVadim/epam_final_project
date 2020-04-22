package ua.nure.huzhyn.services.implementation;

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
import java.util.List;

public class OrderServiceImpl implements OrderService {

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
        Rout rout = transactionManager.execute(() -> routsRepository.read(routsId).orElseThrow(() -> new IncorrectDataException("")));
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

    private void validateFreeSeats(int freeSeatsCount) {
        if (freeSeatsCount < 0) {
            throw new IncorrectDataException("");
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
