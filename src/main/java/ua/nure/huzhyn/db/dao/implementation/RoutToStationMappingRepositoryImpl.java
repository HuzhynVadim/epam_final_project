package ua.nure.huzhyn.db.dao.implementation;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.db.dao.RoutToStationMappingRepository;
import ua.nure.huzhyn.db.dao.transaction.ConnectionManager;
import ua.nure.huzhyn.exception.DBException;
import ua.nure.huzhyn.model.entity.RoutToStationMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RoutToStationMappingRepositoryImpl implements RoutToStationMappingRepository {
    private static final Logger LOGGER = Logger.getLogger(RoutsRepositoryImpl.class);
    private static final String ADD_ROUT_MAPPINGS = "INSERT INTO final_project.railway_system.rout_to_station_mapping as rm (routs_m_id, station_arrival_date, station_dispatch_data, station_id, routs_id, \"order\") VALUES (? , ?, ?, ?, ?, ?)";
    private static final String GET_ROUT_MAPPING_BY_ID = "SELECT * FROM final_project.railway_system.rout_to_station_mapping WHERE routs_m_id = ?";
    private static final String UPDATE_ROUT_MAPPING = "UPDATE final_project.railway_system.rout_to_station_mapping SET station_id = ?, routs_id = ?, station_arrival_date = ?, station_dispatch_data = ?, \"order\" = ? WHERE routs_m_id = ?";
    private static final String DELETE_ROUT_MAPPING = "DELETE FROM final_project.railway_system.rout_to_station_mapping WHERE routs_m_id = ?";


    @Override
    public String create(RoutToStationMapping entity) {
        Connection connection = ConnectionManager.getConnection();
        String uid = UUID.randomUUID().toString();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROUT_MAPPINGS)) {
            preparedStatement.setString(1, uid);
            preparedStatement.setObject(2, entity.getStationArrivalDate());
            preparedStatement.setObject(3, entity.getStationDispatchData());
            preparedStatement.setString(4, entity.getStationId());
            preparedStatement.setString(5, entity.getRoutsId());
            preparedStatement.setString(6, entity.getOrder());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("", e);
        }
        return uid;
    }

    @Override
    public Optional<RoutToStationMapping> read(String id) {
        Connection connection = ConnectionManager.getConnection();
        Optional<RoutToStationMapping> routToStationMapping;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUT_MAPPING_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            routToStationMapping = Optional.ofNullable(extract(rs));
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
            ps.setString(6, entity.getRoutsMId());
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
    public boolean delete(String id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROUT_MAPPING)) {
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

    private RoutToStationMapping extract(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        RoutToStationMapping routToStationMapping = new RoutToStationMapping();

        routToStationMapping.setRoutsMId(resultSet.getString("routs_m_id"));
        routToStationMapping.setStationId(resultSet.getString("station_id"));
        routToStationMapping.setRoutsId(resultSet.getString("rout_id"));
        routToStationMapping.setStationArrivalDate(resultSet.getObject("station_arrival_date", LocalDateTime.class));
        routToStationMapping.setStationDispatchData(resultSet.getObject("station_dispatch_date", LocalDateTime.class));
        routToStationMapping.setOrder(resultSet.getString("order"));
        return routToStationMapping;
    }
}
