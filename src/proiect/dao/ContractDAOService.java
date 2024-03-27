package proiect.dao;

import proiect.model.Contract;
import proiect.repository.ContractRepository;

import java.util.List;

public class ContractDAOService {
    private final ContractRepository contractRepository = new ContractRepository();

    public void addContract(Contract contract) {
        contractRepository.createContract(contract);
    }

    public Contract getContractByTeamAndSponsor(String teamName, String sponsorName) {
        return contractRepository.readContract(teamName, sponsorName);
    }

    public void updateContract(String teamName, String sponsorName, Contract contract) {
        contractRepository.updateContract(teamName, sponsorName, contract);
    }

    public void removeContract(String teamName, String sponsorName) {
        contractRepository.deleteContract(teamName, sponsorName);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAllContract();
    }
}