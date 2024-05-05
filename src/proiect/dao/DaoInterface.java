package proiect.dao;

import java.sql.SQLException;

public interface DaoInterface <T>{
    public void create(T entity)  throws SQLException;

    public T read(String name) throws SQLException;

    public T readByID(int id) throws SQLException;

    public void update(String name, T entity) throws SQLException;

    public void delete(T entity) throws SQLException;
}
