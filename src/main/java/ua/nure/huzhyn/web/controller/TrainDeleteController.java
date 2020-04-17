package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/train_delete")
public class TrainDeleteController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TrainDeleteController.class);
    private TrainService trainService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String trainId = request.getParameter("train_id");
            trainService.removeTrain(trainId);
        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect train ID. train ID = " + request.getParameter("trainId"));
            throw new IncorrectDataException("Incorrect train ID", e);
        }
        response.sendRedirect("administrator_account");
    }

    @Override
    public void init(ServletConfig config) {
        trainService = (TrainService) config.getServletContext().getAttribute(AppContextConstant.TRAIN_SERVICE);

    }
}