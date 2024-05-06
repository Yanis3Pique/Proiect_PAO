package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EchipaDao implements DaoInterface<Echipa> {
    private static EchipaDao echipaDao;

    private Connection connection = DatabaseConnection.getConnection();

    public EchipaDao() throws SQLException {}

    public static EchipaDao getInstance() throws SQLException {
        if(echipaDao == null){
            echipaDao = new EchipaDao();
        }
        return echipaDao;
    }

    @Override
    public void create(Echipa echipa) throws SQLException {
        String sql = "INSERT INTO yanis_football_championship.echipa VALUES (?, ?, ?, ?);";

        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, echipa.getId());
            statement.setString(2, echipa.getNume());
            statement.setInt(3, echipa.getAntrenor().getId());
            statement.setInt(4, echipa.getStadion().getId());
            statement.executeUpdate();
        }
    }

    @Override
    public Echipa read(String nume) throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.echipa e WHERE e.nume = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            rs = statement.executeQuery();

            while (rs.next()) {
                Echipa echipa = new Echipa();
                echipa.setId(rs.getInt("id"));
                echipa.setNume(rs.getString("nume"));
                echipa.setAntrenor(AntrenorDao.getInstance().readByID(rs.getInt("antrenorId")));
                echipa.setStadion(StadionDao.getInstance().readByID(rs.getInt("stadionId")));
                return echipa;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public Echipa readByID(int id) throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.echipa e WHERE e.id = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()) {
                Echipa echipa = new Echipa();
                echipa.setId(rs.getInt("id"));
                echipa.setNume(rs.getString("nume"));
                echipa.setAntrenor(new AntrenorDao().readByID(rs.getInt("antrenorId")));
                echipa.setStadion(new StadionDao().readByID(rs.getInt("stadionId")));
                return echipa;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void update(String nume, Echipa echipaUpdated) throws SQLException {
        String sql = "UPDATE yanis_football_championship.echipa SET nume = ?, antrenorId = ?, stadionId = ? WHERE nume = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, echipaUpdated.getNume());
            statement.setInt(2, echipaUpdated.getAntrenor().getId());
            statement.setInt(3, echipaUpdated.getStadion().getId());
            statement.setString(4, nume);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Echipa echipa) throws SQLException {
        String sql_meci = "DELETE FROM yanis_football_championship.meci WHERE echipa1 = ? OR echipa2 = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql_meci)) {
            statement.setInt(1, echipa.getId());
            statement.setInt(2, echipa.getId());
            statement.executeUpdate();
        }

        String sql_contract = "DELETE FROM yanis_football_championship.contract WHERE echipaId = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql_contract)) {
            statement.setInt(1, echipa.getId());
            statement.executeUpdate();
        }

        String sql_jucator = "UPDATE yanis_football_championship.jucator SET echipaId = NULL WHERE echipaId = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql_jucator)) {
            statement.setInt(1, echipa.getId());
            statement.executeUpdate();
        }

        String sql = "DELETE FROM yanis_football_championship.echipa WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, echipa.getId());
            statement.executeUpdate();
        }
    }

    public List<Echipa> findAllEchipa() {
        String sql = "SELECT * FROM yanis_football_championship.echipa";
        ResultSet rs = null;
        List<Echipa> echipe = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                Echipa echipa = new Echipa();
                echipa.setId(rs.getInt("id"));
                echipa.setNume(rs.getString("nume"));
                echipa.setAntrenor(new AntrenorDao().readByID(rs.getInt("antrenorId")));
                echipa.setStadion(new StadionDao().readByID(rs.getInt("stadionId")));
                echipe.add(echipa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return echipe;
    }

    public boolean checkUniqueName(String nume) {
        String sql = "SELECT * FROM yanis_football_championship.echipa e WHERE e.nume = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            rs = statement.executeQuery();

            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Jucator> getJucatoriByEchipa(Echipa echipa) throws SQLException {
        // SQL to join Jucator and Angajat tables
        String sql = "SELECT j.id, a.nume, a.prenume, a.nationalitate, a.varsta, a.salariu, j.numar, j.pozitie, j.echipaId " +
                "FROM yanis_football_championship.Jucator j " +
                "JOIN yanis_football_championship.Angajat a ON j.angajatId = a.id " +
                "WHERE j.echipaId = ?";

        List<Jucator> jucatori = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, echipa.getId());
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Jucator jucator = new Jucator();
                    jucator.setId(rs.getInt("id"));
                    jucator.setNume(rs.getString("nume"));
                    jucator.setPrenume(rs.getString("prenume"));
                    jucator.setNationalitate(rs.getString("nationalitate"));
                    jucator.setVarsta(rs.getInt("varsta"));
                    jucator.setSalariu(rs.getDouble("salariu"));
                    jucator.setId_echipa(rs.getInt("echipaId"));
                    jucator.setPozitie(rs.getString("pozitie"));
                    jucator.setNumarTricou(rs.getInt("numar"));
                    jucatori.add(jucator);
                }
            }
        }
        return jucatori;
    }
}
