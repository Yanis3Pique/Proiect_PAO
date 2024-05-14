package proiect.service;

import proiect.daoservices.*;
import proiect.model.Contract;
import proiect.model.Echipa;
import proiect.model.Sponsor;
import proiect.utils.FileManagement;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.Scanner;

import static proiect.utils.Constants.AUDIT_FILE;

public class ContractService {
    private ContractRepositoryService databaseService;
    private EchipaRepositoryService echipaRepositoryService;
    private SponsorRepositoryService sponsorRepositoryService;

    public ContractService() throws SQLException {
        this.databaseService = new ContractRepositoryService();
        this.echipaRepositoryService = new EchipaRepositoryService();
        this.sponsorRepositoryService = new SponsorRepositoryService();
    }

    public void createContract(Scanner scanner) throws SQLException {
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
        try {
            databaseService.addContract(contract);
            FileManagement.scriereFisierChar(AUDIT_FILE, "Contract creat cu succes intre echipa " + teamName + " si sponsorul " + sponsorName);
            System.out.println("Contract created successfully.");
        } catch (InvalidDataException e) {
            System.out.println("Contract could not be created: " + e.getMessage());
        }
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

    public Contract searchContract(Scanner scanner) {
        System.out.println("How do you want to search the contract? [name/id]");
        String option = scanner.nextLine().toLowerCase();
        System.out.println("Enter:");
        switch (option) {
            case "name":
                System.out.print("Enter team name: ");
                String teamName = scanner.nextLine();
                System.out.print("Enter sponsor name: ");
                String sponsorName = scanner.nextLine();
                try {
                    return databaseService.getContractByTeamAndSponsor(teamName, sponsorName);
                } catch (SQLException e) {
                    System.out.println("Could not retrieve contract: " + e.getSQLState() + " " + e.getMessage());
                    return null;
                }
            case "id":
                System.out.print("Enter contract id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                try {
                    return databaseService.getContractById(id);
                } catch (SQLException e) {
                    System.out.println("Could not retrieve contract: " + e.getSQLState() + " " + e.getMessage());
                    return null;
                }
            default:
                System.out.println("Wrong option");
                return null;
        }
    }

    public void readContract(Scanner scanner) {
        Contract contract = searchContract(scanner);
        if (contract != null) {
            System.out.println(contract);
        } else {
            System.out.println("Contract not found.");
        }
    }

    public void viewAllContracts() throws SQLException {
        System.out.println("CONTRACTS:");
        databaseService.printAllContracts();
        System.out.println();
    }

    public void updateContract(Scanner scanner) throws SQLException {
        System.out.println("Update a Contract:");
        Contract existingContract = searchContract(scanner);
        if (existingContract == null) {
            System.out.println("Contract not found.");
            return;
        }
        int newDuration = getNewValidContractDuration(scanner);
        double newSumMoney = getNewValidContractSum(scanner);
        existingContract.setDurationYears(newDuration);
        existingContract.setSumMoney(newSumMoney);

        try {
            databaseService.updateContract(existingContract.getTeam().getNume(), existingContract.getSponsor().getName(), existingContract);
            FileManagement.scriereFisierChar(AUDIT_FILE, "Contract actualizat cu succes intre echipa " + existingContract.getTeam().getNume() + " si sponsorul " + existingContract.getSponsor().getName());
            System.out.println("Contract updated successfully.");
        } catch (InvalidDataException e) {
            System.out.println("Contract could not be updated: " + e.getMessage());
        }
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


    public void deleteContract(Scanner scanner) throws SQLException {
        System.out.println("Delete a Contract:");
        Contract contract = searchContract(scanner);
        if (contract != null) {
            databaseService.removeContract(contract.getTeam().getNume(), contract.getSponsor().getName());
            FileManagement.scriereFisierChar(AUDIT_FILE, "Contract sters cu succes intre echipa " + contract.getTeam().getNume() + " si sponsorul " + contract.getSponsor().getName());
            System.out.println("Contract deleted successfully.");
        } else {
            System.out.println("Contract not found.");
        }
    }
}