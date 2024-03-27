package proiect;

import proiect.model.Echipa;
import proiect.service.*;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService angajatService = new AngajatService();
        EchipaService echipaService = new EchipaService();
        MeciService meciService = new MeciService();
        SponsorService sponsorService = new SponsorService();
        StadionService stadionService = new StadionService();
        CampionatService campionatService = new CampionatService();
        ContractService contractService = new ContractService();

        while (true) {
            generalMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageTeams(scanner, echipaService);
                case 2 -> manageMatches(scanner, meciService);
                case 3 -> manageEmployees(scanner, angajatService);
                case 4 -> manageStadiums(scanner, stadionService);
                case 5 -> manageSponsors(scanner, sponsorService);
                case 6 -> manageContracts(scanner, contractService, echipaService, sponsorService);
                case 7 -> viewChampionshipStandings(campionatService);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option! Please enter a number between 0 and 7.");
            }
        }
    }

    private static void manageTeams(Scanner scanner, EchipaService echipaService) {
        System.out.println("\nTeam Management");
        System.out.println("1. Create a new team");
        System.out.println("2. View a team");
        System.out.println("3. Update a team");
        System.out.println("4. Delete a team");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        int teamChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (teamChoice) {
            case 1 -> echipaService.createEchipa(scanner);
            case 2 -> echipaService.viewEchipa(scanner);
            case 3 -> echipaService.updateEchipa(scanner);
            case 4 -> echipaService.deleteEchipa(scanner);
            case 0 -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageMatches(Scanner scanner, MeciService meciService) {
        System.out.println("\nMatch Management");
        System.out.println("1. Schedule a new match");
        System.out.println("2. View match details");
        System.out.println("3. Update match score");
        System.out.println("4. Delete a match");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        int matchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (matchChoice) {
            case 1 -> meciService.createMeci(scanner);
            case 2 -> meciService.viewMeci(scanner);
            case 3 -> meciService.updateMeci(scanner);
            case 4 -> meciService.deleteMeci(scanner);
            case 0 -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageEmployees(Scanner scanner, AngajatService angajatService) {
        System.out.println("\nEmployee Management");
        System.out.println("1. Hire a new employee");
        System.out.println("2. View employee details");
        System.out.println("3. Update employee information");
        System.out.println("4. Terminate an employee");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        int employeeChoice = scanner.nextInt();
        scanner.nextLine();

        switch (employeeChoice) {
            case 1 -> angajatService.createAngajat(scanner);
            case 2 -> angajatService.readAngajat(scanner);
            case 3 -> angajatService.updateAngajat(scanner);
            case 4 -> angajatService.deleteAngajat(scanner);
            case 0 -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageStadiums(Scanner scanner, StadionService stadionService) {
        System.out.println("\nStadium Management");
        System.out.println("1. Add a new stadium");
        System.out.println("2. View stadium details");
        System.out.println("3. Update stadium information");
        System.out.println("4. Remove a stadium");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        int stadiumChoice = scanner.nextInt();
        scanner.nextLine();

        switch (stadiumChoice) {
            case 1 -> stadionService.createStadion(scanner);
            case 2 -> stadionService.viewStadion(scanner);
            case 3 -> stadionService.updateStadion(scanner);
            case 4 -> stadionService.deleteStadion(scanner);
            case 0 -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageSponsors(Scanner scanner, SponsorService sponsorService) {
        System.out.println("\nSponsor Management");
        System.out.println("1. Add a new sponsor");
        System.out.println("2. View sponsor details");
        System.out.println("3. Update sponsor information");
        System.out.println("4. Remove a sponsor");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        int sponsorChoice = scanner.nextInt();
        scanner.nextLine();

        switch (sponsorChoice) {
            case 1 -> sponsorService.createSponsor(scanner);
            case 2 -> sponsorService.viewSponsor(scanner);
            case 3 -> sponsorService.updateSponsor(scanner        );
            case 4 -> sponsorService.deleteSponsor(scanner);
            case 0 -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageContracts(Scanner scanner, ContractService contractService, EchipaService echipaService, SponsorService sponsorService) {
        System.out.println("\nContract Management");
        System.out.println("1. Create a new contract");
        System.out.println("2. View contract details");
        System.out.println("3. Update contract details");
        System.out.println("4. Terminate a contract");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        int contractChoice = scanner.nextInt();
        scanner.nextLine();

        switch (contractChoice) {
            case 1 -> contractService.createContract(scanner);
            case 2 -> contractService.viewContract(scanner);
            case 3 -> contractService.updateContract(scanner);
            case 4 -> contractService.deleteContract(scanner);
            case 0 -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void viewChampionshipStandings(CampionatService campionatService) {
        System.out.println("\nChampionship Standings");
        Map<Echipa, Integer> clasament = campionatService.vizualizeazaClasament();
        if (clasament.isEmpty()) {
            System.out.println("No teams have played matches yet.");
        } else {
            clasament.forEach((echipa, puncte) -> System.out.println(echipa.getNume() + " - Points: " + puncte));
        }
    }

    private static void generalMenu() {
        System.out.println("\nWelcome to Yanis3Pique's Championship Management System");
        System.out.println("1. Manage Teams");
        System.out.println("2. Manage Matches");
        System.out.println("3. Manage Employees");
        System.out.println("4. Manage Stadiums");
        System.out.println("5. Manage Sponsors");
        System.out.println("6. Manage Contracts");
        System.out.println("7. View Championship Standings");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }
}
