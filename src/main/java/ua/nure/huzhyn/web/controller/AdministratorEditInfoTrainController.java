package ua.nure.huzhyn.web.controller;

import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.TrainValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator_edit_info_train")
public class AdministratorEditInfoTrainController extends HttpServlet {
    private TrainService trainService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TrainValidator trainValidator = new TrainValidator();
        Train train = new Train();
        train.setTrainId(request.getParameter("train_id"));
        train.setTrainNumber(request.getParameter("train_number"));
        trainValidator.isValidTrain(train);
        trainService.updateTrain(train);
        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String trainId = request.getParameter("train_id");
        Train train = trainService.getTrainById(trainId);
        request.setAttribute("current_train", train);
        request.getRequestDispatcher("WEB-INF/jsp/administratorEditInfoTrain.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) {
        trainService = (TrainService) config.getServletContext().getAttribute(AppContextConstant.TRAIN_SERVICE);

    }
}


