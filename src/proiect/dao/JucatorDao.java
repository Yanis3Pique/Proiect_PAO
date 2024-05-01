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
    private static int nextId = 1;

    public static JucatorDao getInstance() throws SQLException {
        if(jucatorDao == null){
            jucatorDao = new JucatorDao();
        }
        return jucatorDao;
    }

    @Override
    public void create(Jucator jucator) {
        jucator.setId(nextId++);
        String sql = "INSERT INTO proiectpao.jucator VALUES (?, ?, ?, ?, ?, ?);";

        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, jucator.getId());
            statement.setString(2, jucator.getNume());
            statement.setString(3, jucator.getPrenume());
            statement.setInt(4, jucator.getVarsta());
            statement.setString(5, jucator.getPozitie());
            statement.setInt(6, jucator.getNumarTricou());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Jucator read(String nume_prenume) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(String nume_prenume, Jucator updatedJucator) {
        String nume = nume_prenume.split("_")[0];
        String prenume = nume_prenume.split("_")[1];

        String sql = "UPDATE proiectpao.jucator j set j.nume = ? , j.prenume = ? , j.varsta = ? " +
                ", j.pozitie = ? , j.numarTricou = ? where j.nume = ? and j.prenume = ?";
        try(java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, updatedJucator.getNume());
            preparedStatement.setString(2, updatedJucator.getPrenume());
            preparedStatement.setInt(3, updatedJucator.getVarsta());
            preparedStatement.setString(4, updatedJucator.getPozitie());
            preparedStatement.setInt(5, updatedJucator.getNumarTricou());
            preparedStatement.setString(6, nume);
            preparedStatement.setString(7, prenume);
            preparedStatement.executeUpdate();

            updatEchipaBasedOnJucator(nume, prenume, updatedJucator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatEchipaBasedOnJucator(String nume, String prenume, Jucator updatedJucator) {
        for(Echipa echipa : new EchipaDao().findAllEchipa()) {
            for(Jucator jucatorEchipa : echipa.getJucatori()) {
                if(jucatorEchipa.getNume().equals(nume) && jucatorEchipa.getPrenume().equals(prenume)) {
                    jucatorEchipa.setNume(updatedJucator.getNume());
                    jucatorEchipa.setPrenume(updatedJucator.getPrenume());
                    jucatorEchipa.setVarsta(updatedJucator.getVarsta());
                    jucatorEchipa.setPozitie(updatedJucator.getPozitie());
                    jucatorEchipa.setNumarTricou(updatedJucator.getNumarTricou());
                }
            }
        }
    }

    @Override
    public void delete(Jucator jucator) {
        String sql = "DELETE FROM proiectpao.jucator j WHERE j.nume = ? AND j.prenume = ?";
        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, jucator.getNume());
            statement.setString(2, jucator.getPrenume());
            statement.executeUpdate();

            removeJucatorFromEchipa(jucator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeJucatorFromEchipa(Jucator jucator) {
        for(Echipa echipa : new EchipaDao().findAllEchipa()) {
            for(Jucator jucatorEchipa : echipa.getJucatori()) {
                if(jucatorEchipa.getNume().equals(jucator.getNume()) && jucatorEchipa.getPrenume().equals(jucator.getPrenume())) {
                    echipa.getJucatori().remove(jucatorEchipa);
                    break;
                }
            }
        }
    }
}