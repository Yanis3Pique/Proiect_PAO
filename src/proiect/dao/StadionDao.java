package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.Echipa;
import proiect.model.Stadion;
import proiect.utils.InvalidDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StadionDao implements DaoInterface<Stadion> {
    private static StadionDao stadionDao;

    private Connection connection = DatabaseConnection.getConnection();

    public StadionDao() throws SQLException {}

    public static StadionDao getInstance() throws SQLException {
        if(stadionDao == null){
            stadionDao = new StadionDao();
        }
        return stadionDao;
    }

    @Override
    public void create(Stadion stadion) throws SQLException {
        String sql = "INSERT INTO proiectpao.stadion VALUES (?, ?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, stadion.getId());
            statement.setString(2, stadion.getNume());
            statement.setInt(3, stadion.getCapacitate());
            statement.setString(4, stadion.getLocatie());
            statement.executeUpdate();
        }
    }

    @Override
    public Stadion read(String nume) throws SQLException {
        String sql = "SELECT * FROM proiectpao.stadion s WHERE s.nume = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            rs = statement.executeQuery();

            while (rs.next()){
                Stadion stadion = new Stadion();
                stadion.setId(rs.getInt("id"));
                stadion.setNume(rs.getString("nume"));
                stadion.setCapacitate(rs.getInt("capacitate"));
                stadion.setLocatie(rs.getString("locatie"));
                return  stadion;
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public Stadion readByID(int id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.stadion s WHERE s.id = ?";
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Stadion stadion = new Stadion();
                stadion.setId(rs.getInt("id"));
                stadion.setNume(rs.getString("nume"));
                stadion.setCapacitate(rs.getInt("capacitate"));
                stadion.setLocatie(rs.getString("locatie"));
                return  stadion;
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void update(String nume, Stadion updatedStadion) throws SQLException {
        String sql = "UPDATE proiectpao.stadion set nume = ? , capacitate = ? , locatie = ? where nume = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, updatedStadion.getNume());
            statement.setInt(2, updatedStadion.getCapacitate());
            statement.setString(3, updatedStadion.getLocatie());
            statement.setString(4, nume);
            statement.executeUpdate();

            updateEchipaBasedOnStadion(nume, updatedStadion);
        }
    }

    private void updateEchipaBasedOnStadion(String nume, Stadion updatedStadion) {
        EchipaDao echipaDao = new EchipaDao();
        for(Echipa echipa : echipaDao.findAllEchipa()){
            if(echipa.getStadion() != null && echipa.getStadion().getNume().equals(nume)){
                echipa.getStadion().setNume(updatedStadion.getNume());
                echipa.getStadion().setCapacitate(updatedStadion.getCapacitate());
                echipa.getStadion().setLocatie(updatedStadion.getLocatie());
            }
        }
    }

    @Override
    public void delete(Stadion stadion) throws SQLException {
        String sql = "DELETE FROM proiectpao.stadion s WHERE s.nume = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, stadion.getNume());
            statement.executeUpdate();
        }
    }

    public List<Stadion> findAllStadion() throws SQLException {
        String sql = "SELECT * FROM proiectpao.stadion";
        ResultSet rs = null;
        List<Stadion> stadions = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while(rs.next()) {
                Stadion stadion = new Stadion(rs.getInt("id"), rs.getString("nume"), rs.getInt("capacitate"), rs.getString("locatie"));
                stadions.add(stadion);
            }

        }finally {
            if(rs != null) {
                rs.close();
            }
            else stadions = null;
        }

        return stadions;
    }

    public boolean checkUniqueName(String name) throws SQLException, InvalidDataException {
        String sql = "SELECT * FROM proiectpao.stadion s WHERE s.nume = ?";
        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, name);
            rs = statement.executeQuery();
            if(rs.getRow() == 0) {
                return true;
            }
        }
        return false;
    }
}