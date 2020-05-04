package ua.nure.huzhyn.db.dao;

/**
 * @param <EntityType> the entity of which will be operated
 * @param <IDType>     input parameter type
 * @author Huzhyn Vadim
 * @version 1.0
 * @apiNote Use this interface for extending in your DAO interfaces to implements standard CRUD operations
 */
public interface CRUD<EntityType, IDType> {

    /**
     * Create entity in db
     *
     * @param entity which necessary create
     * @return auto generated uid by server
     */
    IDType create(EntityType entity);

    /**
     * Return entity from db by id
     *
     * @param id input parameter type
     * @return entity by id
     */
    EntityType read(IDType id);

    /**
     * Update entity in db by id
     *
     * @param entity which necessary update
     * @return true if update successful and false otherwise
     */
    boolean update(EntityType entity);

    /**
     * Delete entity in db
     *
     * @param entity which necessary delete
     * @return true if delete successful and false otherwise
     */
    boolean delete(IDType entity);
}
