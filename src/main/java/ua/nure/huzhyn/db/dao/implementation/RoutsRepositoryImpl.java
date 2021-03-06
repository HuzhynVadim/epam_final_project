package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.RoutsRepository;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.dto.StationDto;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Rout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoutsRepositoryImpl implements RoutsRepository {

    private static final Logger LOGGER = Logger.getLogger(RoutsRepositoryImpl.class);
    private static final String ADD_ROUTS = "INSERT INTO final_project.railway_system.rout" +
            " (routs_id, train_id, rout_name, rout_number) VALUES (?,?,?,?)";
    private static final String GET_ROUT_BY_ID = "SELECT r.routs_id, r.train_id, r.rout_name, r.rout_number, " +
            "t.train_number FROM final_project.railway_system.rout as r " +
            "JOIN final_project.railway_system.train as t on r.train_id = t.train_id WHERE r.routs_id = ?";
    private static final String DELETE_ROUT = "DELETE FROM final_project.railway_system.rout WHERE routs_id = ?";
    private static final String GET_ALL_ROUT = "SELECT r.routs_id, r.train_id, r.rout_name, r.rout_number, t.train_number " +
            "FROM final_project.railway_system.rout as r JOIN final_project.railway_system.train as t on r.train_id = t.train_id " +
            "ORDER BY t.train_number, r.rout_name";
    private static final String GET_ROUTE_LIST_WITH_PARAMETERS = "SELECT rout_name, rout_number, r.routs_id, station, " +
            "s.station_id, train_number, r.train_id, station_arrival_date, station_dispatch_data, \"order\" " +
            "FROM final_project.railway_system.rout as r " +
            "JOIN final_project.railway_system.train as t on r.train_id = t.train_id " +
            "JOIN final_project.railway_system.rout_to_station_mapping as rm on rm.routs_id = r.routs_id " +
            "JOIN final_project.railway_system.station as s on rm.station_id = s.station_id " +
            "WHERE station IN (?, ?) " +
            "ORDER BY station_dispatch_data, r.rout_name , r.rout_number";
    private static final String UPDATE_ROUT = "UPDATE final_project.railway_system.rout SET rout_name = ?, " +
            "rout_number = ?, train_id = ? WHERE routs_id = ?";

    @Override
    public String create(Rout entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROUTS)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, entity.getTrainId());
            preparedStatement.setString(3, entity.getRoutName());
            preparedStatement.setString(4, entity.getRoutNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t create rout.", e);
        }
        return uid;
    }

    @Override
    public Rout read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Rout rout;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUT_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rout = extract(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t read rout. ID = " + id, e);
        }
        return rout;
    }

    @Override
    public boolean update(Rout entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ROUT)) {
            ps.setString(1, entity.getRoutName());
            ps.setString(2, entity.getRoutNumber());
            ps.setString(3, entity.getTrainId());
            ps.setString(4, entity.getRoutsId());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t update rout. ID = " + entity.getRoutsId(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROUT)) {
            preparedStatement.setString(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t delete routs. ID = " + id, e);
        }
        return result;
    }

    private Rout extract(ResultSet resultSet) {
        Rout rout = new Rout();
        try {
            if (!resultSet.next()) {
                return null;
            }
            rout.setRoutsId(resultSet.getString("routs_id"));
            rout.setTrainId(resultSet.getString("train_id"));
            rout.setRoutName(resultSet.getString("rout_name"));
            rout.setRoutNumber(resultSet.getString("rout_number"));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract Rout.", e);

        }
        return rout;
    }

    private RoutInfoDto extractRoutInfo(ResultSet resultSet) {
        RoutInfoDto result = new RoutInfoDto();
        try {
            result.setRoutsId(resultSet.getString("routs_id"));
            result.setTrainId(resultSet.getString("train_id"));
            result.setTrainNumber(resultSet.getString("train_number"));
            result.setRoutName(resultSet.getString("rout_name"));
            result.setRoutNumber(resultSet.getString("rout_number"));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract RoutInfoDto.", e);
        }
        return result;
    }

    private StationDto extractStationDto(ResultSet resultSet) {
        StationDto result = new StationDto();
        try {
            result.setStationId(resultSet.getString("station_id"));
            result.setStation(resultSet.getString("station"));
            result.setOrder(Integer.parseInt(resultSet.getString("order")));
            result.setStationArrivalDate(resultSet.getObject("station_arrival_date", LocalDateTime.class));
            result.setStationDispatchData(resultSet.getObject("station_dispatch_data", LocalDateTime.class));
            result.setRoutName(resultSet.getString("rout_name"));
            result.setRoutNumber(resultSet.getString("rout_number"));
            result.setRoutsId(resultSet.getString("routs_id"));
            result.setTrainId(resultSet.getString("train_id"));
            result.setTrainNumber(resultSet.getString("train_number"));
            return result;
        } catch (SQLException | NumberFormatException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can`t extract StationDto.", e);
        }
    }

    @Override
    public List<RoutInfoDto> getAllRoutList() {
        List<RoutInfoDto> routs = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ROUT)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                routs.add(extractRoutInfo(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get all rout list.", e);
        }
        return routs;
    }

    @Override
    public List<StationDto> getRouteListWithParameters(String departureStation, String arrivalStation) {
        List<StationDto> routs = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ROUTE_LIST_WITH_PARAMETERS)) {
            ps.setString(1, departureStation);
            ps.setString(2, arrivalStation);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                routs.add(extractStationDto(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get station " + departureStation + " and station " + arrivalStation + " in rout list", e);
        }
        return routs;
    }

    @Override
    public RoutInfoDto getRoutById(String routsId) {
        RoutInfoDto rout = new RoutInfoDto();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ROUT_BY_ID)) {
            ps.setString(1, routsId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rout = extractRoutInfo(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DataBaseException("Can't get rout by id. Id = " + routsId, e);
        }
        return rout;
    }
}