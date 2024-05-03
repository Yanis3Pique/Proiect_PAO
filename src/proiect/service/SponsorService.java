package proiect.service;

import proiect.daoservices.SponsorRepositoryService;
import proiect.model.Sponsor;
import proiect.utils.FileManagement;
import proiect.utils.InvalidDataException;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

public class SponsorService {
    private SponsorRepositoryService databaseService;

    public SponsorService() throws SQLException {
        this.databaseService = new SponsorRepositoryService();
    }

    public void createSponsor(Scanner scanner) {
        System.out.println("Creating a new Sponsor:");
        String name = getSponsorName(scanner);
        String country = getSponsorCountry(scanner);
        Sponsor sponsor = new Sponsor(0, name, country);

        try {
            databaseService.addSponsor(sponsor);
            FileManagement.scriereFisierChar("audit.txt", "creare sponsor " + name);
            System.out.println("Sponsor created successfully.");
        } catch (InvalidDataException e) {
            System.out.println("Creation failed: " + e.getMessage());
        }
    }

    private String getSponsorName(Scanner scanner) {
        String name = "";
        while (name.trim().isEmpty()) {
            System.out.print("Enter sponsor name (cannot be empty): ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Sponsor name cannot be empty. Please enter a valid name.");
            }
        }
        return name;
    }

    private String getSponsorCountry(Scanner scanner) {
        String country = "";
        while (country.length() < 3) {
            System.out.print("Enter sponsor country (>= 3 characters): ");
            country = scanner.nextLine();
            if (country.length() < 3) {
                System.out.println("Sponsor country >= 3 characters. Please enter a valid country.");
            }
        }
        return country;
    }

    public Sponsor searchSponsor(Scanner scanner) throws SQLException {
        System.out.println("How do you want to search the Sponsor? [name/id]");
        String option = scanner.nextLine().toLowerCase();
        System.out.println("Enter:");
        switch (option) {
            case "name":
                String name = scanner.nextLine();
                return databaseService.getSponsorByName(name);
            case "id":
                int id = scanner.nextInt();
                scanner.nextLine();
                return databaseService.getSponsorById(id);
            default:
                System.out.println("wrong option");
                return null;
        }
    }

    public void readSponsor(Scanner scanner) throws SQLException {
        Sponsor sponsor = searchSponsor(scanner);
        if (sponsor != null) {
            System.out.println(sponsor);
        } else {
            System.out.println("Stadium not found.");
        }
    }

    public void viewAllSponsors() throws SQLException {
        System.out.println("SPONSORS:");
        databaseService.printAllSponsors();
        System.out.println();
    }

    public void updateSponsor(Scanner scanner) throws SQLException {
        System.out.println("Updating a Sponsor:");
        Sponsor existingSponsor = searchSponsor(scanner);
        if (existingSponsor == null) {
            System.out.println("Sponsor not found.");
            return;
        }
        String newCountry = getNewSponsorCountry(scanner);
        existingSponsor.setCountry(newCountry);

        try {
            databaseService.updateSponsor(existingSponsor.getName(), existingSponsor);
            System.out.println("Sponsor updated successfully.");
        } catch (InvalidDataException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    private String getNewSponsorCountry(Scanner scanner) {
        String newCountry = "";
        while (newCountry.length() < 3) {
            System.out.print("Enter new country for the sponsor (>= 3 characters): ");
            newCountry = scanner.nextLine();
            if (newCountry.length() < 3) {
                System.out.println("Sponsor country >= 3 characters. Please enter a valid country.");
            }
        }
        return newCountry;
    }

    public void deleteSponsor(Scanner scanner) throws SQLException {
        System.out.println("Deleting a Sponsor:");
        Sponsor sponsor = searchSponsor(scanner);
        if (sponsor != null) {
            databaseService.removeSponsor(sponsor);
            FileManagement.scriereFisierChar("audit.txt", "stergere sponsor " + sponsor.getName());
            System.out.println("Sponsor deleted successfully.");
        } else {
            System.out.println("Sponsor not found.");
        }
    }
}