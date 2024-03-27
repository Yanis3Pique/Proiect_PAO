package proiect.service;

import proiect.dao.StadionDAOService;
import proiect.model.Stadion;
import java.util.Scanner;

public class StadionService {
    private StadionDAOService stadionDAOService;

    public StadionService() {
        this.stadionDAOService = new StadionDAOService();
    }

    public void createStadion(Scanner scanner) {
        System.out.println("Creating a new Stadium:");

        System.out.print("Enter stadium name: ");
        String name = scanner.nextLine();

        System.out.print("Enter stadium capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter stadium location: ");
        String location = scanner.nextLine();

        Stadion stadion = new Stadion(0, name, capacity, location);
        stadionDAOService.addStadion(stadion);
        System.out.println("Stadium created successfully.");
    }

    public void viewStadion(Scanner scanner) {
        System.out.print("Enter stadium name to view details: ");
        String name = scanner.nextLine();

        Stadion stadion = stadionDAOService.getStadionByName(name);
        if (stadion != null) {
            System.out.println(stadion);
        } else {
            System.out.println("Stadium not found.");
        }
    }

    public void updateStadion(Scanner scanner) {
        System.out.println("Updating a Stadium:");

        System.out.print("Enter stadium name: ");
        String name = scanner.nextLine();

        Stadion existingStadion = stadionDAOService.getStadionByName(name);
        if (existingStadion == null) {
            System.out.println("Stadium not found.");
            return;
        }

        System.out.print("Enter new capacity for the stadium: ");
        int newCapacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new location for the stadium: ");
        String newLocation = scanner.nextLine();

        existingStadion.setCapacitate(newCapacity);
        existingStadion.setLocatie(newLocation);

        stadionDAOService.updateStadion(name, existingStadion);
        System.out.println("Stadium updated successfully.");
    }

    public void deleteStadion(Scanner scanner) {
        System.out.println("Deleting a Stadium:");

        System.out.print("Enter stadium name: ");
        String name = scanner.nextLine();

        Stadion stadion = stadionDAOService.getStadionByName(name);
        if (stadion != null) {
            stadionDAOService.removeStadion(stadion);
            System.out.println("Stadium deleted successfully.");
        } else {
            System.out.println("Stadium not found.");
        }
    }
}
