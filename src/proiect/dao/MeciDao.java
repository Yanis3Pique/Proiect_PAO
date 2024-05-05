package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.Meci;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeciDao implements DaoInterface<Meci> {
    private static MeciDao meciDao;

    private Connection connection = DatabaseConnection.getConnection();

    public MeciDao() throws SQLException {}

    public static MeciDao getInstance() throws SQLException {
        if(meciDao == null){
            meciDao = new MeciDao();
        }
        return meciDao;
    }

    @Override
    public void create(Meci meci) throws SQLException {
        String sql = "INSERT INTO yanis_football_championship.meci VALUES (?, ?, ?, ?, ?, ?, ?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, meci.getId());
            statement.setString(2, meci.getData());
            statement.setInt(3, meci.getEchipa1().getId());
            statement.setInt(4, meci.getEchipa2().getId());
            statement.setInt(5, meci.getStadion().getId());
            statement.setInt(6, meci.getScor1());
            statement.setInt(7, meci.getScor2());
            statement.executeUpdate();
        }
    }

    @Override
    public Meci read(String numeEchipa1_numeEchipa2_data) throws SQLException {
        String numeEchipa1 = numeEchipa1_numeEchipa2_data.split("_")[0];
        String numeEchipa2 = numeEchipa1_numeEchipa2_data.split("_")[1];
        String data = numeEchipa1_numeEchipa2_data.split("_")[2];

        String sql = "SELECT * FROM yanis_football_championship.meci m WHERE m.echipa1 = ? AND m.echipa2 = ? AND m.data = ?";
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, numeEchipa1);
            statement.setString(2, numeEchipa2);
            statement.setString(3, data);
            rs = statement.executeQuery();

            while (rs.next()) {
                Meci meci = new Meci();
                meci.setId(rs.getInt("id"));
                meci.setData(rs.getString("data"));
                meci.setEchipa1(EchipaDao.getInstance().readByID(rs.getInt("echipa1")));
                meci.setEchipa2(EchipaDao.getInstance().readByID(rs.getInt("echipa2")));
                meci.setStadion(StadionDao.getInstance().readByID(rs.getInt("stadionId")));
                meci.setScor1(rs.getInt("scorEchipa1"));
                meci.setScor2(rs.getInt("scorEchipa2"));
                return meci;
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public Meci readByID(int id) throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.meci WHERE id = ?";
        ResultSet rs = null;

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()) {
                Meci meci = new Meci();
                meci.setId(rs.getInt("id"));
                meci.setData(rs.getString("data"));
                meci.setEchipa1(EchipaDao.getInstance().readByID(rs.getInt("echipa1")));
                meci.setEchipa2(EchipaDao.getInstance().readByID(rs.getInt("echipa2")));
                meci.setStadion(StadionDao.getInstance().readByID(rs.getInt("stadionId")));
                meci.setScor1(rs.getInt("scorEchipa1"));
                meci.setScor2(rs.getInt("scorEchipa2"));
                return meci;
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void update(String numeEchipa1_numeEchipa2_date, Meci newMeci) throws SQLException {
        String numeEchipa1 = numeEchipa1_numeEchipa2_date.split("_")[0];
        String numeEchipa2 = numeEchipa1_numeEchipa2_date.split("_")[1];
        String date = numeEchipa1_numeEchipa2_date.split("_")[2];

        String sql = "UPDATE yanis_football_championship.meci SET echipa1 = ?, echipa2 = ?, data = ?, scor1 = ?, scor2 = ?, stadion = ? WHERE echipa1 = ? AND echipa2 = ? AND data = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, newMeci.getEchipa1().getId());
            statement.setInt(2, newMeci.getEchipa2().getId());
            statement.setString(3, newMeci.getData());
            statement.setInt(4, newMeci.getScor1());
            statement.setInt(5, newMeci.getScor2());
            statement.setInt(6, newMeci.getStadion().getId());
            statement.setString(7, numeEchipa1);
            statement.setString(8, numeEchipa2);
            statement.setString(9, date);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Meci meci) throws SQLException {
        String sql = "DELETE FROM yanis_football_championship.meci WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, meci.getId());
            statement.executeUpdate();
        }
    }

    public List<Meci> findAllMeci() throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.meci";
        ResultSet rs = null;
        List<Meci> meciuri = new ArrayList<>();
        try(Statement statement = connection.createStatement();) {
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Meci meci = new Meci();
                meci.setId(rs.getInt("id"));
                meci.setData(rs.getString("data"));
                meci.setEchipa1(EchipaDao.getInstance().readByID(rs.getInt("echipa1")));
                meci.setEchipa2(EchipaDao.getInstance().readByID(rs.getInt("echipa2")));
                meci.setStadion(StadionDao.getInstance().readByID(rs.getInt("stadionId")));
                meci.setScor1(rs.getInt("scorEchipa1"));
                meci.setScor2(rs.getInt("scorEchipa2"));
                meciuri.add(meci);
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
            else meciuri = null;
        }

        return meciuri;
    }
}