package ua.nure.huzhyn.db.dao.transaction;

@FunctionalInterface
public interface Transaction<T> {

    T execute();
}
