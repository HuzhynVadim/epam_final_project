package ua.nure.huzhyn.web.controller;

import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user_account")
public class UserAccountController extends HttpServlet {

    private OrderService orderService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("user_id");
        request.setAttribute("user_id", userId);
        List<Order> orderList = orderService.getOrderByUserId(userId);
        request.setAttribute("order_list", orderList);

        request.getRequestDispatcher("WEB-INF/jsp/userAccount.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);


    }
}
