package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.RoutToStationMappingRepository;
import ua.nure.huzhyn.db.dao.dto.MappingInfoDto;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DBException;
import ua.nure.huzhyn.exception.DataBaseException;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoutToStationMappingRepositoryImpl implements RoutToStationMappingRepository {
    private static final Logger LOGGER = Logger.getLogger(RoutsRepositoryImpl.class);
    private static final String ADD_ROUT_MAPPINGS = "INSERT INTO final_project.railway_system.rout_to_station_mapping as rm (station_id, routs_id, station_arrival_date, station_dispatch_data, \"order\") VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ROUT_MAPPING_BY_ID = "SELECT * FROM final_project.railway_system.rout_to_station_mapping WHERE routs_id = ?";
    private static final String UPDATE_ROUT_MAPPING = "UPDATE final_project.railway_system.rout_to_station_mapping SET station_id = ?, station_arrival_date = ?, station_dispatch_data = ?, \"order\" = ? WHERE routs_id = ?";
    private static final String DELETE_ROUT_MAPPING = "DELETE FROM final_project.railway_system.rout_to_station_mapping WHERE routs_id = ? AND station_id  = ?";
    private static final String GET_ALL_ROUT_MAPPING = "SELECT * FROM final_project.railway_system.rout_to_station_mapping";
    private static final String GET_ROUT_MAPPING_BY_ROUT_ID = "SELECT * FROM final_project.railway_system.rout_to_station_mapping as rm JOIN final_project.railway_system.station as s ON rm.station_id = s.station_id WHERE routs_id = ? ORDER BY \"order\" ASC";


    @Override
    public void create(RoutToStationMapping entity) {
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROUT_MAPPINGS)) {
            preparedStatement.setString(1, entity.getStationId());
            preparedStatement.setString(2, entity.getRoutsId());
            preparedStatement.setObject(3, entity.getStationArrivalDate());
            preparedStatement.setObject(4, entity.getStationDispatchData());
            preparedStatement.setString(5, entity.getOrder());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }
    }

    @Override
    public Optional<RoutToStationMapping> read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Optional<RoutToStationMapping> routToStationMapping;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUT_MAPPING_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            routToStationMapping = Optional.of(extract(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }
        return routToStationMapping;
    }

    @Override
    public boolean update(RoutToStationMapping entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ROUT_MAPPING)) {
            ps.setString(1, entity.getStationId());
            ps.setString(2, entity.getRoutsId());
            ps.setObject(3, entity.getStationArrivalDate());
            ps.setObject(4, entity.getStationDispatchData());
            ps.setString(5, entity.getOrder());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Update 2 " + entity.getStationId(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String routsId, String stationId) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROUT_MAPPING)) {
            preparedStatement.setString(1, routsId);
            preparedStatement.setString(2, stationId);

            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Delete station " + routsId + stationId, e);
        }
        return result;
    }

    private RoutToStationMapping extract(ResultSet resultSet) throws SQLException {
//        if (!resultSet.next()) {
//            return null;
//        }
        RoutToStationMapping routToStationMapping = new RoutToStationMapping();

        routToStationMapping.setStationId(resultSet.getString("station_id"));
        routToStationMapping.setRoutsId(resultSet.getString("routs_id"));
        routToStationMapping.setStationArrivalDate(resultSet.getObject("station_arrival_date", LocalDateTime.class));
        routToStationMapping.setStationDispatchData(resultSet.getObject("station_dispatch_data", LocalDateTime.class));
        routToStationMapping.setOrder(resultSet.getString("order"));
        return routToStationMapping;
    }

    @Override
    public List<MappingInfoDto> getAllRoutToStationMappingListById(String routsId) {
        List<MappingInfoDto> routs = new ArrayList<MappingInfoDto>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ROUT_MAPPING_BY_ROUT_ID)) {
            ps.setString(1, routsId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                routs.add(extractStationInfo(rs));
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
    public List<RoutToStationMapping> getAllRoutToStationMappingList() {
        List<RoutToStationMapping> routs = new ArrayList<RoutToStationMapping>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ROUT_MAPPING)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                routs.add(extract(rs));
            }
            connection.commit();
        } catch (SQLException e) {
            String message = "Can't get rout";
            LOGGER.error(message, e);
            throw new DataBaseException(message);
        }
        return routs;
    }

    private MappingInfoDto extractStationInfo(ResultSet resultSet) throws SQLException {
        MappingInfoDto result = new MappingInfoDto();
        result.setStationId(resultSet.getString("station_id"));
        result.setRoutsId(resultSet.getString("routs_id"));
        result.setStationArrivalDate(resultSet.getObject("station_arrival_date", LocalDateTime.class));
        result.setStationDispatchData(resultSet.getObject("station_dispatch_data", LocalDateTime.class));
        result.setStation(resultSet.getString("station"));
        result.setOrder(resultSet.getString("order"));

        return result;
    }


}
