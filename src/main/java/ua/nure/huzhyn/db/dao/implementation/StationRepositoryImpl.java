package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.StationRepository;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DBException;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StationRepositoryImpl implements StationRepository {
    private static final Logger LOGGER = Logger.getLogger(StationRepositoryImpl.class);
    private static final String ADD_STATION = "INSERT INTO final_project.railway_system.station (station_id, station) VALUES (?,?)";
    private static final String GET_STATION_BY_ID = "SELECT * FROM final_project.railway_system.station WHERE station_id = ?";
    private static final String DELETE_STATION = "DELETE FROM final_project.railway_system.station WHERE  station_id = ?";
    private static final String UPDATE_STATION = "UPDATE final_project.railway_system.station SET station = ? WHERE station_id = ?";
    private static final String GET_ALL_STATION = "SELECT * FROM final_project.railway_system.station";

    @Override
    public String create(Station entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_STATION)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, entity.getStation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }

        return uid;
    }

    @Override
    public Optional<Station> read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Optional<Station> station;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STATION_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            station = Optional.of(extract(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }
        return station;
    }

    @Override
    public boolean update(Station entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_STATION)) {
            ps.setString(1, entity.getStation());
            ps.setString(2, entity.getStationId());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Update station " + entity.getStationId(), e);
        }
        return result;

    }


    @Override
    public boolean delete(String id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATION)) {
            preparedStatement.setString(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Delete station " + id, e);
        }
        return result;
    }

    private Station extract(ResultSet rs) throws SQLException {

        Station station = new Station();
        station.setStationId(rs.getString("station_id"));
        station.setStation(rs.getString("station"));
        return station;
    }

    @Override
    public List<Station> getAllStationList() {
        List<Station> Station = new ArrayList<Station>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_STATION)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Station.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            String message = String.format("Can't get station");
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return Station;
    }

    @Override
    public Station getStationById(String stationId) {
        Station station = new Station();
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_STATION_BY_ID)) {
            ps.setString(1, stationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                station = extract(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            String message = String.format("Can't get station by id. Id = %s", stationId);
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return station;
    }
}
