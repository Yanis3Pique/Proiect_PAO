package proiect.service;

import proiect.dao.*;
import proiect.model.Meci;
import proiect.model.Echipa;
import proiect.model.Stadion;

import java.util.Scanner;

public class MeciService {
    private MeciDAOService meciDAOService;
    private EchipaDAOService echipaDAOService;
    private StadionDAOService stadionDAOService;

    public MeciService() {
        this.meciDAOService = new MeciDAOService();
        this.echipaDAOService = new EchipaDAOService();
        this.stadionDAOService = new StadionDAOService();
    }

    public void createMeci(Scanner scanner) {
        System.out.println("Creating a new Match:");

        System.out.print("Enter home team name: ");
        String homeTeamName = scanner.nextLine();
        Echipa homeTeam = echipaDAOService.getEchipaByName(homeTeamName);
        if (homeTeam == null) {
            System.out.println("Home team not found.");
            return;
        }

        System.out.print("Enter away team name: ");
        String awayTeamName = scanner.nextLine();
        Echipa awayTeam = echipaDAOService.getEchipaByName(awayTeamName);
        if (awayTeam == null) {
            System.out.println("Away team not found.");
            return;
        }

        System.out.print("Enter match date (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        System.out.print("Enter stadium name: ");
        String stadiumName = scanner.nextLine();
        Stadion stadium = stadionDAOService.getStadionByName(stadiumName);
        if (stadium == null) {
            System.out.println("Stadium not found.");
            return;
        }

        Meci meci = new Meci(0, homeTeam, awayTeam, date, 0, 0, stadium);
        meciDAOService.addMeci(meci);
        System.out.println("Match created successfully.");
    }

    public void viewMeci(Scanner scanner) {
        System.out.print("Enter home team name: ");
        String homeTeamName = scanner.nextLine();

        System.out.print("Enter away team name: ");
        String awayTeamName = scanner.nextLine();

        System.out.print("Enter match date (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        Meci meci = meciDAOService.getMeci(homeTeamName, awayTeamName, date);
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

        Meci existingMeci = meciDAOService.getMeci(homeTeamName, awayTeamName, date);
        if (existingMeci == null) {
            System.out.println("Match not found.");
            return;
        }

        System.out.print("Enter new score for home team: ");
        int scoreHome = scanner.nextInt();

        System.out.print("Enter new score for away team: ");
        int scoreAway = scanner.nextInt();
        scanner.nextLine();

        meciDAOService.updateMeci(homeTeamName, awayTeamName, date, scoreHome, scoreAway);
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

        Meci meci = meciDAOService.getMeci(homeTeamName, awayTeamName, date);
        if (meci != null) {
            meciDAOService.removeMeci(meci);
            System.out.println("Match deleted successfully.");
        } else {
            System.out.println("Match not found.");
        }
    }
}