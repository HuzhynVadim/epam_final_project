package ua.nure.huzhyn.web.controller;


import org.apache.log4j.Logger;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.StationValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator_edit_info_station")
public class AdministratorEditInfoStationController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorEditInfoStationController.class);
    private StationService stationService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StationValidator stationValidator = new StationValidator();
        Station station = new Station();
        station.setStationId(request.getParameter("station_id"));
        station.setStation(request.getParameter("station"));
        stationValidator.isValidStation(station);
        stationService.updateStation(station);
        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stationId = request.getParameter("station");
        Station station = stationService.getStationById(stationId);
        request.setAttribute("current_station", station);
        request.getRequestDispatcher("WEB-INF/jsp/administratorEditInfoStation.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        stationService = (StationService) config.getServletContext().getAttribute(AppContextConstant.STATION_SERVICE);

    }
}
