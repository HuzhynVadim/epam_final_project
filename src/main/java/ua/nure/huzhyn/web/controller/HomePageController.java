package ua.nure.huzhyn.web.controller;


import org.apache.log4j.Logger;
import ua.nure.huzhyn.model.entity.User;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/home")
public class HomePageController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(HomePageController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppContextConstant.SESSION_USER);
        request.setAttribute("role", user.getRole());

        request.getRequestDispatcher("WEB-INF/jsp/homePage.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        LOGGER.trace("home Servlet init");

    }
}
