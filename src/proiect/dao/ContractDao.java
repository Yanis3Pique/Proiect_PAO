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
        String sql = "INSERT INTO yanis_football_championship.contract VALUES (?, ?, ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contract.getId());
            statement.setInt(2, contract.getTeam().getId());
            statement.setInt(3, contract.getSponsor().getId());
            statement.setInt(4, contract.getDurationYears());
            statement.setDouble(5, contract.getSumMoney());
            statement.executeUpdate();
        }
    }

    @Override
    public Contract read(String teamName_sponsorName) throws SQLException {
        String[] parts = teamName_sponsorName.split("_");
        String teamName = parts[0];
        String sponsorName = parts[1];

        // Correct SQL query to join Contract with Echipa and Sponsor tables
        String sql = "SELECT c.id, c.echipaId, c.sponsorId, c.duration, c.valoare, e.id as eId, s.id as sId " +
                "FROM yanis_football_championship.Contract c " +
                "JOIN yanis_football_championship.Echipa e ON c.echipaId = e.id " +
                "JOIN yanis_football_championship.Sponsor s ON c.sponsorId = s.id " +
                "WHERE e.nume = ? AND s.nume = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teamName);
            statement.setString(2, sponsorName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Contract contract = new Contract();
                    contract.setId(rs.getInt("id"));
                    contract.setTeam(EchipaDao.getInstance().readByID(rs.getInt("eId")));
                    contract.setSponsor(SponsorDao.getInstance().readByID(rs.getInt("sId")));
                    contract.setDurationYears(rs.getInt("duration"));
                    contract.setSumMoney(rs.getDouble("valoare"));
                    return contract;
                }
            }
        }
        return null;
    }

    @Override
    public Contract readByID(int id) throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.contract c WHERE c.id = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setTeam(EchipaDao.getInstance().readByID(rs.getInt("echipaId")));
                contract.setSponsor(SponsorDao.getInstance().readByID(rs.getInt("sponsorId")));
                contract.setDurationYears(rs.getInt("duration"));
                contract.setSumMoney(rs.getDouble("valoare"));
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
        String[] parts = teamName_sponsorName.split("_");
        String teamName = parts[0];
        String sponsorName = parts[1];

        // Find the Echipa ID
        String findEchipaIdSql = "SELECT id FROM yanis_football_championship.Echipa WHERE nume = ?";
        int echipaId = -1;
        try (PreparedStatement statement = connection.prepareStatement(findEchipaIdSql)) {
            statement.setString(1, teamName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                echipaId = rs.getInt("id");
            } else {
                throw new SQLException("No team found with the name: " + teamName);
            }
        }

        // Find the Sponsor ID
        String findSponsorIdSql = "SELECT id FROM yanis_football_championship.Sponsor WHERE nume = ?";
        int sponsorId = -1;
        try (PreparedStatement statement = connection.prepareStatement(findSponsorIdSql)) {
            statement.setString(1, sponsorName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                sponsorId = rs.getInt("id");
            } else {
                throw new SQLException("No sponsor found with the name: " + sponsorName);
            }
        }

        // Update the Contract
        String updateSql = "UPDATE yanis_football_championship.Contract " +
                "SET duration = ?, valoare = ? " +
                "WHERE echipaId = ? AND sponsorId = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setInt(1, contractUpdated.getDurationYears());
            statement.setDouble(2, contractUpdated.getSumMoney());
            statement.setInt(3, echipaId);
            statement.setInt(4, sponsorId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating contract failed, no rows affected.");
            }
        }
    }

    @Override
    public void delete(Contract contract) throws SQLException {
        String sql = "DELETE FROM yanis_football_championship.contract WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contract.getId());
            statement.executeUpdate();
        }
    }

    public List<Contract> findAllContract() throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.contract";
        ResultSet rs = null;
        List<Contract> contracts = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setTeam(EchipaDao.getInstance().readByID(rs.getInt("echipaId")));
                contract.setSponsor(SponsorDao.getInstance().readByID(rs.getInt("sponsorId")));
                contract.setDurationYears(rs.getInt("duration"));
                contract.setSumMoney(rs.getDouble("valoare"));
                contracts.add(contract);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return contracts;
    }

    public boolean checkUniqueName(int echipaId, int sponsorId) throws SQLException {
        String sql = "SELECT * FROM yanis_football_championship.contract WHERE echipaId = ? AND sponsorId = ?";
        ResultSet rs = null;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, echipaId);
            statement.setInt(2, sponsorId);
            rs = statement.executeQuery();
            return !rs.next();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
