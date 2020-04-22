package ua.nure.huzhyn.web.controller;

import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.services.RoutToStationMappingService;
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

    private RoutToStationMappingService routToStationMappingService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routsId = request.getParameter("routs_id");
        String stationId = request.getParameter("station_id");
        request.setAttribute("station_id", stationId);
        request.setAttribute("routs_id", routsId);
        List<MappingInfoDto> AllRoutToStationMappingListById = routToStationMappingService.getAllRoutToStationMappingListById(routsId);
        request.setAttribute("rout_m_list", AllRoutToStationMappingListById);
        request.getRequestDispatcher("WEB-INF/jsp/administratorDetailsSetRout.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {

        routToStationMappingService = (RoutToStationMappingService) config.getServletContext().getAttribute((AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE));

    }
}
