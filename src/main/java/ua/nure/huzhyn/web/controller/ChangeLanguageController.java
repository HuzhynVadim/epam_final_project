package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/change_language")
public class ChangeLanguageController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ChangeLanguageController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (!Objects.isNull(request.getParameter("lang"))) {
            if (AppContextConstant.LOCALE_EN.equals(request.getParameter("lang"))) {
                session.setAttribute("locale", AppContextConstant.LOCALE_EN);
            } else if (AppContextConstant.LOCALE_RU.equals(request.getParameter("lang"))) {
                session.setAttribute("locale", AppContextConstant.LOCALE_RU);
            }
        }
        if (session.isNew()) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @Override
    public void init(ServletConfig config)  {
        LOGGER.trace("change_language Servlet init");

    }
}
