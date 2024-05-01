package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.Antrenor;
import proiect.model.Echipa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AntrenorDao implements DaoInterface<Antrenor> {

    private static AntrenorDao antrenorDao;
    private Connection connection = DatabaseConnection.getConnection();
    private AntrenorDao() throws SQLException {}
    private static int nextId = 1;

    public static AntrenorDao getInstance() throws SQLException {
        if(antrenorDao == null){
            antrenorDao = new AntrenorDao();
        }
        return antrenorDao;
    }

    @Override
    public void create(Antrenor antrenor) {
        antrenor.setId(nextId++);
        String sql = "INSERT INTO proiectpao.antrenor VALUES (?, ?, ?, ?, ?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, antrenor.getId());
            statement.setString(2, antrenor.getNume());
            statement.setString(3, antrenor.getPrenume());
            statement.setInt(4, antrenor.getVarsta());
            statement.setDouble(5, antrenor.getSalariu());
            statement.setInt(6, antrenor.getAniExperienta());
            statement.setString(7, antrenor.getNationalitate());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Antrenor could not be created " + e.getSQLState() + " " + e.getMessage());
        }
    }

    @Override
    public Antrenor read(String nume_prenume) {
        String nume = nume_prenume.split("_")[0];
        String prenume = nume_prenume.split("_")[1];

        String sql = "SELECT * FROM proiectpao.antrenor s WHERE s.nume = ? AND s.prenume = ?";
        ResultSet result = null;

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, nume);
            statement.setString(2, prenume);
            result = statement.executeQuery();

            while (result.next()){
                Antrenor antrenor = new Antrenor();
                antrenor.setId(result.getInt("id"));
                antrenor.setNume(result.getString("nume"));
                antrenor.setPrenume(result.getString("prenume"));
                antrenor.setVarsta(result.getInt("varsta"));
                antrenor.setSalariu(result.getDouble("salariu"));
                antrenor.setAniExperienta(result.getInt("aniExperienta"));
                antrenor.setNationalitate(result.getString("nationalitate"));
                return antrenor;
            }
        } catch (SQLException e) {
            System.out.println("Antrenor could not be read " + e.getSQLState() + " " + e.getMessage());
        } finally {
            if(result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    System.out.println("Result could not be closed " + e.getSQLState() + " " + e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public void update(String nume_prenume, Antrenor antrenorUpdated) {
        String nume = nume_prenume.split("_")[0];
        String prenume = nume_prenume.split("_")[1];

        String sql = "UPDATE proiectpao.antrenor SET id = ?, nume = ?, prenume = ?, varsta = ?, " +
                "salariu = ?, aniExperienta = ?, nationalitate = ? WHERE nume = ? AND prenume = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, antrenorUpdated.getId());
            statement.setString(2, antrenorUpdated.getNume());
            statement.setString(3, antrenorUpdated.getPrenume());
            statement.setInt(4, antrenorUpdated.getVarsta());
            statement.setDouble(5, antrenorUpdated.getSalariu());
            statement.setInt(6, antrenorUpdated.getAniExperienta());
            statement.setString(7, antrenorUpdated.getNationalitate());
            statement.setString(8, nume);
            statement.setString(9, prenume);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Antrenor could not be updated " + e.getSQLState() + " " + e.getMessage());
        }
    }

    @Override
    public void delete(Antrenor antrenor) {
        String sql = "DELETE FROM proiectpao.antrenor s WHERE s.nume = ? AND s.prenume = ?;";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, antrenor.getNume());
            statement.setString(2, antrenor.getPrenume());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Antrenor could not be deleted " + e.getSQLState() + " " + e.getMessage());
        }
    }
}
