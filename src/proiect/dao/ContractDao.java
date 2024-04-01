package proiect.dao;

import proiect.model.Contract;

import java.util.ArrayList;
import java.util.List;

public class ContractDao {
    private static int nextId = 1;
    private static List<Contract> contracts = new ArrayList<>();

    public void createContract(Contract contract) {
        contract.setId(nextId++);
        contracts.add(contract);
    }

    public Contract readContract(String teamName, String sponsorName) {
        for (Contract contract : contracts) {
            if (contract.getTeam().getNume().equals(teamName) &&
                    contract.getSponsor().getName().equals(sponsorName)) {
                return contract;
            }
        }
        return null;
    }

    public void updateContract(String teamName, String sponsorName, Contract contractUpdated) {
        for (Contract contract : contracts) {
            if (contract.getTeam().getNume().equals(teamName) && contract.getSponsor().getName().equals(sponsorName)) {
                contract.setId(contractUpdated.getId());
                contract.setTeam(contractUpdated.getTeam());
                contract.setSponsor(contractUpdated.getSponsor());
                contract.setSumMoney(contractUpdated.getSumMoney());
                contract.setDurationYears(contractUpdated.getDurationYears());
                return;
            }
        }
    }

    public void deleteContract(String teamName, String sponsorName) {
        contracts.removeIf(contract ->
                contract.getTeam().getNume().equals(teamName) &&
                        contract.getSponsor().getName().equals(sponsorName));
    }

    public List<Contract> findAllContract() {
        return new ArrayList<>(contracts);
    }
}
