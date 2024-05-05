package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.Antrenor;
import proiect.model.Echipa;
import proiect.utils.InvalidDataException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AntrenorDao implements DaoInterface<Antrenor> {

    private static AntrenorDao antrenorDao;
    private Connection connection = DatabaseConnection.getConnection();
    public AntrenorDao() throws SQLException {}

    public static AntrenorDao getInstance() throws SQLException {
        if(antrenorDao == null){
            antrenorDao = new AntrenorDao();
        }
        return antrenorDao;
    }

    @Override
    public void create(Antrenor antrenor) throws SQLException {
        // SQL for inserting into the Angajat table
        String sql_angajat = "INSERT INTO yanis_football_championship.angajat (nume, prenume, nationalitate, varsta, salariu) VALUES (?, ?, ?, ?, ?);";
        // SQL for inserting into the Antrenor table
        String sql_antrenor = "INSERT INTO yanis_football_championship.antrenor (angajatId, aniExperienta) VALUES (?, ?);";

        // Insert into Angajat table and retrieve the generated key
        try (PreparedStatement statement = connection.prepareStatement(sql_angajat, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, antrenor.getNume());
            statement.setString(2, antrenor.getPrenume());
            statement.setString(3, antrenor.getNationalitate());
            statement.setInt(4, antrenor.getVarsta());
            statement.setDouble(5, antrenor.getSalariu());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating angajat failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    antrenor.setAngajatId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating angajat failed, no ID obtained.");
                }
            }
        }

        // Now insert into Antrenor table using the retrieved angajatId
        try (PreparedStatement statement = connection.prepareStatement(sql_antrenor)) {
            statement.setInt(1, antrenor.getAngajatId());
            statement.setInt(2, antrenor.getAniExperienta());
            statement.executeUpdate();
        }
    }


    @Override
    public Antrenor read(String nume_prenume) throws SQLException {
        // Splitting the input to extract first name and last name
        String[] parts = nume_prenume.split("_");
        String nume = parts[0];
        String prenume = parts[1];

        // SQL query to join Angajat and Antrenor tables on their ID
        String sql = "SELECT a.id, a.nume, a.prenume, a.nationalitate, a.varsta, a.salariu, t.angajatId, t.aniExperienta " +
                "FROM yanis_football_championship.angajat a JOIN yanis_football_championship.antrenor t ON a.id = t.angajatId " +
                "WHERE a.nume = ? AND a.prenume = ?";

        ResultSet result = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nume);
            statement.setString(2, prenume);
            result = statement.executeQuery();

            if (result.next()) {
                Antrenor antrenor = new Antrenor();
                antrenor.setId(result.getInt("id"));
                antrenor.setNume(result.getString("nume"));
                antrenor.setPrenume(result.getString("prenume"));
                antrenor.setNationalitate(result.getString("nationalitate"));
                antrenor.setVarsta(result.getInt("varsta"));
                antrenor.setSalariu(result.getDouble("salariu"));
                antrenor.setAngajatId(result.getInt("angajatId"));
                antrenor.setAniExperienta(result.getInt("aniExperienta"));
                return antrenor;
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
        return null;
    }

    @Override
    public Antrenor readByID(int id) throws SQLException {
        String sql = "SELECT a.id, a.nume, a.prenume, a.nationalitate, a.varsta, a.salariu, t.angajatId, t.aniExperienta " +
                "FROM yanis_football_championship.angajat a JOIN yanis_football_championship.antrenor t ON a.id = t.angajatId " +
                "WHERE a.id = ?";

        ResultSet result = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                Antrenor antrenor = new Antrenor();
                antrenor.setId(result.getInt("id"));
                antrenor.setNume(result.getString("nume"));
                antrenor.setPrenume(result.getString("prenume"));
                antrenor.setNationalitate(result.getString("nationalitate"));
                antrenor.setVarsta(result.getInt("varsta"));
                antrenor.setSalariu(result.getDouble("salariu"));
                antrenor.setAngajatId(result.getInt("angajatId"));
                antrenor.setAniExperienta(result.getInt("aniExperienta"));
                return antrenor;
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
        return null;
    }

    @Override
    public void update(String nume_prenume, Antrenor antrenorUpdated) throws SQLException {
        // Splitting the input to extract first name and last name
        String[] parts = nume_prenume.split("_");
        String nume = parts[0];
        String prenume = parts[1];

        String sql_angajat = "UPDATE yanis_football_championship.angajat a " +
                "SET a.nume = ?, a.prenume = ?, a.nationalitate = ?, a.varsta = ?, a.salariu = ? " +
                "WHERE a.nume = ? AND a.prenume = ?;";

        try(PreparedStatement statement = connection.prepareStatement(sql_angajat);) {
            statement.setString(1, antrenorUpdated.getNume());
            statement.setString(2, antrenorUpdated.getPrenume());
            statement.setString(3, antrenorUpdated.getNationalitate());
            statement.setInt(4, antrenorUpdated.getVarsta());
            statement.setDouble(5, antrenorUpdated.getSalariu());
            statement.setString(6, nume);
            statement.setString(7, prenume);
            statement.executeUpdate();
        }

        String sql_antrenor = "UPDATE yanis_football_championship.antrenor t " +
                "SET t.aniExperienta = ? " +
                "WHERE t.id = (SELECT a.id FROM yanis_football_championship.angajat a WHERE a.nume = ? AND a.prenume = ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql_antrenor);) {
            statement.setInt(1, antrenorUpdated.getAniExperienta());
            statement.setString(2, antrenorUpdated.getNume());
            statement.setString(3, antrenorUpdated.getPrenume());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Antrenor antrenor) throws SQLException {
        String sql_antrenor = "DELETE FROM yanis_football_championship.antrenor t " +
                "WHERE t.angajatId = (SELECT a.id FROM yanis_football_championship.angajat a WHERE a.nume = ? AND a.prenume = ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql_antrenor);) {
            statement.setString(1, antrenor.getNume());
            statement.setString(2, antrenor.getPrenume());
            statement.executeUpdate();
        }

        String sql_angajat = "DELETE FROM yanis_football_championship.angajat a " +
                "WHERE a.nume = ? AND a.prenume = ?;";

        try(PreparedStatement statement = connection.prepareStatement(sql_angajat);) {
            statement.setString(1, antrenor.getNume());
            statement.setString(2, antrenor.getPrenume());
            statement.executeUpdate();
        }
    }

    public List<Antrenor> findAllAntrenor() throws SQLException {
        List<Antrenor> antrenori = new ArrayList<>();
        String sql = "SELECT a.id, a.nume, a.prenume, a.nationalitate, a.varsta, a.salariu, t.angajatId, t.aniExperienta " +
                "FROM yanis_football_championship.angajat a JOIN yanis_football_championship.antrenor t ON a.id = t.angajatId";

        ResultSet result = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            result = statement.executeQuery();

            while (result.next()) {
                Antrenor antrenor = new Antrenor();
                antrenor.setId(result.getInt("id"));
                antrenor.setNume(result.getString("nume"));
                antrenor.setPrenume(result.getString("prenume"));
                antrenor.setNationalitate(result.getString("nationalitate"));
                antrenor.setVarsta(result.getInt("varsta"));
                antrenor.setSalariu(result.getDouble("salariu"));
                antrenor.setAngajatId(result.getInt("angajatId"));
                antrenor.setAniExperienta(result.getInt("aniExperienta"));
                antrenori.add(antrenor);
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
        return antrenori;
    }

    public boolean checkUniqueName(String name) throws SQLException {
        String sql = "SELECT a.nume, a.prenume " +
                "FROM yanis_football_championship.angajat a " +
                "WHERE a.nume = ?";

        ResultSet result = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            result = statement.executeQuery();

            if (result.next()) {
                return false;
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
        return true;
    }
}
