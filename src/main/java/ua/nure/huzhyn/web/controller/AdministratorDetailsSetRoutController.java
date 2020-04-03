package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.services.RoutToStationMappingService;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/administrator_details_set_rout")
public class AdministratorDetailsSetRoutController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorAccountController.class);

    private StationService stationService;
    private RoutService routService;


    private RoutToStationMappingService routToStationMappingService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Station> stationList = stationService.getAllStationList();
        request.setAttribute("station_list", stationList);
        List<RoutInfoDto> routList = routService.getAllRoutList();
        request.setAttribute("rout_list", routList);

        List<RoutToStationMapping> routToStationMappingList = routToStationMappingService.getAllRoutToStationMappingList();
        request.setAttribute("rout_m_list", routToStationMappingList);

        request.getRequestDispatcher("WEB-INF/jsp/administratorDetailsSetRout.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {

        stationService = (StationService) config.getServletContext().getAttribute((AppContextConstant.STATION_SERVICE));
        routService = (RoutService) config.getServletContext().getAttribute((AppContextConstant.ROUT_SERVICE));

        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));

    }
}