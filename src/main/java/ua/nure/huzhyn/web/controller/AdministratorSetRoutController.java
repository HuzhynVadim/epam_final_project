package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.services.RoutMappingService;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.RoutMappingValidator;

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
    private RoutMappingService routMappingService;

    public static boolean contains(final List<MappingInfoDto> array, final int v) {

        boolean result = false;

        for (MappingInfoDto i : array) {
            if (i.getOrder() == v) {
                result = true;
                break;
            }
        }
        return result;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RoutMappingValidator routMappingValidator = new RoutMappingValidator();
        RoutToStationMapping routToStationMapping = new RoutToStationMapping();
        String routsId = request.getParameter("routs_id");
        int stationOrder = Integer.parseInt(request.getParameter("station_order"));
        routToStationMapping.setRoutsId(routsId);
        routToStationMapping.setStationId(request.getParameter("station_station"));
        List<MappingInfoDto> mappingList = routMappingService.getAllRoutToStationMappingListById(routsId);
        try {
            routToStationMapping.setStationArrivalDate(LocalDateTime.parse(request.getParameter("station_arrival_date")));
            routToStationMapping.setStationDispatchData(LocalDateTime.parse(request.getParameter("station_dispatch_data")));
            if (!contains(mappingList, stationOrder)) {
                routToStationMapping.setOrder(stationOrder);
            } else {
                LOGGER.error("Incorrect data entered");
                throw new IncorrectDataException("Incorrect data entered");
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        routMappingValidator.isValidRoutToStationMapping(routToStationMapping, mappingList);
        routMappingService.addRoutToStationMapping(routToStationMapping);

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
        routMappingService = (RoutMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));

    }
}
