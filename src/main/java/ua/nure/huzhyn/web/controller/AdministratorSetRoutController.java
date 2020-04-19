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
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/administrator_set_rout_mapping")
public class AdministratorSetRoutController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorSetRoutController.class);


    private StationService stationService;
    private RoutToStationMappingService routToStationMappingService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RoutToStationMappingValidator routToStationMappingValidator = new RoutToStationMappingValidator();
        RoutToStationMapping routToStationMapping = new RoutToStationMapping();
        String routsId = request.getParameter("routs_id");
        routToStationMapping.setRoutsId(routsId);
        routToStationMapping.setStationId(request.getParameter("station_station"));
        try {
            routToStationMapping.setStationArrivalDate(LocalDateTime.parse(request.getParameter("station_arrival_date")));
            routToStationMapping.setStationDispatchData(LocalDateTime.parse(request.getParameter("station_dispatch_data")));
            routToStationMapping.setOrder(Integer.parseInt(request.getParameter("station_order")));
        } catch (NumberFormatException | DateTimeParseException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        routToStationMappingValidator.isValidRoutToStationMapping(routToStationMapping);
        routToStationMappingService.addRoutToStationMapping(routToStationMapping);

        response.sendRedirect("administrator_details_set_rout?routs_id=" + routsId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routsId = request.getParameter("routs_id");
        request.setAttribute("routs_id", routsId);
        List<Station> stationList = stationService.getAllStationList();
        request.setAttribute("station_list", stationList);


        request.getRequestDispatcher("WEB-INF/jsp/administratorSetRout.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {

        stationService = (StationService) config.getServletContext().getAttribute((AppContextConstant.STATION_SERVICE));
        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));

    }
}
