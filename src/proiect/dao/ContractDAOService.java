package proiect.dao;

import proiect.model.Contract;
import proiect.model.Echipa;
import proiect.model.Sponsor;
import proiect.repository.ContractRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ContractDAOService {
    private ContractRepository contractRepository;

    public ContractDAOService() {
        this.contractRepository = new ContractRepository();
    }

    public void createContract(Echipa team, Sponsor sponsor, int durationYears, double sumMoney) {
        Contract newContract = new Contract(team, sponsor, durationYears, sumMoney);
        contractRepository.createContract(newContract);
        System.out.println("Contract creat cu succes intre " + team.getNume() + " si " + sponsor.getName() + ".");
    }

    public Contract readContract(String teamName, String sponsorName) {
        return contractRepository.readContract(teamName, sponsorName);
    }

    public void updateContract(String teamName, String sponsorName, Contract updatedContract) {
        contractRepository.updateContract(teamName, sponsorName, updatedContract);
        System.out.println("Contract intre " + teamName + " si " + sponsorName + " actualizat cu succes.");
    }

    public void deleteContract(String teamName, String sponsorName) {
        contractRepository.deleteContract(teamName, sponsorName);
        System.out.println("Contract intre " + teamName + " si " + sponsorName + " sters cu succes.");
    }

    public List<Contract> findAllContracts() {
        return contractRepository.findAllContract();
    }

    public List<Contract> findContractsForTeam(Echipa team) {
        return findAllContracts().stream()
                .filter(contract -> contract.getTeam().equals(team))
                .collect(Collectors.toList());
    }

    public void displayContractsForTeam(Echipa team) {
        List<Contract> contracts = findContractsForTeam(team);
        if (contracts.isEmpty()) {
            System.out.println("Echipa " + team.getNume() + " nu are contracte cu niciun sponsor.");
        } else {
            System.out.println("Contracts for team: " + team.getNume());
            contracts.forEach(contract -> {
                String contractDetails = String.format("Sponsor: %s, Tara: %s, Perioada: %d ani, Valoare: %.2f",
                        contract.getSponsor().getName(), contract.getSponsor().getCountry(),
                        contract.getDurationYears(), contract.getSumMoney());
                System.out.println(contractDetails);
            });
        }
    }
}
