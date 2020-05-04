package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.dto.CarDto;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.CarValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@WebServlet("/administrator_add_car")
public class AdministratorAddCarController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorAddCarController.class);
    private CarService carService;
    private TrainService trainService;

    public static boolean containsCarWithCarNumber(final List<Car> array, final String carNumber) {

        boolean result = false;

        for (Car car : array) {
            if (car.getCarNumber().equals(carNumber)) {
                result = true;
                break;
            }
        }
        return result;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CarValidator carValidator = new CarValidator();
        CarDto carDto = new CarDto();
        String trainId = request.getParameter("train_id");
        String trainNotSelected = trainId.equals("TRAIN_NOT_SELECTED") ? null : trainId;
        carDto.setTrainId(trainNotSelected);
        Train train = trainService.getTrainById(trainId);
        List<Car> carByTrainId = carService.getCarByTrainId(train.getTrainId());
        String carNumber = request.getParameter("car_number");

        if (train.getTrainId() == null || !containsCarWithCarNumber(carByTrainId, carNumber) && trainId.equals(train.getTrainId())) {
            carDto.setCarNumber(carNumber);
        } else {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered");
        }
        try {
            carDto.setCarType(CarType.valueOf(request.getParameter("car_type")));
            carDto.setSeats(Integer.valueOf(request.getParameter("seats")));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        carValidator.isValidCar(carDto);
        carService.addCar(carDto);
        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Train> trainList = trainService.getAllTrainList();
        request.setAttribute("trainList", trainList);
        List<CarType> carTypeList = new ArrayList<>(EnumSet.allOf(CarType.class));
        request.setAttribute("carTypeList", carTypeList);

        request.getRequestDispatcher("WEB-INF/jsp/administratorAddCar.jsp").forward(request, response);
    }

    public void init(ServletConfig config) {
        trainService = (TrainService) config.getServletContext().getAttribute(AppContextConstant.TRAIN_SERVICE);
        carService = (CarService) config.getServletContext().getAttribute(AppContextConstant.CARS_SERVICE);
        LOGGER.trace("administrator_add_car Servlet init");

    }
}

