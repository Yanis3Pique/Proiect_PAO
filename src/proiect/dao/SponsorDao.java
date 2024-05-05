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
        String sql = "INSERT INTO proiectpao.sponsor VALUES (?, ?, ?);";

        try(java.sql.PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, sponsor.getId());
            statement.setString(2, sponsor.getName());
            statement.setString(3, sponsor.getCountry());
            statement.executeUpdate();
        }
    }

    @Override
    public Sponsor read(String name) throws SQLException {
        String sql = "SELECT * FROM proiectpao.sponsor s WHERE s.name = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            rs = statement.executeQuery();

            while (rs.next()){
                Sponsor sponsor = new Sponsor();
                sponsor.setId(rs.getInt("id"));
                sponsor.setName(rs.getString("name"));
                sponsor.setCountry(rs.getString("country"));
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
        String sql = "SELECT * FROM proiectpao.sponsor s WHERE s.id = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                Sponsor sponsor = new Sponsor();
                sponsor.setId(rs.getInt("id"));
                sponsor.setName(rs.getString("name"));
                sponsor.setCountry(rs.getString("country"));
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
        String sql = "UPDATE proiectpao.sponsor set name = ?, country = ? where name = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, sponsorUpdated.getName());
            statement.setString(2, sponsorUpdated.getCountry());
            statement.setString(3, name);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Sponsor sponsor) throws SQLException {
        String sql = "DELETE FROM proiectpao.sponsor s WHERE s.name = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, sponsor.getName());
            statement.executeUpdate();
        }
    }

    public List<Sponsor> findAllSponsor() throws SQLException {
        String sql = "SELECT * FROM proiectpao.sponsor";
        ResultSet rs = null;
        List<Sponsor> sponsors = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while(rs.next()) {
                Sponsor sponsor = new Sponsor(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
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
        String sql = "SELECT * FROM proiectpao.sponsor WHERE name = ?";
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