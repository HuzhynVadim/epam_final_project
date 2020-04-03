package ua.nure.huzhyn.web.controller;


import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Rout;
import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.RoutValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/administrator_add_rout")
public class AdministratorAddRoutController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorAddRoutController.class);
    private RoutService routService;
    private TrainService trainService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoutValidator routValidator = new RoutValidator();
        Rout rout = new Rout();
        try {
            rout.setRoutName(request.getParameter("rout_name"));
            rout.setRoutNumber(request.getParameter("rout_number"));
            rout.setTrainId(request.getParameter("train_number"));
            routValidator.isValidRout(rout);
            routService.addRout(rout);
        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Train> trainList = trainService.getAllTrain();
        request.setAttribute("trainList", trainList);
        request.getRequestDispatcher("WEB-INF/jsp/administratorAddRout.jsp").forward(request, response);
    }

    public void init(ServletConfig config) {
        routService = (RoutService) config.getServletContext().getAttribute(AppContextConstant.ROUT_SERVICE);
        trainService = (TrainService) config.getServletContext().getAttribute(AppContextConstant.TRAIN_SERVICE);

    }
}
