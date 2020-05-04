package ua.nure.huzhyn.db.dao;

import ua.nure.huzhyn.model.entity.Train;

import java.util.List;

/**
 * DAO for the {@link Train} objects.
 * Besides the basic CRUD methods it provides methods gets a list of all trains, gets a specific train by identifier
 *
 * @author Huzhyn Vadim
 * @version 1.0
 */
public interface TrainRepository extends CRUD<Train, String> {

    /**
     * Return list of {@link Train} by
     *
     * @return list of {@link Train} by
     */
    List<Train> getAllTrainList();

    /**
     * Return {@link Train} by id
     *
     * @param trainId {@link Train} train id
     * @return {@link Train} by id
     */
    Train getTrainById(String trainId);

}
