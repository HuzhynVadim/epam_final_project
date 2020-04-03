package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.services.RoutToStationMappingService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remove_rout_mapping")
public class RoutMappingDeleteController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RoutMappingDeleteController.class);

    RoutToStationMappingService routToStationMappingService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String routsMId = request.getParameter("routM");
            routToStationMappingService.removeRoutToStationMapping(routsMId);
        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect rout ID. Rout ID = " + request.getParameter("routsMId"));
            throw new IncorrectDataException("Incorrect rout ID", e);
        }
        response.sendRedirect("administrator_account");
    }

    @Override
    public void init(ServletConfig config) {
        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute(AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE);

    }
}
