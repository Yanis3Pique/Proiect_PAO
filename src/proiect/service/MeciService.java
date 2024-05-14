package proiect.service;

import proiect.daoservices.*;
import proiect.model.Meci;
import proiect.model.Echipa;
import proiect.model.Stadion;
import proiect.utils.FileManagement;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.Scanner;

import static proiect.utils.Constants.AUDIT_FILE;

public class MeciService {
    private MeciRepositoryService databaseService;
    private StadionRepositoryService stadionRepositoryService;
    private EchipaRepositoryService echipaRepositoryService;

    public MeciService() throws SQLException {
        this.databaseService = new MeciRepositoryService();
        this.stadionRepositoryService = new StadionRepositoryService();
        this.echipaRepositoryService = new EchipaRepositoryService();
    }

    private Echipa getTeamByName(Scanner scanner, String teamType) throws SQLException {
        System.out.print("Enter " + teamType + " team name: ");
        String teamName = scanner.nextLine();
        Echipa team = echipaRepositoryService.getEchipaByName(teamName);
        if (team == null) {
            System.out.println(teamType + " team not found.");
        }
        return team;
    }

    private Stadion getStadiumByName(Scanner scanner) throws SQLException {
        System.out.print("Enter stadium name: ");
        String stadiumName = scanner.nextLine();
        Stadion stadium = stadionRepositoryService.getStadionByName(stadiumName);
        if (stadium == null) {
            System.out.println("Stadium not found.");
        }
        return stadium;
    }

    public void createMeci(Scanner scanner) throws SQLException {
        System.out.println("Creating a new Match:");
        Echipa homeTeam = getTeamByName(scanner, "home");
        if (homeTeam == null) return;
        Echipa awayTeam = getTeamByName(scanner, "away");
        if (awayTeam == null) return;
        System.out.print("Enter match date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        Stadion stadium = getStadiumByName(scanner);
        if (stadium == null) return;
        System.out.println("Enter match score:");
        System.out.print("Home team: ");
        int scoreHome = scanner.nextInt();
        System.out.print("Away team: ");
        int scoreAway = scanner.nextInt();
        scanner.nextLine();
        Meci meci = new Meci(0, homeTeam, awayTeam, date, scoreHome, scoreAway, stadium);

        try {
            databaseService.addMeci(meci);
            FileManagement.scriereFisierChar(AUDIT_FILE, "creare meci " + homeTeam.getNume() + " vs " + awayTeam.getNume());
        } catch (InvalidDataException e) {
            System.out.println("Creation failed: " + e.getMessage());
        }
    }

    public Meci searchMeci(Scanner scanner) throws SQLException {
        System.out.println("How do you want to search the stadium? [names/id]");
        String option = scanner.nextLine().toLowerCase();
        System.out.println("Enter:");
        switch (option) {
            case "names":
                System.out.print("Enter home team name: ");
                String homeTeamName = scanner.nextLine();
                System.out.print("Enter away team name: ");
                String awayTeamName = scanner.nextLine();
                System.out.print("Enter match date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                return databaseService.getMeci(homeTeamName, awayTeamName, date);
            case "id":
                int id = scanner.nextInt();
                scanner.nextLine();
                return databaseService.getMeciById(id);
            default:
                System.out.println("Invalid option.");
                return null;
        }
    }

    public void readMeci(Scanner scanner) throws SQLException {
        Meci meci = searchMeci(scanner);
        if (meci != null) {
            System.out.println(meci);
        }
    }

    public void viewAllMeci() throws SQLException {
        System.out.println("MATChES:");
        databaseService.printAllMatches();
        System.out.println();
    }

    public void updateMeci(Scanner scanner) throws SQLException {
        System.out.println("Updating a Match:");
        Meci existingMeci = searchMeci(scanner);
        if (existingMeci == null) {
            System.out.println("Match not found.");
            return;
        }
        System.out.print("Enter new score for home team: ");
        int scoreHome = scanner.nextInt();
        System.out.print("Enter new score for away team: ");
        int scoreAway = scanner.nextInt();
        scanner.nextLine();
        Meci newMeci = new Meci(existingMeci.getId(), existingMeci.getEchipa1(), existingMeci.getEchipa2(),
                                existingMeci.getData(), scoreHome, scoreAway, existingMeci.getStadion());

        try {
            databaseService.updateMeci(newMeci.getEchipa1().getNume(), newMeci.getEchipa2().getNume(), newMeci.getData(), newMeci);
            FileManagement.scriereFisierChar(AUDIT_FILE, "actualizare meci " + newMeci.getEchipa1().getNume() + " vs " + newMeci.getEchipa2().getNume());
        } catch (InvalidDataException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void deleteMeci(Scanner scanner) throws SQLException {
        System.out.println("Deleting a Match:");
        Meci meci = searchMeci(scanner);
        if (meci != null) {
            databaseService.removeMeci(meci);
            FileManagement.scriereFisierChar(AUDIT_FILE, "stergere meci " + meci.getEchipa1().getNume() + " vs " + meci.getEchipa2().getNume());
        } else {
            System.out.println("Match not found.");
        }
    }
}
