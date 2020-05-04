package ua.nure.huzhyn.services.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.OrderRepository;
import ua.nure.huzhyn.db.dao.SeatRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.Seat;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.services.SeatService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;
    private SeatRepository seatRepository;
    private TransactionManager transactionManager;
    private SeatService seatService;

    public OrderServiceImpl(OrderRepository orderRepository, SeatService seatService, TransactionManager transactionManager, SeatRepository seatRepository) {
        this.orderRepository = orderRepository;
        this.seatRepository = seatRepository;
        this.transactionManager = transactionManager;
        this.seatService = seatService;
    }

    @Override
    public void addOrder(Order order, String routsId, List<Seat> seats) {
        order.setPrice(order.getCarType().getPrice().multiply(new BigDecimal(order.getCountOfSeats())));
        transactionManager.execute(() -> {
            for (int i = 0; i <= seats.size() - 1; i++) {
                seatRepository.takeTheSeat(seats.get(i).getSeatId());
            }
            orderRepository.create(order);
            return null;
        });

    }

    @Override
    public void cancelOrder(String orderId) {
        Order order = getOrderById(orderId);
        LocalDateTime now = LocalDateTime.now();
        order.setOrderStatus(OrderStatus.ORDER_CANCELED);
        validateDate(order, now);
        String seatId = order.getSeatId();
        transactionManager.execute(() -> {
            List<String> seatsId = seatService.getSeatsId(seatId);
            List<Seat> seatsByIdBatch = seatRepository.getSeatsByIdBatch(seatsId);
            for (int i = 0; i <= seatsByIdBatch.size() - 1; i++) {
                seatRepository.freeSeat(seatsByIdBatch.get(i).getSeatId());
            }
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


    @Override
    public List<Order> getOrderByUserId(String userId) {
        return transactionManager.execute(() -> orderRepository.getOrderByUserId(userId));
    }

    @Override
    public boolean updateOrderStatus(String orderId, OrderStatus status) {
        return transactionManager.execute(() -> {
            if (status == OrderStatus.ORDER_DECLINED || status == OrderStatus.ORDER_CANCELED) {
                Order order = orderRepository.getOrderById(orderId);
                String seatId = order.getSeatId();
                ArrayList<String> seatsId = seatService.getSeatsId(seatId);
                List<Seat> seatsByIdBatch = seatRepository.getSeatsByIdBatch(seatsId);
                for (int i = 0; i <= seatsByIdBatch.size() - 1; i++) {
                    seatRepository.freeSeat(seatsByIdBatch.get(i).getSeatId());
                }
            }
            return orderRepository.updateOrderStatus(orderId, status);
        });
    }

    @Override
    public Order getOrderById(String orderId) {
        return transactionManager.execute(() -> orderRepository.getOrderById(orderId));
    }

    @Override
    public List<Order> getAllOrderList() {
        return transactionManager.execute(() -> orderRepository.getAllOrderList());

    }

    @Override
    public BigDecimal getPrice(String carType, String countOfSeats) {
        return CarType.valueOf(carType).getPrice().multiply(new BigDecimal(countOfSeats));
    }
}
