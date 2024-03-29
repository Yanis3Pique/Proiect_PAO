package proiect.service;

import proiect.dao.*;
import proiect.model.Antrenor;
import proiect.model.Echipa;
import proiect.model.Jucator;
import proiect.model.Stadion;

import java.util.Scanner;

public class EchipaService {
    private EchipaDAOService echipaDAOService;
    private AngajatDAOService antrenorDAOService;
    private StadionDAOService stadionDAOService;

    public EchipaService() {
        this.echipaDAOService = new EchipaDAOService();
        this.antrenorDAOService = new AngajatDAOService();
        this.stadionDAOService = new StadionDAOService();
    }

    public void createEchipa(Scanner scanner) {
        System.out.println("Creating a new Team:");
        System.out.print("Enter team name: ");
        String nume = scanner.nextLine();

        System.out.print("Enter coach's first and last name: ");
        String antrenorNume = scanner.nextLine();
        Antrenor antrenor = (Antrenor) antrenorDAOService.getAngajatByName(antrenorNume.split(" ")[0], antrenorNume.split(" ")[1]);

        System.out.print("Enter stadium name: ");
        String stadionNume = scanner.nextLine();
        Stadion stadion = stadionDAOService.getStadionByName(stadionNume);

        Echipa echipa = new Echipa(0, nume, antrenor, stadion);
        echipaDAOService.addEchipa(echipa);
        System.out.println("Team created successfully.");
    }

    public void viewEchipa(Scanner scanner) {
        System.out.print("Enter team name to view details: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaDAOService.getEchipaByName(nume);
        if (echipa != null) {
            System.out.println(echipa);
        } else {
            System.out.println("Team not found.");
        }
    }

    private void updateCoach(Scanner scanner, Echipa echipa) {
        System.out.print("Enter new coach's first and last name (or press Enter to skip): ");
        String antrenorNume = scanner.nextLine();
        if (!antrenorNume.isEmpty()) {
            String[] names = antrenorNume.split(" ");
            if (names.length < 2) {
                System.out.println("Please enter both first and last name.");
                return;
            }
            Antrenor antrenor = (Antrenor) antrenorDAOService.getAngajatByName(names[0], names[1]);
            if (antrenor != null) {
                echipa.setAntrenor(antrenor);
            } else {
                System.out.println("Coach not found.");
                throw new IllegalArgumentException("Coach not found.");
            }
        }
    }

    private void updateStadium(Scanner scanner, Echipa echipa) {
        System.out.print("Enter new stadium name (or press Enter to skip): ");
        String stadionNume = scanner.nextLine();
        if (!stadionNume.isEmpty()) {
            Stadion stadion = stadionDAOService.getStadionByName(stadionNume);
            if (stadion != null) {
                echipa.setStadion(stadion);
            } else {
                System.out.println("Stadium not found.");
                throw new IllegalArgumentException("Stadium not found.");
            }
        }
    }

    public void updateEchipa(Scanner scanner) {
        System.out.print("Enter the name of the team you want to update: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaDAOService.getEchipaByName(nume);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }

        try {
            updateCoach(scanner, echipa);
            updateStadium(scanner, echipa);
            echipaDAOService.updateEchipa(nume, echipa);
            System.out.println("Team updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Team not updated.");
        }
    }

    public void deleteEchipa(Scanner scanner) {
        System.out.print("Enter the name of the team you want to delete: ");
        String nume = scanner.nextLine();

        Echipa echipa = echipaDAOService.getEchipaByName(nume);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }

        echipaDAOService.removeEchipa(nume);
        System.out.println("Team deleted successfully.");
    }


    public void addJucator(Scanner scanner) {
        System.out.print("Enter the name of the team you want to add a player to: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaDAOService.getEchipaByName(nume);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }
        System.out.print("Enter player's first and last name: ");
        String jucatorNume = scanner.nextLine();
        String[] names = jucatorNume.split(" ");
        if (names.length < 2) {
            System.out.println("Please enter both first and last name.");
            return;
        }
        Jucator jucator = (Jucator) antrenorDAOService.getAngajatByName(names[0], names[1]);
        if (jucator != null) {
            echipa.getJucatori().add(jucator);
            echipaDAOService.updateEchipa(nume, echipa);
            System.out.println("Player added successfully.");
        } else {
            System.out.println("Player not found.");
        }
    }

    public void removeJucator(Scanner scanner) {
        System.out.print("Enter the name of the team you want to remove a player from: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaDAOService.getEchipaByName(nume);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }
        System.out.print("Enter player's first and last name: ");
        String jucatorNume = scanner.nextLine();
        String[] names = jucatorNume.split(" ");
        if (names.length < 2) {
            System.out.println("Please enter both first and last name.");
            return;
        }
        Jucator jucator = (Jucator) antrenorDAOService.getAngajatByName(jucatorNume.split(" ")[0], jucatorNume.split(" ")[1]);
        if (jucator != null) {
            echipa.getJucatori().remove(jucator);
            echipaDAOService.updateEchipa(nume, echipa);
            System.out.println("Player removed successfully.");
        } else {
            System.out.println("Player not found.");
        }
    }

    public void viewJucatori(Scanner scanner) {
        System.out.print("Enter the name of the team you want to view players for: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaDAOService.getEchipaByName(nume);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }
        if (!echipa.getJucatori().isEmpty()) {
            for (Jucator jucator : echipa.getJucatori()) {
                System.out.println(jucator);
            }
        } else {
            System.out.println("No players found.");
        }
    }
}
