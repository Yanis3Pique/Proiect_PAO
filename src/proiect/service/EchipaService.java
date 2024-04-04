package proiect.service;

import proiect.daoservices.*;
import proiect.model.Antrenor;
import proiect.model.Echipa;
import proiect.model.Jucator;
import proiect.model.Stadion;

import java.util.Scanner;

public class EchipaService {
    private EchipaRepositoryService echipaRepositoryService;
    private AngajatRepositoryService antrenorDAOService;
    private StadionRepositoryService stadionRepositoryService;

    public EchipaService() {
        this.echipaRepositoryService = new EchipaRepositoryService();
        this.antrenorDAOService = new AngajatRepositoryService();
        this.stadionRepositoryService = new StadionRepositoryService();
    }

    public void createEchipa(Scanner scanner) {
        System.out.println("Creating a new Team:");
        String nume;
        do {
            System.out.print("Enter team name (length >= 3): ");
            nume = scanner.nextLine();
        } while (nume.length() < 3);

        System.out.print("Enter coach's first and last name: ");
        Antrenor antrenor;
        while(true) {
            String antrenorNume = scanner.nextLine();
            if (antrenorNume.split(" ").length == 2) {
                antrenor = (Antrenor) antrenorDAOService.getAngajatByName(antrenorNume.split(" ")[0], antrenorNume.split(" ")[1]);
                break;
            } else {
                System.out.println("Invalid input. Please make sure to include the first and a last name.");
            }
        }

        System.out.print("Enter stadium name: ");
        String stadionNume = scanner.nextLine();
        Stadion stadion = stadionRepositoryService.getStadionByName(stadionNume);

        Echipa echipa = new Echipa(0, nume, antrenor, stadion);
        echipaRepositoryService.addEchipa(echipa);
        System.out.println("Team created successfully.");
    }

    public void viewEchipa(Scanner scanner) {
        System.out.print("Enter team name to view details: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaRepositoryService.getEchipaByName(nume);
        if (echipa != null) {
            System.out.println(echipa);
        } else {
            System.out.println("Team not found.");
        }
    }

    private void updateCoach(Scanner scanner, Echipa echipa) {
        String antrenorNume;
        while (true) {
            System.out.print("Enter new coach's first and last name (or press Enter to skip): ");
            antrenorNume = scanner.nextLine();
            if (antrenorNume.isEmpty()) {
                break;
            } else {
                String[] names = antrenorNume.split(" ");
                if (names.length == 2) {
                    Antrenor antrenor = (Antrenor) antrenorDAOService.getAngajatByName(names[0], names[1]);
                    if (antrenor != null) {
                        echipa.setAntrenor(antrenor);
                        break;
                    } else {
                        System.out.println("Coach not found.");
                        throw new IllegalArgumentException("Stadium not found.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter both first and last name, separated by a space.");
                }
            }
        }
    }

    private void updateStadium(Scanner scanner, Echipa echipa) {
        System.out.print("Enter new stadium name (or press Enter to skip): ");
        String stadionNume = scanner.nextLine();
        if (!stadionNume.isEmpty()) {
            Stadion stadion = stadionRepositoryService.getStadionByName(stadionNume);
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
        Echipa echipa = echipaRepositoryService.getEchipaByName(nume);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }

        try {
            updateCoach(scanner, echipa);
            updateStadium(scanner, echipa);
            echipaRepositoryService.updateEchipa(nume, echipa);
            System.out.println("Team updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Team not updated.");
        }
    }

    public void deleteEchipa(Scanner scanner) {
        System.out.print("Enter the name of the team you want to delete: ");
        String nume = scanner.nextLine();

        Echipa echipa = echipaRepositoryService.getEchipaByName(nume);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }

        echipaRepositoryService.removeEchipa(nume);
        System.out.println("Team deleted successfully.");
    }


    public void addJucator(Scanner scanner) {
        System.out.print("Enter the name of the team you want to add a player to: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaRepositoryService.getEchipaByName(nume);
        if (echipa == null) {System.out.println("Team not found."); return;}
        System.out.print("Enter player's first and last name: ");
        String jucatorNume;
        String[] names;
        while (true) {
            System.out.println("Please enter the full name of the player (First Last):");
            jucatorNume = scanner.nextLine();
            names = jucatorNume.split(" ");
            if (names.length >= 2) {break;}
            else {System.out.println("Invalid input. Please enter both first and last name, separated by a space.");}
        }
        Jucator jucator = (Jucator) antrenorDAOService.getAngajatByName(names[0], names[1]);
        if (jucator != null) {
            echipa.getJucatori().add(jucator);
            echipaRepositoryService.updateEchipa(nume, echipa);
            System.out.println("Player added successfully.");
        } else {System.out.println("Player not found.");}
    }

    public void removeJucator(Scanner scanner) {
        System.out.print("Enter the name of the team you want to remove a player from: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaRepositoryService.getEchipaByName(nume);
        if (echipa == null) {System.out.println("Team not found."); return;}
        System.out.print("Enter player's first and last name: ");
        System.out.print("Enter player's first and last name: ");
        String jucatorNume;
        String[] names;
        while (true) {
            System.out.println("Please enter the full name of the player (First Last):");
            jucatorNume = scanner.nextLine();
            names = jucatorNume.split(" ");
            if (names.length >= 2) {break;}
            else {System.out.println("Invalid input. Please enter both first and last name, separated by a space.");}
        }
        Jucator jucator = (Jucator) antrenorDAOService.getAngajatByName(jucatorNume.split(" ")[0], jucatorNume.split(" ")[1]);
        if (jucator != null) {
            echipa.getJucatori().remove(jucator);
            echipaRepositoryService.updateEchipa(nume, echipa);
            System.out.println("Player removed successfully.");
        } else {System.out.println("Player not found.");}
    }

    public void viewJucatori(Scanner scanner) {
        System.out.print("Enter the name of the team you want to view players for: ");
        String nume = scanner.nextLine();
        Echipa echipa = echipaRepositoryService.getEchipaByName(nume);
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
