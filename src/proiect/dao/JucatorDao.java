package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.Echipa;
import proiect.model.Jucator;

import java.sql.*;
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
        // SQL to insert a new row into the Angajat table
        String sql_angajat = "INSERT INTO yanis_football_championship.angajat (nume, prenume, nationalitate, varsta, salariu) VALUES (?, ?, ?, ?, ?);";

        // SQL to insert a new row into the Jucator table
        String sql_jucator = "INSERT INTO yanis_football_championship.jucator (angajatId, numar, pozitie, echipaId) VALUES (?, ?, ?, ?);";

        // Insert into Angajat table and retrieve the generated key
        try (PreparedStatement statement = connection.prepareStatement(sql_angajat, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, jucator.getNume());
            statement.setString(2, jucator.getPrenume());
            statement.setString(3, jucator.getNationalitate());
            statement.setInt(4, jucator.getVarsta());
            statement.setDouble(5, jucator.getSalariu());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating angajat failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    jucator.setAngajatId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating angajat failed, no ID obtained.");
                }
            }
        }

        // Now insert into Jucator table using the retrieved angajatId
        try (PreparedStatement statement = connection.prepareStatement(sql_jucator)) {
            statement.setInt(1, jucator.getAngajatId());
            statement.setInt(2, jucator.getNumarTricou());
            statement.setString(3, jucator.getPozitie());
            statement.setInt(4, jucator.getId_echipa());
            statement.executeUpdate();
        }
    }

    @Override
    public Jucator read(String nume_prenume) throws SQLException {
        // Splitting the input to extract first name and last name
        String[] parts = nume_prenume.split("_");
        String nume = parts[0];
        String prenume = parts[1];

        // SQL query to join Angajat and Jucator tables on their ID
        String sql = "SELECT a.id, a.nume, a.prenume, a.nationalitate, a.varsta, a.salariu, j.angajatId, j.numar, j.pozitie, j.echipaId " +
                "FROM yanis_football_championship.angajat a JOIN yanis_football_championship.jucator j ON a.id = j.angajatId " +
                "WHERE a.nume = ? AND a.prenume = ?";

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
                jucator.setNationalitate(rs.getString("nationalitate"));
                jucator.setVarsta(rs.getInt("varsta"));
                jucator.setSalariu(rs.getDouble("salariu"));
                jucator.setAngajatId(rs.getInt("angajatId"));
                jucator.setNumarTricou(rs.getInt("numar"));
                jucator.setPozitie(rs.getString("pozitie"));
                jucator.setId_echipa(rs.getInt("echipaId"));
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
    public Jucator readByID(int id) throws SQLException {
        String sql = "SELECT a.id, a.nume, a.prenume, a.nationalitate, a.varsta, a.salariu, j.angajatId, j.numar, j.pozitie, j.echipaId " +
                "FROM yanis_football_championship.angajat a JOIN yanis_football_championship.jucator j ON a.id = j.angajatId " +
                "WHERE a.id = ?";
        ResultSet rs = null;
        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Jucator jucator = new Jucator();
                jucator.setId(rs.getInt("id"));
                jucator.setNume(rs.getString("nume"));
                jucator.setPrenume(rs.getString("prenume"));
                jucator.setNationalitate(rs.getString("nationalitate"));
                jucator.setVarsta(rs.getInt("varsta"));
                jucator.setSalariu(rs.getDouble("salariu"));
                jucator.setAngajatId(rs.getInt("angajatId"));
                jucator.setNumarTricou(rs.getInt("numar"));
                jucator.setPozitie(rs.getString("pozitie"));
                jucator.setId_echipa(rs.getInt("echipaId"));
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
        // Splitting the input to extract first name and last name
        String[] parts = nume_prenume.split("_");
        String nume = parts[0];
        String prenume = parts[1];

        String sql_angajat = "UPDATE yanis_football_championship.angajat a " +
                "SET a.nume = ?, a.prenume = ?, a.nationalitate = ?, a.varsta = ?, a.salariu = ? " +
                "WHERE a.nume = ? AND a.prenume = ?;";

        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql_angajat);) {
            statement.setString(1, updatedJucator.getNume());
            statement.setString(2, updatedJucator.getPrenume());
            statement.setString(3, updatedJucator.getNationalitate());
            statement.setInt(4, updatedJucator.getVarsta());
            statement.setDouble(5, updatedJucator.getSalariu());
            statement.setString(6, nume);
            statement.setString(7, prenume);
            statement.executeUpdate();
        }

        String sql_jucator = "UPDATE yanis_football_championship.jucator j " +
                "SET j.numar = ?, j.pozitie = ?, j.echipaId = ? " +
                "WHERE j.angajatId = (SELECT a.id FROM yanis_football_championship.angajat a WHERE a.nume = ? AND a.prenume = ?);";

        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql_jucator);) {
            statement.setInt(1, updatedJucator.getNumarTricou());
            statement.setString(2, updatedJucator.getPozitie());
            statement.setInt(3, updatedJucator.getId_echipa());
            statement.setString(4, updatedJucator.getNume());
            statement.setString(5, updatedJucator.getPrenume());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Jucator jucator) throws SQLException {
        String sql_jucator = "DELETE FROM yanis_football_championship.jucator j " +
                "WHERE j.angajatId = (SELECT a.id FROM yanis_football_championship.angajat a WHERE a.nume = ? AND a.prenume = ?);";

        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql_jucator);) {
            statement.setString(1, jucator.getNume());
            statement.setString(2, jucator.getPrenume());
            statement.executeUpdate();
        }

        String sql_angajat = "DELETE FROM yanis_football_championship.angajat a " +
                "WHERE a.nume = ? AND a.prenume = ?;";

        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql_angajat);) {
            statement.setString(1, jucator.getNume());
            statement.setString(2, jucator.getPrenume());
            statement.executeUpdate();
        }
    }

    public List<Jucator> findAllJucator() throws SQLException {
        List<Jucator> jucatori = new ArrayList<>();
        String sql = "SELECT a.id, a.nume, a.prenume, a.nationalitate, a.varsta, a.salariu, j.angajatId, j.numar, j.pozitie, j.echipaId " +
                "FROM yanis_football_championship.angajat a JOIN yanis_football_championship.jucator j ON a.id = j.angajatId";
        ResultSet rs = null;
        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            rs = statement.executeQuery();

            while (rs.next()){
                Jucator jucator = new Jucator();
                jucator.setId(rs.getInt("id"));
                jucator.setNume(rs.getString("nume"));
                jucator.setPrenume(rs.getString("prenume"));
                jucator.setNationalitate(rs.getString("nationalitate"));
                jucator.setVarsta(rs.getInt("varsta"));
                jucator.setSalariu(rs.getDouble("salariu"));
                jucator.setAngajatId(rs.getInt("angajatId"));
                jucator.setNumarTricou(rs.getInt("numar"));
                jucator.setPozitie(rs.getString("pozitie"));
                jucator.setId_echipa(rs.getInt("echipaId"));
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