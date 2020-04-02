package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.db.dao.OrderRepository;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private TransactionManager transactionManager;


    public OrderServiceImpl(OrderRepository orderRepository, TransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public List<Order> getAllOrderList() {
        return transactionManager.execute(() -> orderRepository.getAllOrder());
    }
}
