package proiect.service;

import proiect.dao.*;
import proiect.model.Contract;
import proiect.model.Echipa;
import proiect.model.Sponsor;

import java.util.Scanner;

public class ContractService {
    private ContractDAOService contractDAOService;
    private EchipaDAOService echipaDAOService;
    private SponsorDAOService sponsorDAOService;

    public ContractService() {
        this.contractDAOService = new ContractDAOService();
        this.echipaDAOService = new EchipaDAOService();
        this.sponsorDAOService = new SponsorDAOService();
    }

    public void createContract(Scanner scanner) {
        System.out.println("Creating a new Contract:");

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();
        Echipa team = echipaDAOService.getEchipaByName(teamName);
        if (team == null) {
            System.out.println("Team not found.");
            return;
        }

        System.out.print("Enter sponsor name: ");
        String sponsorName = scanner.nextLine();
        Sponsor sponsor = sponsorDAOService.getSponsorByName(sponsorName);
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
        contractDAOService.addContract(contract);
        System.out.println("Contract created successfully.");
    }

    public void viewContract(Scanner scanner) {
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter sponsor name: ");
        String sponsorName = scanner.nextLine();

        Contract contract = contractDAOService.getContractByTeamAndSponsor(teamName, sponsorName);
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

        Contract existingContract = contractDAOService.getContractByTeamAndSponsor(teamName, sponsorName);
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

        contractDAOService.updateContract(teamName, sponsorName, existingContract);
        System.out.println("Contract updated successfully.");
    }

    public void deleteContract(Scanner scanner) {
        System.out.println("Delete a Contract:");

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter sponsor name: ");
        String sponsorName = scanner.nextLine();

        contractDAOService.removeContract(teamName, sponsorName);
        System.out.println("Contract deleted successfully.");
    }
}