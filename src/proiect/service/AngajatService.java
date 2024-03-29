package proiect.service;

import proiect.dao.AngajatDAOService;
import proiect.model.Angajat;
import proiect.model.Antrenor;
import proiect.model.Jucator;

import java.util.Scanner;

public class AngajatService {
    private AngajatDAOService angajatDAOService;

    public AngajatService() {
        this.angajatDAOService = new AngajatDAOService();
    }

    public void createAngajat(Scanner scanner) {
        System.out.println("Enter type of employee [antrenor/jucator]:");
        String typeOfAngajat = scanner.nextLine().toLowerCase();
        if (!typeOfAngajatValidation(typeOfAngajat)) {
            return;
        }
        angajatInit(scanner, typeOfAngajat);
    }

    public void readAngajat(Scanner scanner) {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        Angajat angajat = angajatDAOService.getAngajatByName(firstName, lastName);
        if (angajat != null) {
            System.out.println(angajat);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void updateAngajat(Scanner scanner) {
        System.out.println("Updating an employee:");
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        Angajat angajat = angajatDAOService.getAngajatByName(firstName, lastName);
        if (angajat == null) {
            System.out.println("Employee not found.");
            return;
        }

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

        angajatDAOService.updateAngajat(angajat);
        System.out.println("Employee updated successfully.");
    }

    public void deleteAngajat(Scanner scanner) {
        System.out.println("Deleting an employee:");
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        Angajat angajat = angajatDAOService.getAngajatByName(firstName, lastName);
        if (angajat != null) {
            angajatDAOService.removeAngajat(angajat);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private boolean typeOfAngajatValidation(String typeOfAngajat) {
        if (!typeOfAngajat.equals("antrenor") && !typeOfAngajat.equals("jucator")) {
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

        if (typeOfAngajat.equals("antrenor")) {
            System.out.println("Enter years of experience:");
            int years = scanner.nextInt();
            scanner.nextLine();
            Antrenor antrenor = new Antrenor(0, firstName, lastName, nationality, age, salary, years);
            angajatDAOService.addAngajat(antrenor);
        } else if (typeOfAngajat.equals("jucator")) {
            System.out.println("Enter player number:");
            int number = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter position:");
            String position = scanner.nextLine();
            Jucator jucator = new Jucator(0, firstName, lastName, nationality, age, salary, number);
            jucator.setPozitie(position);
            angajatDAOService.addAngajat(jucator);
        }
        System.out.println("Employee created successfully.");
    }
}