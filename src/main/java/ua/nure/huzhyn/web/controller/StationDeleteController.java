package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/station_delete")
public class StationDeleteController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(StationDeleteController.class);

    private StationService stationService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String stationId = request.getParameter("station");
        stationService.removeStation(stationId);
        response.sendRedirect("administrator_account");
    }

    @Override
    public void init(ServletConfig config) {
        stationService = (StationService) config.getServletContext().getAttribute(AppContextConstant.STATION_SERVICE);
        LOGGER.trace("station_delete Servlet init");
    }
}
