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

        System.out.print("Enter sponsor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter sponsor country: ");
        String country = scanner.nextLine();

        Sponsor sponsor = new Sponsor(0, name, country);
        sponsorRepositoryService.addSponsor(sponsor);
        System.out.println("Sponsor created successfully.");
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

        System.out.print("Enter new country for the sponsor: ");
        String newCountry = scanner.nextLine();

        existingSponsor.setCountry(newCountry);

        sponsorRepositoryService.updateSponsor(name, existingSponsor);
        System.out.println("Sponsor updated successfully.");
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