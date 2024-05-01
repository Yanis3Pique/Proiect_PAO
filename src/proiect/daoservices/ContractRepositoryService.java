package proiect.daoservices;

import proiect.model.Contract;
import proiect.dao.ContractDao;

import java.sql.SQLException;
import java.util.List;

public class ContractRepositoryService {
    private final ContractDao contractDao = ContractDao.getInstance();

    public ContractRepositoryService() throws SQLException {}

    public void addContract(Contract contract) throws SQLException {
        contractDao.create(contract);
    }

    public Contract getContractByTeamAndSponsor(String teamName, String sponsorName) throws SQLException {
        return contractDao.read(teamName + "_" + sponsorName);
    }

    public void updateContract(String teamName, String sponsorName, Contract contract) throws SQLException {
        contractDao.update(teamName + "_" + sponsorName, contract);
    }

    public void removeContract(String teamName, String sponsorName) throws SQLException {
        Contract contract = contractDao.read(teamName + "_" + sponsorName);
        contractDao.delete(contract);
    }

    public List<Contract> getAllContracts() {
        return contractDao.findAllContract();
    }
}