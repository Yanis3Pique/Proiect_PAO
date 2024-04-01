package proiect.daoservices;

import proiect.model.Contract;
import proiect.dao.ContractDao;

import java.util.List;

public class ContractRepositoryService {
    private final ContractDao contractDao = new ContractDao();

    public void addContract(Contract contract) {
        contractDao.createContract(contract);
    }

    public Contract getContractByTeamAndSponsor(String teamName, String sponsorName) {
        return contractDao.readContract(teamName, sponsorName);
    }

    public void updateContract(String teamName, String sponsorName, Contract contract) {
        contractDao.updateContract(teamName, sponsorName, contract);
    }

    public void removeContract(String teamName, String sponsorName) {
        contractDao.deleteContract(teamName, sponsorName);
    }

    public List<Contract> getAllContracts() {
        return contractDao.findAllContract();
    }
}