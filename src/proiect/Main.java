package proiect;

import proiect.model.Echipa;
import proiect.service.*;

import java.util.Map;
import java.util.Scanner;

import static proiect.service.CampionatService.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService angajatService = new AngajatService();
        EchipaService echipaService = new EchipaService();
        MeciService meciService = new MeciService();
        SponsorService sponsorService = new SponsorService();
        StadionService stadionService = new StadionService();
        CampionatService campionatService = new CampionatService();
        ContractService contractService = new ContractService();

        while (true) {
            generalMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageTeams(scanner, echipaService);
                case 2 -> manageMatches(scanner, meciService);
                case 3 -> manageEmployees(scanner, angajatService);
                case 4 -> manageStadiums(scanner, stadionService);
                case 5 -> manageSponsors(scanner, sponsorService);
                case 6 -> manageContracts(scanner, contractService, echipaService, sponsorService);
                case 7 -> viewChampionshipStandings(campionatService);
                case 0 -> {System.out.println("Exiting..."); return;}
                default -> System.out.println("Invalid option! Please enter a number between 0 and 7.");
            }
        }
    }
}
