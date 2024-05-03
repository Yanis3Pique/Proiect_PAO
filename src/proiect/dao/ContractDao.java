package proiect.dao;

import proiect.daoservices.DatabaseConnection;
import proiect.model.Contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractDao implements DaoInterface<Contract> {
    private static ContractDao contractDao;

    private Connection connection = DatabaseConnection.getConnection();

    private ContractDao() throws SQLException {}

    public static ContractDao getInstance() throws SQLException {
        if(contractDao == null){
            contractDao = new ContractDao();
        }
        return contractDao;
    }

    @Override
    public void create(Contract contract) throws SQLException {
        String sql = "INSERT INTO proiectpao.contract VALUES (?, ?, ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contract.getId());
            statement.setString(2, contract.getTeam().getNume());
            statement.setString(3, contract.getSponsor().getName());
            statement.setInt(4, contract.getDurationYears());
            statement.setDouble(5, contract.getSumMoney());
            statement.executeUpdate();
        }
    }

    @Override
    public Contract read(String teamName_sponsorName) throws SQLException {
        String teamName = teamName_sponsorName.split("_")[0];
        String sponsorName = teamName_sponsorName.split("_")[1];

        String sql = "SELECT * FROM proiectpao.contract c WHERE c.teamName = ? AND c.sponsorName = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teamName);
            statement.setString(2, sponsorName);
            rs = statement.executeQuery();
            if (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setDurationYears(rs.getInt("duration"));
                contract.setSumMoney(rs.getDouble("sumMoney"));
                return contract;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public Contract readByID(int id) throws SQLException {
        String sql = "SELECT * FROM proiectpao.contract c WHERE c.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setDurationYears(rs.getInt("duration"));
                contract.setSumMoney(rs.getDouble("sumMoney"));
                return contract;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void update(String teamName_sponsorName, Contract contractUpdated) throws SQLException {
        String teamName = teamName_sponsorName.split("_")[0];
        String sponsorName = teamName_sponsorName.split("_")[1];

        String sql = "UPDATE proiectpao.contract c SET c.duration = ?, c.sumMoney = ? WHERE c.teamName = ? AND c.sponsorName = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contractUpdated.getDurationYears());
            statement.setDouble(2, contractUpdated.getSumMoney());
            statement.setString(3, teamName);
            statement.setString(4, sponsorName);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Contract contract) throws SQLException {
        String sql = "DELETE FROM proiectpao.contract c WHERE c.teamName = ? AND c.sponsorName = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contract.getTeam().getNume());
            statement.setString(2, contract.getSponsor().getName());
            statement.executeUpdate();
        }
    }

    public List<Contract> findAllContract() throws SQLException {
        String sql = "SELECT * FROM proiectpao.contract";
        ResultSet rs = null;
        List<Contract> contracts = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setDurationYears(rs.getInt("duration"));
                contract.setSumMoney(rs.getDouble("sumMoney"));
                contracts.add(contract);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return contracts;
    }

    public boolean checkUniqueName(String name) throws SQLException {
        String sql = "SELECT * FROM proiectpao.contract c WHERE c.teamName = ? AND c.sponsorName = ?";
        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name.split("_")[0]);
            statement.setString(2, name.split("_")[1]);
            rs = statement.executeQuery();
            if(rs.getRow() == 0) {
                return true;
            }
        }
        return false;
    }
}
