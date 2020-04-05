package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
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

    private RoutToStationMappingService routToStationMappingService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routsId = request.getParameter("routs_id");
        String stationId = request.getParameter("station_id");
        routToStationMappingService.removeRoutToStationMapping(routsId, stationId);

        response.sendRedirect("administrator_details_set_rout?routs_id=" + routsId);
    }

    @Override
    public void init(ServletConfig config) {
        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute(AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE);

    }
}
