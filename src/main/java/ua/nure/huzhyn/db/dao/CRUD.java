package ua.nure.huzhyn.db.dao;

import java.util.Optional;

public interface CRUD<EntityType, IDType> {

    String create(EntityType entity);

    Optional<EntityType> read(IDType id);

    boolean update(EntityType entity);

    boolean delete(IDType entity);
}
