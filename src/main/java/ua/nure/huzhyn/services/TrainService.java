package ua.nure.huzhyn.services;

import ua.nure.huzhyn.model.entity.Train;

import java.util.List;

/**
 * Provides service which contains methods for provide information about {@link Train}
 *
 * @author Huzhyn Vadim
 * @version 1.0 *
 */
public interface TrainService {

    /**
     * Return list of {@link Train} by
     *
     * @return list of {@link Train} by
     */
    List<Train> getAllTrainList();

    /**
     * Add {@link Train}
     *
     * @param train {@link Train}
     */
    void addTrain(Train train);

    /**
     * Remove {@link Train} by train id
     *
     * @param trainId {@link Train} train id
     */
    void removeTrain(String trainId);

    /**
     * Update {@link Train}
     *
     * @param train {@link Train}
     */
    void updateTrain(Train train);

    /**
     * Return {@link Train} by id
     *
     * @param trainId {@link Train} train id
     * @return {@link Train} by id
     */
    Train getTrainById(String trainId);

}

