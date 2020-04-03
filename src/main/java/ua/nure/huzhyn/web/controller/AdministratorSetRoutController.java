package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.services.RoutToStationMappingService;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.RoutToStationMappingValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/administrator_set_rout")
public class AdministratorSetRoutController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorEditInfoStationController.class);

    private StationService stationService;
    private RoutToStationMappingService routToStationMappingService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoutToStationMappingValidator routToStationMappingValidator = new RoutToStationMappingValidator();
        RoutToStationMapping routToStationMapping = new RoutToStationMapping();
        try {

            routToStationMapping.setRoutsMId(request.getParameter("rout_m_id"));
            routToStationMapping.setOrder(request.getParameter("station_station"));
            routToStationMapping.setStationId(request.getParameter("routs_id"));
            routToStationMapping.setStationArrivalDate(LocalDateTime.parse(request.getParameter("station_arrival_date")));
            routToStationMapping.setStationDispatchData(LocalDateTime.parse(request.getParameter("station_dispatch_date")));
            routToStationMapping.setOrder(request.getParameter("station_order"));
            routToStationMappingValidator.isValidRoutToStationMapping(routToStationMapping);
            routToStationMappingService.addRoutToStationMapping(routToStationMapping);
        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        String routsId = request.getParameter("rout");
//        RoutInfoDto rout = routService.getRoutById(routsId);
//        request.setAttribute("current_rout", rout);

        List<Station> stationList = stationService.getAllStationList();
        request.setAttribute("stationList", stationList);
        request.getRequestDispatcher("WEB-INF/jsp/administratorSetRout.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        stationService = (StationService) config.getServletContext().getAttribute(AppContextConstant.STATION_SERVICE);
        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute(AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE);

    }
}
