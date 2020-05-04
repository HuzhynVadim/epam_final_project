package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/car_delete")
public class CarDeleteController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CarDeleteController.class);

    private CarService carService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String carId = request.getParameter("car_id");
        carService.removeCar(carId);
        response.sendRedirect("administrator_account");
    }

    @Override
    public void init(ServletConfig config) {
        carService = (CarService) config.getServletContext().getAttribute(AppContextConstant.CARS_SERVICE);
        LOGGER.trace("car_delete Servlet init");

    }
}


