package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Station;
import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.services.StationService;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.StationValidator;
import ua.nure.huzhyn.validator.TrainValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator_add_train")
public class AdministratorAddTrainController extends HttpServlet {
        private static final Logger LOGGER = Logger.getLogger(AdministratorAddTrainController.class);
        private TrainService trainService;

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            TrainValidator trainValidator = new TrainValidator();
            Train train = new Train();
            try {
                train.setTrainNumber(request.getParameter("train_number"));
                trainValidator.isValidTrain(train);
                trainService.addTrain(train);
            } catch (NumberFormatException e) {
                LOGGER.error("Incorrect data entered");
                throw new IncorrectDataException("Incorrect data entered", e);
            }
            response.sendRedirect("administrator_account");
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("WEB-INF/jsp/administratorAddTrain.jsp").forward(request, response);
        }

        public void init(ServletConfig config) {
            trainService = (TrainService) config.getServletContext().getAttribute(AppContextConstant.TRAIN_SERVICE);

        }
    }
