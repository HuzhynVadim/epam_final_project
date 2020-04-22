package ua.nure.huzhyn.web.controller;

import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rout_delete")
public class RoutDeleteController extends HttpServlet {

    private RoutService routService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String routsId = request.getParameter("routs_id");
        routService.removeRout(routsId);
        response.sendRedirect("administrator_account");
    }

    @Override
    public void init(ServletConfig config) {
        routService = (RoutService) config.getServletContext().getAttribute(AppContextConstant.ROUT_SERVICE);

    }
}
