package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rout_delete")
public class RoutDeleteController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RoutDeleteController.class);

    private RoutService routService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String routsId = request.getParameter("routs_id");
            routService.removeRout(routsId);
        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect rout ID. Rout ID = " + request.getParameter("routsId"));
            throw new IncorrectDataException("Incorrect rout ID", e);
        }
        response.sendRedirect("administrator_account/");
    }

    @Override
    public void init(ServletConfig config) {
        routService = (RoutService) config.getServletContext().getAttribute(AppContextConstant.ROUT_SERVICE);

    }
}
