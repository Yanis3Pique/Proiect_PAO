package proiect.service;

import proiect.daoservices.*;
import proiect.model.Contract;
import proiect.model.Echipa;
import proiect.model.Sponsor;

import java.util.Scanner;

public class ContractService {
    private ContractRepositoryService contractRepositoryService;
    private EchipaRepositoryService echipaRepositoryService;
    private SponsorRepositoryService sponsorRepositoryService;

    public ContractService() {
        this.contractRepositoryService = new ContractRepositoryService();
        this.echipaRepositoryService = new EchipaRepositoryService();
        this.sponsorRepositoryService = new SponsorRepositoryService();
    }

    public void createContract(Scanner scanner) {
        System.out.println("Creating a new Contract:");

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();
        Echipa team = echipaRepositoryService.getEchipaByName(teamName);
        if (team == null) {
            System.out.println("Team not found.");
            return;
        }

        System.out.print("Enter sponsor name: ");
        String sponsorName = scanner.nextLine();
        Sponsor sponsor = sponsorRepositoryService.getSponsorByName(sponsorName);
        if (sponsor == null) {
            System.out.println("Sponsor not found.");
            return;
        }

        System.out.print("Enter contract duration (years): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter sum of money for the contract: ");
        double sumMoney = scanner.nextDouble();
        scanner.nextLine();

        Contract contract = new Contract(0, team, sponsor, duration, sumMoney);
        contractRepositoryService.addContract(contract);
        System.out.println("Contract created successfully.");
    }

    public void viewContract(Scanner scanner) {
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter sponsor name: ");
        String sponsorName = scanner.nextLine();

        Contract contract = contractRepositoryService.getContractByTeamAndSponsor(teamName, sponsorName);
        if (contract != null) {
            System.out.println(contract);
        } else {
            System.out.println("Contract not found.");
        }
    }

    public void updateContract(Scanner scanner) {
        System.out.println("Update a Contract:");
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();
        System.out.print("Enter sponsor name: ");
        String sponsorName = scanner.nextLine();
        Contract existingContract = contractRepositoryService.getContractByTeamAndSponsor(teamName, sponsorName);
        if (existingContract == null) {
            System.out.println("Contract not found.");
            return;
        }
        System.out.print("Enter new contract duration (years): ");
        int duration = scanner.nextInt();
        System.out.print("Enter new sum of money for the contract: ");
        double sumMoney = scanner.nextDouble();
        scanner.nextLine();
        existingContract.setDurationYears(duration);
        existingContract.setSumMoney(sumMoney);
        contractRepositoryService.updateContract(teamName, sponsorName, existingContract);
        System.out.println("Contract updated successfully.");
    }

    public void deleteContract(Scanner scanner) {
        System.out.println("Delete a Contract:");

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter sponsor name: ");
        String sponsorName = scanner.nextLine();

        contractRepositoryService.removeContract(teamName, sponsorName);
        System.out.println("Contract deleted successfully.");
    }
}