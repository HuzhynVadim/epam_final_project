package ua.nure.huzhyn.web.controller;


import ua.nure.huzhyn.services.UserService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user_block")
public class UserBlockController extends HttpServlet {

    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        boolean blockStatus = Boolean.parseBoolean(request.getParameter("block_status"));
        userService.updateBlocked(userId, blockStatus);
        response.sendRedirect("administrator_account");
    }

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(AppContextConstant.USER_SERVICE);
    }
}
