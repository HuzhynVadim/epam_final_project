package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.RoutsRepository;
import ua.nure.huzhyn.db.dao.dto.RoutInfoDto;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DBException;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Rout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoutsRepositoryImpl implements RoutsRepository {

    private static final Logger LOGGER = Logger.getLogger(RoutsRepositoryImpl.class);
    private static final String ADD_ROUTS = "INSERT INTO final_project.railway_system.rout (routs_id, train_id, rout_name, rout_number) VALUES (?,?,?,?)";
    private static final String GET_ROUT_BY_ID = "SELECT r.routs_id, r.train_id, r.rout_name, r.rout_number, t.train_number FROM final_project.railway_system.rout as r JOIN final_project.railway_system.train as t on r.train_id = t.train_id WHERE r.routs_id = ?";
    private static final String DELETE_ROUT = "DELETE FROM final_project.railway_system.rout WHERE routs_id = ?";
    private static final String GET_ALL_ROUT = "SELECT r.routs_id, r.train_id, r.rout_name, r.rout_number, t.train_number FROM final_project.railway_system.rout as r JOIN final_project.railway_system.train as t on r.train_id = t.train_id";
    private static final String UPDATE_ROUT = "UPDATE final_project.railway_system.rout SET rout_name = ?, rout_number = ?, train_id = ? WHERE routs_id = ?";

//    private static final String GET_ROUT_BY_ID = "SELECT * FROM final_project.railway_system.rout as r JOIN final_project.railway_system.rout_to_station_mapping as rm on r.routs_id = rm.routs_id WHERE r.routs_id = ?";


    @Override
    public String create(Rout entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROUTS)) {
            preparedStatement.setString(1, uid);
//            preparedStatement.setObject(2, entity.getroutDispatchDate());
//            preparedStatement.setObject(3, entity.getroutArrivalDate());
            preparedStatement.setString(2, entity.getTrainId());
            preparedStatement.setString(3, entity.getRoutName());
            preparedStatement.setString(4, entity.getRoutNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }

//        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROUT_MAPPINGS)) {
//
//            for (Station station : entity.getStations()) {
//                preparedStatement.clearParameters();
//                preparedStatement.setObject(1, station.getArrivalDate());
//                preparedStatement.setObject(2, station.getDispatchDate());
//                preparedStatement.setObject(3, station.getStationId());
//                preparedStatement.setObject(4, uid);
//                preparedStatement.setObject(5, station.getOrder());
//                preparedStatement.addBatch();
//            }
//
//            preparedStatement.executeBatch();
//        } catch (SQLException e) {
//            LOGGER.error(e);
//            throw new DBException("", e);
//        }

        return uid;
    }

    @Override
    public Optional<Rout> read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Optional<Rout> rout;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUT_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rout = Optional.ofNullable(extract(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
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
            throw new DBException("Update station " + entity.getRoutsId(), e);
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
            throw new DBException("Delete routs " + id, e);
        }
        return result;
    }


    private Rout extract(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Rout rout = new Rout();
        rout.setRoutsId(resultSet.getString("routs_id"));
//        rout.setroutArrivalDate(resultSet.getObject("rout_arrival_date", LocalDateTime.class));
//        rout.setroutDispatchDate(resultSet.getObject("rout_dispatch_date", LocalDateTime.class));
        rout.setTrainId(resultSet.getString("train_id"));
        rout.setRoutName(resultSet.getString("rout_name"));
        rout.setRoutNumber(resultSet.getString("rout_number"));

//        List<Station> stations = new ArrayList<>();
//        stations.add(extractStation(resultSet));
//
//        while (resultSet.next()) {
//            stations.add(extractStation(resultSet));
//        }
//
//        rout.setStations(stations);

        return rout;
    }

    private RoutInfoDto extractRoutInfo(ResultSet resultSet) throws SQLException {
        RoutInfoDto result = new RoutInfoDto();
        result.setRoutsId(resultSet.getString("routs_id"));
        result.setTrainId(resultSet.getString("train_id"));
        result.setTrainNumber(resultSet.getString("train_number"));
        result.setRoutName(resultSet.getString("rout_name"));
        result.setRoutNumber(resultSet.getString("rout_number"));

        return result;
    }


//    private Station extractStation(ResultSet resultSet) throws SQLException {
//
//        Station station = new Station();
//        station.setArrivalDate(resultSet.getObject("station_arrival_date", LocalDateTime.class));
//        station.setDispatchDate(resultSet.getObject("station_dispatch_date", LocalDateTime.class));
//        station.setOrder(resultSet.getInt("order"));
//        station.setStation(resultSet.getString("station"));
//        station.setStationId(resultSet.getString("station_id"));
//        return station;
//    }

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
            String message = "Can't get rout";
            LOGGER.error(message, e);
            throw new DataBaseException(message);
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
            String message = String.format("Can't get rout by id. Id = %s", routsId);
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return rout;
    }
}
