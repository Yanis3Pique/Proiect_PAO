package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.Echipa;
import proiect.model.Jucator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JucatorDao implements DaoInterface<Jucator> {

    private static JucatorDao jucatorDao;
    private Connection connection = DatabaseConnection.getConnection();
    private JucatorDao() throws SQLException {}

    public static JucatorDao getInstance() throws SQLException {
        if(jucatorDao == null){
            jucatorDao = new JucatorDao();
        }
        return jucatorDao;
    }

    @Override
    public void create(Jucator jucator) throws SQLException {
        String sql = "INSERT INTO proiectpao.jucator VALUES (?, ?, ?, ?, ?, ?);";

        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, jucator.getId());
            statement.setString(2, jucator.getNume());
            statement.setString(3, jucator.getPrenume());
            statement.setInt(4, jucator.getVarsta());
            statement.setString(5, jucator.getPozitie());
            statement.setInt(6, jucator.getNumarTricou());
            statement.executeUpdate();
        }
    }

    @Override
    public Jucator read(String nume_prenume) throws SQLException {
        String nume = nume_prenume.split("_")[0];
        String prenume = nume_prenume.split("_")[1];

        String sql = "SELECT * FROM proiectpao.jucator j WHERE j.nume = ? AND j.prenume = ?";
        ResultSet rs = null;
        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, nume);
            statement.setString(2, prenume);
            rs = statement.executeQuery();

            while (rs.next()){
                Jucator jucator = new Jucator();
                jucator.setId(rs.getInt("id"));
                jucator.setNume(rs.getString("nume"));
                jucator.setPrenume(rs.getString("prenume"));
                jucator.setVarsta(rs.getInt("varsta"));
                jucator.setPozitie(rs.getString("pozitie"));
                jucator.setNumarTricou(rs.getInt("numarTricou"));
                return jucator;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public Jucator readByID(int id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.jucator j WHERE j.id = ?";
        ResultSet rs = null;
        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Jucator jucator = new Jucator();
                jucator.setId(rs.getInt("id"));
                jucator.setNume(rs.getString("nume"));
                jucator.setPrenume(rs.getString("prenume"));
                jucator.setVarsta(rs.getInt("varsta"));
                jucator.setPozitie(rs.getString("pozitie"));
                jucator.setNumarTricou(rs.getInt("numarTricou"));
                return jucator;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void update(String nume_prenume, Jucator updatedJucator) throws SQLException {
        String nume = nume_prenume.split("_")[0];
        String prenume = nume_prenume.split("_")[1];

        String sql = "UPDATE proiectpao.jucator j set j.nume = ? , j.prenume = ? , j.varsta = ? " +
                ", j.pozitie = ? , j.numarTricou = ? where j.nume = ? and j.prenume = ?";
        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, updatedJucator.getNume());
            statement.setString(2, updatedJucator.getPrenume());
            statement.setInt(3, updatedJucator.getVarsta());
            statement.setString(4, updatedJucator.getPozitie());
            statement.setInt(5, updatedJucator.getNumarTricou());
            statement.setString(6, nume);
            statement.setString(7, prenume);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Jucator jucator) throws SQLException {
        String sql = "DELETE FROM proiectpao.jucator j WHERE j.nume = ? AND j.prenume = ?";
        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, jucator.getNume());
            statement.setString(2, jucator.getPrenume());
            statement.executeUpdate();
        }
    }

    public List<Jucator> findAllJucator() throws SQLException {
        String sql = "SELECT * FROM proiectpao.jucator";
        ResultSet rs = null;
        List<Jucator> jucatori = new ArrayList<>();
        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            rs = statement.executeQuery();

            while (rs.next()){
                Jucator jucator = new Jucator();
                jucator.setId(rs.getInt("id"));
                jucator.setNume(rs.getString("nume"));
                jucator.setPrenume(rs.getString("prenume"));
                jucator.setVarsta(rs.getInt("varsta"));
                jucator.setPozitie(rs.getString("pozitie"));
                jucator.setNumarTricou(rs.getInt("numarTricou"));
                jucatori.add(jucator);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return jucatori;
    }
}