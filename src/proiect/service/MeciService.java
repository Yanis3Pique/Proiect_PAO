package proiect.service;

import proiect.daoservices.*;
import proiect.model.Meci;
import proiect.model.Echipa;
import proiect.model.Stadion;

import java.util.Scanner;

public class MeciService {
    private MeciRepositoryService meciRepositoryService;
    private EchipaRepositoryService echipaRepositoryService;
    private StadionRepositoryService stadionRepositoryService;

    public MeciService() {
        this.meciRepositoryService = new MeciRepositoryService();
        this.echipaRepositoryService = new EchipaRepositoryService();
        this.stadionRepositoryService = new StadionRepositoryService();
    }

    private Echipa getTeamByName(Scanner scanner, String teamType) {
        System.out.print("Enter " + teamType + " team name: ");
        String teamName = scanner.nextLine();
        Echipa team = echipaRepositoryService.getEchipaByName(teamName);
        if (team == null) {
            System.out.println(teamType + " team not found.");
        }
        return team;
    }

    private Stadion getStadiumByName(Scanner scanner) {
        System.out.print("Enter stadium name: ");
        String stadiumName = scanner.nextLine();
        Stadion stadium = stadionRepositoryService.getStadionByName(stadiumName);
        if (stadium == null) {
            System.out.println("Stadium not found.");
        }
        return stadium;
    }

    public void createMeci(Scanner scanner) {
        System.out.println("Creating a new Match:");
        Echipa homeTeam = getTeamByName(scanner, "home");
        if (homeTeam == null) return;

        Echipa awayTeam = getTeamByName(scanner, "away");
        if (awayTeam == null) return;

        System.out.print("Enter match date (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        Stadion stadium = getStadiumByName(scanner);
        if (stadium == null) return;

        Meci meci = new Meci(0, homeTeam, awayTeam, date, 0, 0, stadium);
        meciRepositoryService.addMeci(meci);
        System.out.println("Match created successfully.");
    }


    public void viewMeci(Scanner scanner) {
        System.out.print("Enter home team name: ");
        String homeTeamName = scanner.nextLine();
        System.out.print("Enter away team name: ");
        String awayTeamName = scanner.nextLine();
        System.out.print("Enter match date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        Meci meci = meciRepositoryService.getMeci(homeTeamName, awayTeamName, date);
        if (meci != null) {
            System.out.println(meci);
        } else {
            System.out.println("Match not found.");
        }
    }

    public void updateMeci(Scanner scanner) {
        System.out.println("Updating a Match:");
        System.out.print("Enter home team name: ");
        String homeTeamName = scanner.nextLine();
        System.out.print("Enter away team name: ");
        String awayTeamName = scanner.nextLine();
        System.out.print("Enter match date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        Meci existingMeci = meciRepositoryService.getMeci(homeTeamName, awayTeamName, date);
        if (existingMeci == null) {
            System.out.println("Match not found.");
            return;
        }
        System.out.print("Enter new score for home team: ");
        int scoreHome = scanner.nextInt();
        System.out.print("Enter new score for away team: ");
        int scoreAway = scanner.nextInt();
        scanner.nextLine();
        meciRepositoryService.updateMeci(homeTeamName, awayTeamName, date, scoreHome, scoreAway);
        System.out.println("Match updated successfully.");
    }

    public void deleteMeci(Scanner scanner) {
        System.out.println("Deleting a Match:");
        System.out.print("Enter home team name: ");
        String homeTeamName = scanner.nextLine();
        System.out.print("Enter away team name: ");
        String awayTeamName = scanner.nextLine();
        System.out.print("Enter match date (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        Meci meci = meciRepositoryService.getMeci(homeTeamName, awayTeamName, date);
        if (meci != null) {
            meciRepositoryService.removeMeci(meci);
            System.out.println("Match deleted successfully.");
        } else {
            System.out.println("Match not found.");
        }
    }
}
