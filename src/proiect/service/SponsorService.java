package proiect.service;

import proiect.daoservices.SponsorRepositoryService;
import proiect.model.Sponsor;
import java.util.Scanner;

public class SponsorService {
    private SponsorRepositoryService sponsorRepositoryService;

    public SponsorService() {
        this.sponsorRepositoryService = new SponsorRepositoryService();
    }

    public void createSponsor(Scanner scanner) {
        System.out.println("Creating a new Sponsor:");

        String name = getSponsorName(scanner);
        String country = getSponsorCountry(scanner);

        Sponsor sponsor = new Sponsor(0, name, country);
        sponsorRepositoryService.addSponsor(sponsor);
        System.out.println("Sponsor created successfully.");
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

    public void viewSponsor(Scanner scanner) {
        System.out.print("Enter sponsor name to view details: ");
        String name = scanner.nextLine();

        Sponsor sponsor = sponsorRepositoryService.getSponsorByName(name);
        if (sponsor != null) {
            System.out.println(sponsor);
        } else {
            System.out.println("Sponsor not found.");
        }
    }

    public void updateSponsor(Scanner scanner) {
        System.out.println("Updating a Sponsor:");

        System.out.print("Enter sponsor name: ");
        String name = scanner.nextLine();

        Sponsor existingSponsor = sponsorRepositoryService.getSponsorByName(name);
        if (existingSponsor == null) {
            System.out.println("Sponsor not found.");
            return;
        }

        String newCountry = getNewSponsorCountry(scanner);

        existingSponsor.setCountry(newCountry);

        sponsorRepositoryService.updateSponsor(name, existingSponsor);
        System.out.println("Sponsor updated successfully.");
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

    public void deleteSponsor(Scanner scanner) {
        System.out.println("Deleting a Sponsor:");

        System.out.print("Enter sponsor name: ");
        String name = scanner.nextLine();

        Sponsor sponsor = sponsorRepositoryService.getSponsorByName(name);
        if (sponsor != null) {
            sponsorRepositoryService.removeSponsor(sponsor);
            System.out.println("Sponsor deleted successfully.");
        } else {
            System.out.println("Sponsor not found.");
        }
    }
}