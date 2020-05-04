package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.services.RoutService;
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
    private static final Logger LOGGER = Logger.getLogger(UserAccountController.class);

    private OrderService orderService;
    private RoutService routService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("user_id");
        request.setAttribute("user_id", userId);
        List<Order> orderList = orderService.getOrderByUserId(userId);
        for (int i = 0; i <= orderList.size() - 1; i++) {
            RoutInfoDto routInfoDto = routService.getRoutById(orderList.get(i).getRoutsId());
            orderList.get(i).setRoutsId(routInfoDto.getRoutName());
        }
        request.setAttribute("order_list", orderList);

        request.getRequestDispatcher("WEB-INF/jsp/userAccount.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);
        routService = (RoutService) config.getServletContext().getAttribute(AppContextConstant.ROUT_SERVICE);
        LOGGER.trace("user_account Servlet init");

    }
}
