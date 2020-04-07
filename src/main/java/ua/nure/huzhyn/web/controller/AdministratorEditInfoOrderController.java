package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Order;
import ua.nure.huzhyn.model.entity.Rout;
import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.services.OrderService;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.RoutValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/administrator_edit_info_order")
public class AdministratorEditInfoOrderController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorEditInfoOrderController.class);
    private OrderService orderService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoutValidator routValidator = new RoutValidator();
        Rout rout = new Rout();
//        try {
//            rout.setRoutsId(request.getParameter("rout_id"));
//            rout.setRoutName(request.getParameter("rout_name"));
//            rout.setRoutNumber(request.getParameter("rout_number"));
//            rout.setTrainId(request.getParameter("train_number"));
//            routValidator.isValidRout(rout);
//            orderService.updateRout(rout);
//        } catch (NumberFormatException e) {
//            LOGGER.error("Incorrect data entered");
//            throw new IncorrectDataException("Incorrect data entered", e);
//        }
//        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String orderId = request.getParameter("order_id");
        Order order = orderService.getOrderById(orderId);
        request.setAttribute("order_id", order);
        request.getRequestDispatcher("WEB-INF/jsp/administratorEditInfoOrder.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstant.ORDER_SERVICE);
    }
}
