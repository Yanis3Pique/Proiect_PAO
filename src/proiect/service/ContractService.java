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

        String teamName = getValidTeamName(scanner);
        Echipa team = echipaRepositoryService.getEchipaByName(teamName);
        if (team == null) {
            System.out.println("Team not found.");
            return;
        }

        String sponsorName = getValidSponsorName(scanner);
        Sponsor sponsor = sponsorRepositoryService.getSponsorByName(sponsorName);
        if (sponsor == null) {
            System.out.println("Sponsor not found.");
            return;
        }

        int duration = getValidContractDuration(scanner);
        double sumMoney = getValidContractSum(scanner);

        Contract contract = new Contract(0, team, sponsor, duration, sumMoney);
        contractRepositoryService.addContract(contract);
        System.out.println("Contract created successfully.");
    }

    private String getValidTeamName(Scanner scanner) {
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();
        while (teamName.trim().isEmpty()) {
            System.out.println("Team name cannot be empty. Please enter a valid team name.");
            teamName = scanner.nextLine();
        }
        return teamName;
    }

    private String getValidSponsorName(Scanner scanner) {
        System.out.print("Enter sponsor name: ");
        String sponsorName = scanner.nextLine();
        while (sponsorName.trim().isEmpty()) {
            System.out.println("Sponsor name cannot be empty. Please enter a valid sponsor name.");
            sponsorName = scanner.nextLine();
        }
        return sponsorName;
    }

    private int getValidContractDuration(Scanner scanner) {
        System.out.print("Enter contract duration (years): ");
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid number. Please enter a valid duration in years.");
            scanner.next();
            System.out.print("Enter contract duration (years): ");
        }
        int duration = scanner.nextInt();
        while (duration <= 0) {
            System.out.println("Duration must be positive. Please enter a valid duration in years.");
            scanner.nextLine();
            duration = scanner.nextInt();
        }
        scanner.nextLine();
        return duration;
    }

    private double getValidContractSum(Scanner scanner) {
        System.out.print("Enter sum of money for the contract: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("That's not a valid amount. Please enter a valid sum of money.");
            scanner.next();
            System.out.print("Enter sum of money for the contract: ");
        }
        double sumMoney = scanner.nextDouble();
        while (sumMoney <= 0) {
            System.out.println("Sum of money must be positive. Please enter a valid sum.");
            scanner.nextLine();
            sumMoney = scanner.nextDouble();
        }
        scanner.nextLine();
        return sumMoney;
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
        int newDuration = getNewValidContractDuration(scanner);
        double newSumMoney = getNewValidContractSum(scanner);
        existingContract.setDurationYears(newDuration);
        existingContract.setSumMoney(newSumMoney);
        contractRepositoryService.updateContract(teamName, sponsorName, existingContract);
        System.out.println("Contract updated successfully.");
    }

    private int getNewValidContractDuration(Scanner scanner) {
        int duration;
        do {
            System.out.print("Enter new contract duration (years): ");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number. Please enter a valid duration in years.");
                scanner.next();
            }
            duration = scanner.nextInt();
            if (duration <= 0) {
                System.out.println("Duration must be positive. Please enter a valid duration in years.");
            }
        } while (duration <= 0);
        scanner.nextLine();
        return duration;
    }

    private double getNewValidContractSum(Scanner scanner) {
        double sumMoney;
        do {
            System.out.print("Enter new sum of money for the contract: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("That's not a valid amount. Please enter a valid sum of money.");
                scanner.next();
            }
            sumMoney = scanner.nextDouble();
            if (sumMoney <= 0) {
                System.out.println("Sum of money must be positive. Please enter a valid sum.");
            }
        } while (sumMoney <= 0);
        scanner.nextLine();
        return sumMoney;
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