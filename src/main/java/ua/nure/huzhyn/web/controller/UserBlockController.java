package ua.nure.huzhyn.web.controller;


import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.services.UserService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user_block")
public class UserBlockController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserBlockController.class);

    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String userId = String.valueOf(request.getParameter("user_id"));
            boolean blockStatus = Boolean.parseBoolean(request.getParameter("block_status"));
            userService.updateBlocked(userId, blockStatus);

        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect data received");
            throw new IncorrectDataException("Incorrect data received");
        }
        response.sendRedirect("administrator_account");
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(AppContextConstant.USER_SERVICE);
    }
}
