package proiect.daoservices;

import proiect.model.Contract;
import proiect.dao.ContractDao;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.List;

public class ContractRepositoryService {
    private final ContractDao contractDao = ContractDao.getInstance();

    public ContractRepositoryService() throws SQLException {}

    public void printAllContracts() throws SQLException {
        List<Contract> contracts = contractDao.findAllContract();
        if(contracts != null){
            contracts.forEach(System.out:: println);
        } else {
            System.out.println("There is no contract.");
        }
    }

    public List<Contract> getAllContracts() throws SQLException {
        return contractDao.findAllContract();
    }

    public void addContract(Contract contract) throws InvalidDataException {
        try {
            if(contract != null){
//                if(contractDao.readByID(contract.getId()) != null)
//                    throw new InvalidDataException("We already have a contract with this ID!");
                if(contractDao.read(contract.getTeam().getNume() + "_" + contract.getSponsor().getName()) != null)
                    throw new InvalidDataException("We already have a contract with this team and sponsor!");

                contractDao.create(contract);
                System.out.println("Contract added!");
            }
        } catch (SQLException e) {
            System.out.println("Creation failed: " + e.getMessage());
        }
    }

    public Contract getContractByTeamAndSponsor(String teamName, String sponsorName) throws SQLException {
        Contract contract = contractDao.read(teamName + "_" + sponsorName);

        if (contract != null) {
            return contract;
        } else {
            System.out.println("Contract has not been found!");
        }

        return null;
    }

    public Contract getContractById(int id) throws SQLException {
        Contract contract = contractDao.readByID(id);

        if (contract != null) {
            return contract;
        } else {
            System.out.println("Contract has not been found!");
        }
        return null;
    }

    public void updateContract(String teamName, String sponsorName, Contract contract) throws InvalidDataException {
        try {
            Contract existingContract = contractDao.read(teamName + "_" + sponsorName);
            if (existingContract != null) {
                if (!contractDao.checkUniqueName(contract.getTeam().getId(), contract.getSponsor().getId()))
                    throw new InvalidDataException("We already have a contract with this team and sponsor!");
            }

            contractDao.update(teamName + "_" + sponsorName, contract);
            System.out.println("Contract updated!");
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void removeContract(String teamName, String sponsorName) throws SQLException {
        Contract contract = contractDao.read(teamName + "_" + sponsorName);
        try {
            if (contract == null) return;

            contractDao.delete(contract);
            System.out.println("Contract removed!");

        } catch (SQLException e) {
            System.out.println("Removal failed: " + e.getMessage());
        }
    }
}