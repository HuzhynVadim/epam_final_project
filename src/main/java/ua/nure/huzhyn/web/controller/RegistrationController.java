package ua.nure.huzhyn.web.controller;


import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.User;
import ua.nure.huzhyn.model.entity.enums.UserRole;
import ua.nure.huzhyn.services.UserService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.RegistrationValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        RegistrationValidator registrationValidator = new RegistrationValidator();
        HttpSession session = request.getSession();
        try {
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setFirstName(request.getParameter("first_name"));
            user.setLastName(request.getParameter("last_name"));
            user.setPhone(request.getParameter("phone"));
            Date birthDate = Date.valueOf(request.getParameter("birth_date"));
            user.setBirthDate(birthDate.toLocalDate());
            user.setRole(UserRole.USER);
            user.setBlocked(false);

            registrationValidator.isValidClientRegister(user);
            String id = userService.registr(user);
            user.setUserId(String.valueOf(id));
            session.setAttribute(AppContextConstant.SESSION_USER, user);
        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        response.sendRedirect("home");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(AppContextConstant.USER_SERVICE);
    }
}