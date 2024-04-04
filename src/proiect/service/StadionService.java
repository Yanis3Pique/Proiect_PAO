package proiect.service;

import proiect.daoservices.StadionRepositoryService;
import proiect.model.Stadion;
import java.util.Scanner;

public class StadionService {
    private StadionRepositoryService stadionRepositoryService;

    public StadionService() {
        this.stadionRepositoryService = new StadionRepositoryService();
    }

    public void createStadion(Scanner scanner) {
        System.out.println("Creating a new Stadium:");
        String name = getStadiumName(scanner);
        int capacity = getStadiumCapacity(scanner);
        String location = getStadiumLocation(scanner);
        Stadion stadion = new Stadion(0, name, capacity, location);
        stadionRepositoryService.addStadion(stadion);
        System.out.println("Stadium created successfully.");
    }

    private String getStadiumName(Scanner scanner) {
        String name = "";
        while (name == null || name.trim().isEmpty()) {
            System.out.print("Enter stadium name: ");
            name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Stadium name cannot be empty. Please enter a valid name.");
            }
        }
        return name;
    }

    private int getStadiumCapacity(Scanner scanner) {
        int capacity = 0;
        while (capacity <= 0) {
            System.out.print("Enter stadium capacity: ");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number. Please enter a valid capacity.");
                scanner.next();
                System.out.print("Enter stadium capacity: ");
            }
            capacity = scanner.nextInt();
            scanner.nextLine();
            if (capacity <= 0) {
                System.out.println("Capacity must be greater than 0. Please enter a valid capacity.");
            }
        }
        return capacity;
    }

    private String getStadiumLocation(Scanner scanner) {
        String location = "";
        while (location == null || location.trim().isEmpty()) {
            System.out.print("Enter stadium location: ");
            location = scanner.nextLine();
            if (location == null || location.trim().isEmpty()) {
                System.out.println("Stadium location cannot be empty. Please enter a valid location.");
            }
        }
        return location;
    }

    public void viewStadion(Scanner scanner) {
        System.out.print("Enter stadium name to view details: ");
        String name = scanner.nextLine();
        Stadion stadion = stadionRepositoryService.getStadionByName(name);
        if (stadion != null) {
            System.out.println(stadion);
        } else {
            System.out.println("Stadium not found.");
        }
    }

    public void updateStadion(Scanner scanner) {
        System.out.println("Updating a Stadium:");
        Stadion existingStadion = getStadiumByName(scanner);
        if (existingStadion == null) {
            System.out.println("Stadium not found.");
            return;
        }
        int newCapacity = getNewStadiumCapacity(scanner);
        String newLocation = getNewStadiumLocation(scanner);
        existingStadion.setCapacitate(newCapacity);
        existingStadion.setLocatie(newLocation);
        stadionRepositoryService.updateStadion(existingStadion.getNume(), existingStadion);
        System.out.println("Stadium updated successfully.");
    }

    private Stadion getStadiumByName(Scanner scanner) {
        System.out.print("Enter stadium name: ");
        String name = scanner.nextLine();
        return stadionRepositoryService.getStadionByName(name);
    }

    private int getNewStadiumCapacity(Scanner scanner) {
        int newCapacity = -1;
        while (newCapacity <= 0) {
            System.out.print("Enter new capacity for the stadium (must be greater than 0): ");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number. Please enter a valid capacity.");
                scanner.next();
                System.out.print("Enter new capacity for the stadium (must be greater than 0): ");
            }
            newCapacity = scanner.nextInt();
            scanner.nextLine();
            if (newCapacity <= 0) {
                System.out.println("Capacity must be greater than 0. Please enter a valid capacity.");
            }
        }
        return newCapacity;
    }

    private String getNewStadiumLocation(Scanner scanner) {
        String newLocation = "";
        while (newLocation.length() < 3) {
            System.out.print("Enter new location for the stadium (must be longer than 3 characters): ");
            newLocation = scanner.nextLine();
            if (newLocation.length() < 3) {
                System.out.println("Location must be longer than 3 characters. Please enter a valid location.");
            }
        }
        return newLocation;
    }

    public void deleteStadion(Scanner scanner) {
        System.out.println("Deleting a Stadium:");
        System.out.print("Enter stadium name: ");
        String name = scanner.nextLine();
        Stadion stadion = stadionRepositoryService.getStadionByName(name);
        if (stadion != null) {
            stadionRepositoryService.removeStadion(stadion);
            System.out.println("Stadium deleted successfully.");
        } else {
            System.out.println("Stadium not found.");
        }
    }
}
