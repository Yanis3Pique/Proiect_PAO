package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.Contract;
import proiect.model.Sponsor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SponsorDao implements DaoInterface<Sponsor> {
    private static SponsorDao sponsorDao;

    private Connection connection = DatabaseConnection.getConnection();

    public SponsorDao() throws SQLException {}

    public static SponsorDao getInstance() throws SQLException {
        if(sponsorDao == null){
            sponsorDao = new SponsorDao();
        }
        return sponsorDao;
    }

    @Override
    public void create(Sponsor sponsor) throws SQLException {
        String sql = "INSERT INTO yanis_football_championship.sponsor VALUES (?, ?, ?);";

        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, sponsor.getId());
            statement.setString(2, sponsor.getName());
            statement.setString(3, sponsor.getCountry());
            statement.executeUpdate();
        }
    }

    @Override
    public Sponsor read(String name) throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.sponsor s WHERE s.nume = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            rs = statement.executeQuery();

            while (rs.next()){
                Sponsor sponsor = new Sponsor();
                sponsor.setId(rs.getInt("id"));
                sponsor.setName(rs.getString("nume"));
                sponsor.setCountry(rs.getString("tara"));
                return  sponsor;
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public Sponsor readByID(int id) throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.sponsor s WHERE s.id = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Sponsor sponsor = new Sponsor();
                sponsor.setId(rs.getInt("id"));
                sponsor.setName(rs.getString("nume"));
                sponsor.setCountry(rs.getString("tara"));
                return  sponsor;
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void update(String name, Sponsor sponsorUpdated) throws SQLException {
        String sql = "UPDATE yanis_football_championship.sponsor set nume = ?, tara = ? where nume = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, sponsorUpdated.getName());
            statement.setString(2, sponsorUpdated.getCountry());
            statement.setString(3, name);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Sponsor sponsor) throws SQLException {
        String sql_contract = "DELETE FROM yanis_football_championship.contract c WHERE c.sponsorId = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql_contract);) {
            statement.setInt(1, sponsor.getId());
            statement.executeUpdate();
        }

        String sql = "DELETE FROM yanis_football_championship.sponsor s WHERE s.nume = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, sponsor.getName());
            statement.executeUpdate();
        }
    }

    public List<Sponsor> findAllSponsor() throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.sponsor";
        ResultSet rs = null;
        List<Sponsor> sponsors = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while(rs.next()) {
                Sponsor sponsor = new Sponsor(rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("tara"));
                sponsors.add(sponsor);
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
            else sponsors = null;
        }

        return sponsors;
    }

    public boolean checkUniqueName(String name) throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.sponsor WHERE nume = ?";
        ResultSet rs = null;

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            rs = statement.executeQuery();
            return rs.next();
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
    }
}