package ua.nure.huzhyn.db.dao;

public interface CRUD<EntityType, IDType> {

    IDType create(EntityType entity);

    EntityType read(IDType id);

    boolean update(EntityType entity);

    boolean delete(IDType entity);
}
