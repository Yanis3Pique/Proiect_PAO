package proiect.service;

import proiect.daoservices.*;
import proiect.model.Antrenor;
import proiect.model.Echipa;
import proiect.model.Jucator;
import proiect.model.Stadion;
import proiect.utils.FileManagement;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.Scanner;

import static proiect.utils.Constants.AUDIT_FILE;

public class EchipaService {
    private EchipaRepositoryService databaseService;
    private AngajatRepositoryService antrenorRepositoryService;
    private StadionRepositoryService stadionRepositoryService;

    public EchipaService() throws SQLException {
        this.databaseService = new EchipaRepositoryService();
        this.antrenorRepositoryService = new AngajatRepositoryService();
        this.stadionRepositoryService = new StadionRepositoryService();
    }

    public void createEchipa(Scanner scanner) throws SQLException {
        System.out.println("Creating a new Team:");
        String nume;
        do {
            System.out.print("Enter team name (length >= 3): ");
            nume = scanner.nextLine();
        } while (nume.length() < 3);

        Antrenor antrenor;
        while (true) {
            System.out.print("Enter coach's first and last name: ");
            String antrenorNume = scanner.nextLine();
            if (antrenorNume.split(" ").length == 2) {
                String firstName = antrenorNume.split(" ")[0];
                String lastName = antrenorNume.split(" ")[1];
                if (antrenorRepositoryService.getAngajatByName(firstName, lastName) instanceof Jucator) {
                    System.out.println("Coach cannot be a player.");
                    return;
                }
                antrenor = (Antrenor) antrenorRepositoryService.getAngajatByName(firstName, lastName);
                if (antrenor == null) {
                    System.out.println("Coach not found.");
                    return;
                }
                break;
            } else {
                System.out.println("Invalid input. Please make sure to include the first and last name.");
            }
        }


        System.out.print("Enter stadium name: ");
        String stadionNume = scanner.nextLine();
        Stadion stadion = stadionRepositoryService.getStadionByName(stadionNume);
        if (stadion == null) {
            System.out.println("Stadium not found.");
            return;
        }

        Echipa echipa = new Echipa(0, nume, antrenor, stadion);

        try {
            databaseService.addEchipa(echipa);
            FileManagement.scriereFisierChar(AUDIT_FILE, "create team " + nume);
        } catch (InvalidDataException e) {
            System.out.println("Team not created.");
        }
    }

    public Echipa searchEchipa(Scanner scanner) throws SQLException {
        System.out.println("How do you want to search the team? [name/id]");
        String option = scanner.nextLine().toLowerCase();
        System.out.println("Enter:");
        switch (option) {
            case "name":
                String nume = scanner.nextLine();
                return databaseService.getEchipaByName(nume);
            case "id":
                int id = scanner.nextInt();
                scanner.nextLine();
                return databaseService.getEchipaById(id);
            default:
                System.out.println("Invalid option.");
                return null;
        }
    }

    public void readEchipa(Scanner scanner) throws SQLException {
        Echipa echipa = searchEchipa(scanner);
        if (echipa != null) {
            System.out.println(echipa);
        }
        else {
            System.out.println("Team not found.");
        }
    }

    private void updateCoach(Scanner scanner, Echipa echipa) throws SQLException {
        String antrenorNume;
        while (true) {
            System.out.print("Enter new coach's first and last name: ");
            antrenorNume = scanner.nextLine();
            if (antrenorNume.isEmpty()) {
                break;
            } else {
                String[] names = antrenorNume.split(" ");
                if (names.length == 2) {
                    Antrenor antrenor = (Antrenor) antrenorRepositoryService.getAngajatByName(names[0], names[1]);
                    if (antrenor != null) {
                        echipa.setAntrenor(antrenor);
                        break;
                    } else {
                        System.out.println("Coach not found.");
                        throw new IllegalArgumentException("Coach not found.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter both first and last name, separated by a space.");
                }
            }
        }
    }

    private void updateStadium(Scanner scanner, Echipa echipa) throws SQLException {
        System.out.print("Enter new stadium name: ");
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

    public void updateEchipa(Scanner scanner) throws SQLException {
        System.out.print("Enter the name of the team you want to update: ");
        String nume = scanner.nextLine();
        Echipa echipa = databaseService.getEchipaByName(nume);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }

        try {
            updateCoach(scanner, echipa);
            updateStadium(scanner, echipa);
            databaseService.updateEchipa(nume, echipa);
            FileManagement.scriereFisierChar(AUDIT_FILE, "update team " + nume);
        } catch (InvalidDataException e) {
            System.out.println("Team not updated.");
        }
    }

    public void deleteEchipa(Scanner scanner) throws SQLException {
        Echipa echipa = searchEchipa(scanner);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }

        databaseService.removeEchipa(echipa.getNume());
        FileManagement.scriereFisierChar(AUDIT_FILE, "delete team " + echipa.getNume());
        System.out.println("Team deleted successfully.");
    }

    public void viewJucatori(Scanner scanner) throws SQLException {
        Echipa echipa = searchEchipa(scanner);
        if (echipa == null) {
            System.out.println("Team not found.");
            return;
        }
        databaseService.getJucatori(echipa);
    }
}
