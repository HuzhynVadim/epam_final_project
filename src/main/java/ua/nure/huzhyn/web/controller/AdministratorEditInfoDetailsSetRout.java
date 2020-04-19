package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.services.RoutService;
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

@WebServlet("/administrator_edit_info_details_set_rout")
public class AdministratorEditInfoDetailsSetRout extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorEditInfoDetailsSetRout.class);
    private RoutService routService;
    private RoutToStationMappingService routToStationMappingService;
    private StationService stationService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoutToStationMappingValidator routToStationMappingValidator = new RoutToStationMappingValidator();
        RoutToStationMapping routToStationMapping = new RoutToStationMapping();
        String routsId = request.getParameter("routs_id");
        String stationId = request.getParameter("station_current_id");
        routToStationMapping.setRoutsId(routsId);
        routToStationMapping.setStationId(stationId);
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
        routToStationMappingService.updateRoutToStationMapping(routToStationMapping, stationId);
        response.sendRedirect("administrator_details_set_rout?routs_id=" + routsId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String routsId = request.getParameter("routs_id");
        String stationId = request.getParameter("station_id");
        List<Station> stationList = stationService.getAllStationList();
        MappingInfoDto MappingInfo = routToStationMappingService.getMappingInfo(routsId, stationId);
        request.setAttribute("routs_id", routsId);
        request.setAttribute("station_id", stationId);
        request.setAttribute("current_rout", MappingInfo);
        request.setAttribute("station_list", stationList);

        request.getRequestDispatcher("WEB-INF/jsp/administratorEditInfoDetailsSetRout.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        routService = (RoutService) config.getServletContext().getAttribute(AppContextConstant.ROUT_SERVICE);
        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute(AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE);

        stationService = (StationService) config.getServletContext().getAttribute((AppContextConstant.STATION_SERVICE));
    }
}
