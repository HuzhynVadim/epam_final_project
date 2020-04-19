package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.enums.OrderStatus;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.OrderValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@WebServlet("/administrator_edit_info_order")
public class AdministratorEditInfoOrderController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorEditInfoOrderController.class);
    private OrderService orderService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderValidator orderValidator = new OrderValidator();
        Order order = new Order();
        String orderId = request.getParameter("order_id");
        OrderStatus status = null;
        try {
            status = OrderStatus.valueOf(request.getParameter("order_status"));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        orderValidator.isValidOrder(order);
        orderService.updateOrderStatus(orderId, status);
        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String orderId = request.getParameter("order_id");
        Order order = orderService.getOrderById(orderId);
        request.setAttribute("current_order", order);
        List<OrderStatus> orderStatusList = new ArrayList<>(EnumSet.allOf(OrderStatus.class));
        request.setAttribute("statusList", orderStatusList);

        request.getRequestDispatcher("WEB-INF/jsp/administratorEditInfoOrder.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);
    }
}
