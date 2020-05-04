package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.User;
import ua.nure.huzhyn.services.UserService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.LoginValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginValidator loginValidator = new LoginValidator();
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            loginValidator.isValid(login, password);
            User user = userService.isValidUser(login, password);
            HttpSession session = request.getSession();
            if (user != (null)) {
                session.setAttribute(AppContextConstant.LOCALE, AppContextConstant.LOCALE_RU);
                session.setAttribute(AppContextConstant.SESSION_USER, user);
                response.sendRedirect("home");
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(AppContextConstant.USER_SERVICE);
    }
}
