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
    private static int nextId = 1;

    public static ContractDao getInstance() throws SQLException {
        if(contractDao == null){
            contractDao = new ContractDao();
        }
        return contractDao;
    }

    @Override
    public void create(Contract contract) {
        contract.setId(nextId++);

        String sql = "INSERT INTO proiectpao.contract VALUES (?, ?, ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contract.getId());
            statement.setString(2, contract.getTeam().getNume());
            statement.setString(3, contract.getSponsor().getName());
            statement.setInt(4, contract.getDurationYears());
            statement.setDouble(5, contract.getSumMoney());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contract read(String teamName_sponsorName) {
        String teamName = teamName_sponsorName.split("_")[0];
        String sponsorName = teamName_sponsorName.split("_")[1];

        String sql = "SELECT * FROM proiectpao.contract c WHERE c.teamName = ? AND c.sponsorName = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teamName);
            statement.setString(2, sponsorName);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void update(String teamName_sponsorName, Contract contractUpdated) {
        String teamName = teamName_sponsorName.split("_")[0];
        String sponsorName = teamName_sponsorName.split("_")[1];

        String sql = "UPDATE proiectpao.contract c SET c.duration = ?, c.sumMoney = ? WHERE c.teamName = ? AND c.sponsorName = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contractUpdated.getDurationYears());
            statement.setDouble(2, contractUpdated.getSumMoney());
            statement.setString(3, teamName);
            statement.setString(4, sponsorName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Contract contract) {
        String sql = "DELETE FROM proiectpao.contract c WHERE c.teamName = ? AND c.sponsorName = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contract.getTeam().getNume());
            statement.setString(2, contract.getSponsor().getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contract> findAllContract() {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM proiectpao.contract";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setDurationYears(rs.getInt("duration"));
                contract.setSumMoney(rs.getDouble("sumMoney"));
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contracts;
    }
}
