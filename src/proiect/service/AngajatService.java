package proiect.service;

import proiect.daoservices.AngajatRepositoryService;
import proiect.model.Angajat;
import proiect.model.Antrenor;
import proiect.model.Jucator;
import proiect.utils.FileManagement;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.Scanner;

public class AngajatService {
    private AngajatRepositoryService databaseService;

    public AngajatService() throws SQLException {
        this.databaseService = new AngajatRepositoryService();
    }

    public void createAngajat(Scanner scanner) {
        System.out.println("Enter type of employee [coach/player]:");
        String typeOfAngajat = scanner.nextLine().toLowerCase();
        if (!typeOfAngajatValidation(typeOfAngajat)) { return; }
        angajatInit(scanner, typeOfAngajat);
    }

    public Angajat searchAngajat(Scanner scanner) throws SQLException {
        System.out.println("How do you want to search the employee? [name/id]");
        String option = scanner.nextLine().toLowerCase();
        System.out.println("Enter:");
        switch (option) {
            case "name":
                String name = scanner.nextLine();
                String surname = scanner.nextLine();
                return databaseService.getAngajatByName(name, surname);
            case "id":
                int id = scanner.nextInt();
                scanner.nextLine();
                return databaseService.getAngajatByID(id);
            default:
                System.out.println("Wrong option");
                return null;
        }
    }

    public void readAngajat(Scanner scanner) throws SQLException {
        Angajat angajat = searchAngajat(scanner);
        if (angajat != null) {
            System.out.println(angajat);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void viewAllAngajati() throws SQLException {
        System.out.println("EMPLOYEES:");
        databaseService.printAllEmployees();
        System.out.println();
    }

    private void updateGeneralAttributes(Angajat angajat, Scanner scanner) {
        System.out.println("Enter new first name:");
        String newFirstName = scanner.nextLine();
        System.out.println("Enter new last name:");
        String newLastName = scanner.nextLine();
        System.out.println("Enter new nationality:");
        String newNationality = scanner.nextLine();
        System.out.println("Enter new age:");
        int newAge = scanner.nextInt();
        System.out.println("Enter new salary:");
        double newSalary = scanner.nextDouble();
        scanner.nextLine();

        angajat.setNume(newFirstName);
        angajat.setPrenume(newLastName);
        angajat.setNationalitate(newNationality);
        angajat.setVarsta(newAge);
        angajat.setSalariu(newSalary);
    }

    private void updateSpecificAttributes(Angajat angajat, Scanner scanner) {
        if (angajat instanceof Antrenor antrenor) {
            System.out.println("Enter new years of experience:");
            int newYrsExperience = scanner.nextInt();
            scanner.nextLine();
            antrenor.setAniExperienta(newYrsExperience);
        } else if (angajat instanceof Jucator jucator) {
            System.out.println("Enter new player number:");
            int newNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new position:");
            String newPosition = scanner.nextLine();
            jucator.setNumarTricou(newNumber);
            jucator.setPozitie(newPosition);
        }
    }

    public void updateAngajat(Scanner scanner) throws SQLException {
        System.out.println("Updating an employee:");
        Angajat angajat = searchAngajat(scanner);
        if (angajat == null) {
            System.out.println("Employee not found.");
            return;
        }

        updateGeneralAttributes(angajat, scanner);
        updateSpecificAttributes(angajat, scanner);

        try {
            databaseService.updateAngajat(angajat);
            System.out.println("Employee updated successfully.");
        } catch (InvalidDataException e) {
            System.out.println("Employee could not be updated " + e.getMessage());
        }
    }

    public void deleteAngajat(Scanner scanner) throws SQLException {
        System.out.println("Deleting an employee:");
        Angajat angajat = searchAngajat(scanner);
        databaseService.removeAngajat(angajat);
        FileManagement.scriereFisierChar("audit.txt", "stergere angajat " + angajat.getPrenume() + " " + angajat.getNume());
        System.out.println("Employee deleted successfully.");
    }

    private boolean typeOfAngajatValidation(String typeOfAngajat) {
        if (!typeOfAngajat.equals("coach") && !typeOfAngajat.equals("player")) {
            System.out.println("Invalid type of employee.");
            return false;
        }
        return true;
    }

    private void angajatInit(Scanner scanner, String typeOfAngajat) {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter nationality:");
        String nationality = scanner.nextLine();
        System.out.println("Enter age:");
        int age = scanner.nextInt();
        System.out.println("Enter salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        if (typeOfAngajat.equals("coach")) {
            System.out.println("Enter years of experience:");
            int years = scanner.nextInt();
            scanner.nextLine();
            Antrenor antrenor = new Antrenor(0, 0, firstName, lastName, nationality, age, salary, years);
            try {
                databaseService.addAngajat(antrenor);
                FileManagement.scriereFisierChar("audit.txt", "adaugare antrenor " + firstName + " " + lastName);
            } catch (InvalidDataException e) {
                System.out.println("Coach could not be created " + e.getMessage());
            }
        } else if (typeOfAngajat.equals("player")) {
            System.out.println("Enter player number:");
            int number = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter position:");
            String position = scanner.nextLine();
            System.out.println("Enter team id:");
            int id_echipa = scanner.nextInt();
            scanner.nextLine();
            Jucator jucator = new Jucator(0, 0, firstName, lastName, nationality, age, salary, id_echipa, position, number);
            jucator.setPozitie(position);
            try {
                databaseService.addAngajat(jucator);
                FileManagement.scriereFisierChar("audit.txt", "adaugare jucator " + firstName + " " + lastName);
            } catch (InvalidDataException e) {
                System.out.println("Player could not be created " + e.getMessage());
            }
        }
    }
}