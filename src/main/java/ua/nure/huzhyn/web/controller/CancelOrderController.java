package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancel_order")
public class CancelOrderController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CancelOrderController.class);

    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        String userId = req.getParameter("user_id");
        orderService.cancelOrder(orderId);
        resp.sendRedirect("user_account?user_id=" + userId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {


    }

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);
        LOGGER.trace("cancel_order Servlet init");

    }
}
