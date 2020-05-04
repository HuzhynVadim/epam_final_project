package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.services.LogoutService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LogoutController.class);

    private LogoutService logoutService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logoutService.logout(request);
        response.sendRedirect("login.jsp");
    }

    @Override
    public void init(ServletConfig config) {
        logoutService = (LogoutService) config.getServletContext().getAttribute(AppContextConstant.LOGOUT_SERVICE);
        LOGGER.trace("logout Servlet init");

    }
}