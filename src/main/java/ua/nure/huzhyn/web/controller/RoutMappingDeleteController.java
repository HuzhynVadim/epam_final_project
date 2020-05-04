package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.services.RoutMappingService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remove_rout_mapping")
public class RoutMappingDeleteController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RoutMappingDeleteController.class);

    private RoutMappingService routMappingService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String routsId = request.getParameter("routs_id");
        String stationId = request.getParameter("station_id");
        routMappingService.removeRoutToStationMapping(routsId, stationId);

        response.sendRedirect("administrator_details_set_rout?routs_id=" + routsId);
    }

    @Override
    public void init(ServletConfig config) {
        routMappingService = (RoutMappingService) config.getServletContext().getAttribute(AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE);
        LOGGER.trace("remove_rout_mapping Servlet init");
    }
}
