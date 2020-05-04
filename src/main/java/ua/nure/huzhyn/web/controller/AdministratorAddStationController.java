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

@WebServlet("/administrator_add_station")
public class AdministratorAddStationController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorAddStationController.class);

    private StationService stationService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StationValidator stationValidator = new StationValidator();
        Station station = new Station();
        station.setStation(request.getParameter("station_station"));
        stationValidator.isValidStation(station);
        stationService.addStation(station);
        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/administratorAddStation.jsp").forward(request, response);
    }

    public void init(ServletConfig config) {
        stationService = (StationService) config.getServletContext().getAttribute(AppContextConstant.STATION_SERVICE);
        LOGGER.trace("administrator_add_station Servlet init");

    }
}
