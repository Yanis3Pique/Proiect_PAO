package proiect.dao;

import java.sql.SQLException;

// interfata generica; T este tipul obiectului
// T se mai numeste type erasure
// la compilare T va fi inlocuit cu tipul real
public interface DaoInterface <T>{
    public void create(T entity)  throws SQLException;

    public T read(String name) throws SQLException;

    public void update(String name, T entity) throws SQLException;

    public void delete(T entity) throws SQLException;
}
