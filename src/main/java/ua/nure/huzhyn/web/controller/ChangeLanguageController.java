package ua.nure.huzhyn.web.controller;

import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/change_language")
public class ChangeLanguageController extends HttpServlet {
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
}
