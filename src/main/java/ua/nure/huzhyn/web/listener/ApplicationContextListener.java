package ua.nure.huzhyn.web.listener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.huzhyn.db.dao.*;
import ua.nure.huzhyn.db.dao.implementation.*;
import ua.nure.huzhyn.db.dao.transaction.TransactionManager;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.services.*;
import ua.nure.huzhyn.services.implementation.*;
import ua.nure.huzhyn.util.constants.AppContextConstant;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    private final static Logger LOGGER = Logger.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initLog4j(sce.getServletContext());
        TransactionManager transactionManager = new TransactionManager(getDataSource());


        UserRepository userRepository = new UserRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        StationRepository stationRepository = new StationRepositoryImpl();
        RoutsRepository routsRepository = new RoutsRepositoryImpl();
        TrainRepository trainRepository = new TrainRepositoryImpl();
        CarRepository carRepository = new CarRepositoryImpl();
        RoutMappingRepository routMappingRepository = new RoutMappingRepositoryImpl();


        CarService carService = new CarServiceImpl(carRepository, transactionManager);
        UserService userService = new UserServiceImpl(userRepository, transactionManager);
        RoutService routService = new RoutServiceImpl(routsRepository, carService, transactionManager);
        OrderService orderService = new OrderServiceImpl(orderRepository, transactionManager, routsRepository);
        StationService stationService = new StationServiceImpl(stationRepository, transactionManager);
        TrainService trainService = new TrainServiceImpl(trainRepository, transactionManager);
        RoutMappingService routMappingService = new RoutMappingServiceImpl(routMappingRepository, transactionManager);
        LogoutService logoutService = new LogoutService();

        sce.getServletContext().setAttribute(AppContextConstant.USER_SERVICE, userService);
        sce.getServletContext().setAttribute(AppContextConstant.ORDER_SERVICE, orderService);
        sce.getServletContext().setAttribute(AppContextConstant.STATION_SERVICE, stationService);
        sce.getServletContext().setAttribute(AppContextConstant.ROUT_SERVICE, routService);
        sce.getServletContext().setAttribute(AppContextConstant.TRAIN_SERVICE, trainService);
        sce.getServletContext().setAttribute(AppContextConstant.CARS_SERVICE, carService);
        sce.getServletContext().setAttribute(AppContextConstant.ROUT_TO_STATION_MAPPING_SERVICE, routMappingService);
        sce.getServletContext().setAttribute(AppContextConstant.LOGOUT_SERVICE, logoutService);

    }

    private DataSource getDataSource() {
        DataSource ds;
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/railway_system");
        } catch (NamingException e) {
            String message = "Cannot initialize connection pool";
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return ds;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void initLog4j(ServletContext sc) {
        System.out.println("Log4JInitServlet is initializing log4j");
        String log4jLocation = sc.getInitParameter("log4j-properties-location");
        System.out.println(log4jLocation);

        if (log4jLocation == null) {
            System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        } else {
            String log4jProp = sc.getRealPath("WEB-INF/log4j.properties");
            File properties = new File(log4jProp);
            if (properties.exists()) {
                System.out.println("Initializing log4j with: " + log4jProp);
                PropertyConfigurator.configure(log4jProp);
            } else {
                System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
                BasicConfigurator.configure();
            }
        }
    }
}
