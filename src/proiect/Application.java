package proiect;

import proiect.service.*;

import java.sql.SQLException;
import java.util.Scanner;

import static proiect.service.CampionatService.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        AngajatService angajatService = new AngajatService();
        EchipaService echipaService = new EchipaService();
        MeciService meciService = new MeciService();
        SponsorService sponsorService = new SponsorService();
        StadionService stadionService = new StadionService();
        CampionatService campionatService = new CampionatService();
        ContractService contractService = new ContractService();
        System.out.println("\nWelcome to Yanis3Pique's Championship Management System\n");

        while (true) {
            generalMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> manageTeams(scanner, echipaService);
                case "2" -> manageMatches(scanner, meciService);
                case "3" -> manageEmployees(scanner, angajatService);
                case "4" -> manageStadiums(scanner, stadionService);
                case "5" -> manageSponsors(scanner, sponsorService);
                case "6" -> manageContracts(scanner, contractService, echipaService, sponsorService);
                case "7" -> viewChampionshipStandings(campionatService);
                case "0" -> {System.out.println("Exiting..."); return;}
                default -> System.out.println("Invalid option! Please enter a number between 0 and 7.");
            }
        }
    }
}
