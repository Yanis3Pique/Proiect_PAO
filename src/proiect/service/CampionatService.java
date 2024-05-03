package proiect.service;

import proiect.model.Echipa;
import proiect.model.Meci;
import proiect.dao.EchipaDao;
import proiect.dao.MeciDao;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CampionatService {
    private EchipaDao echipaDao;
    private MeciDao meciDao;

    public CampionatService() throws SQLException {
        this.echipaDao = new EchipaDao();
        this.meciDao = new MeciDao();
    }

    public Map<Echipa, Integer> vizualizeazaClasament() throws SQLException {
        List<Echipa> echipe = echipaDao.findAllEchipa();
        List<Meci> meciuri = meciDao.findAllMeci();

        Map<Echipa, Integer> clasament = new HashMap<>();
        for (Echipa echipa : echipe) {
            clasament.put(echipa, 0);
        }

        calculatePoints(meciuri, clasament);

        return clasament.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private void calculatePoints(List<Meci> meciuri, Map<Echipa, Integer> clasament) {
        for (Meci meci : meciuri) {
            Echipa echipa1 = meci.getEchipa1();
            Echipa echipa2 = meci.getEchipa2();
            int puncteEchipa1 = clasament.getOrDefault(echipa1, 0);
            int puncteEchipa2 = clasament.getOrDefault(echipa2, 0);
            if (meci.getScor1() > meci.getScor2()) {
                clasament.put(echipa1, puncteEchipa1 + 3);
            } else if (meci.getScor1() < meci.getScor2()) {
                clasament.put(echipa2, puncteEchipa2 + 3);
            } else {
                clasament.put(echipa1, puncteEchipa1 + 1);
                clasament.put(echipa2, puncteEchipa2 + 1);
            }
        }
    }

    public static void manageTeams(Scanner scanner, EchipaService echipaService) throws SQLException {
        manageTeamsMenu();

        String teamChoice = scanner.nextLine();

        switch (teamChoice) {
            case "1" -> echipaService.createEchipa(scanner);
            case "2" -> echipaService.readEchipa(scanner);
            case "3" -> echipaService.updateEchipa(scanner);
            case "4" -> echipaService.deleteEchipa(scanner);
            case "5" -> echipaService.viewJucatori(scanner);
            case "0" -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageTeamsMenu() {
        System.out.println("\nTeam Management");
        System.out.println("1. Create a new team");
        System.out.println("2. View a team");
        System.out.println("3. Update a team");
        System.out.println("4. Delete a team");
        System.out.println("5. Add a player to a team");
        System.out.println("6. Remove a player from a team");
        System.out.println("7. View team players");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

    public static void manageMatches(Scanner scanner, MeciService meciService) throws SQLException {
        manageMatchesMenu();

        String matchChoice = scanner.nextLine();

        switch (matchChoice) {
            case "1" -> meciService.createMeci(scanner);
            case "2" -> meciService.readMeci(scanner);
            case "3" -> meciService.updateMeci(scanner);
            case "4" -> meciService.deleteMeci(scanner);
            case "0" -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageMatchesMenu() {
        System.out.println("\nMatch Management");
        System.out.println("1. Schedule a new match");
        System.out.println("2. View match details");
        System.out.println("3. Update match score");
        System.out.println("4. Delete a match");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

    public static void manageEmployees(Scanner scanner, AngajatService angajatService) throws SQLException {
        manageEmployeesMenu();

        String employeeChoice = scanner.nextLine();

        switch (employeeChoice) {
            case "1" -> angajatService.createAngajat(scanner);
            case "2" -> angajatService.readAngajat(scanner);
            case "3" -> angajatService.updateAngajat(scanner);
            case "4" -> angajatService.deleteAngajat(scanner);
            case "0" -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageEmployeesMenu() {
        System.out.println("\nEmployee Management");
        System.out.println("1. Hire a new employee");
        System.out.println("2. View employee details");
        System.out.println("3. Update employee information");
        System.out.println("4. Terminate an employee");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

    public static void manageStadiums(Scanner scanner, StadionService stadionService) throws SQLException {
        manageStadiumsMenu();

        String stadiumChoice = scanner.nextLine();

        switch (stadiumChoice) {
            case "1" -> stadionService.createStadion(scanner);
            case "2" -> stadionService.readStadion(scanner);
            case "3" -> stadionService.updateStadion(scanner);
            case "4" -> stadionService.deleteStadion(scanner);
            case "0" -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageStadiumsMenu() {
        System.out.println("\nStadium Management");
        System.out.println("1. Add a new stadium");
        System.out.println("2. View stadium details");
        System.out.println("3. Update stadium information");
        System.out.println("4. Remove a stadium");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

    public static void manageSponsors(Scanner scanner, SponsorService sponsorService) throws SQLException {
        manageSponsorsMenu();

        String sponsorChoice = scanner.nextLine();

        switch (sponsorChoice) {
            case "1" -> sponsorService.createSponsor(scanner);
            case "2" -> sponsorService.readSponsor(scanner);
            case "3" -> sponsorService.updateSponsor(scanner        );
            case "4" -> sponsorService.deleteSponsor(scanner);
            case "0" -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageSponsorsMenu() {
        System.out.println("\nSponsor Management");
        System.out.println("1. Add a new sponsor");
        System.out.println("2. View sponsor details");
        System.out.println("3. Update sponsor information");
        System.out.println("4. Remove a sponsor");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

    public static void manageContracts(Scanner scanner, ContractService contractService, EchipaService echipaService, SponsorService sponsorService) throws SQLException {
        manageContractsMenu();

        String contractChoice = scanner.nextLine();

        switch (contractChoice) {
            case "1" -> contractService.createContract(scanner);
            case "2" -> contractService.readContract(scanner);
            case "3" -> contractService.updateContract(scanner);
            case "4" -> contractService.deleteContract(scanner);
            case "0" -> {
                return;
            }
            default -> System.out.println("Invalid option! Please enter a valid choice.");
        }
    }

    private static void manageContractsMenu() {
        System.out.println("\nContract Management");
        System.out.println("1. Create a new contract");
        System.out.println("2. View contract details");
        System.out.println("3. Update contract details");
        System.out.println("4. Terminate a contract");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

    public static void viewChampionshipStandings(CampionatService campionatService) throws SQLException {
        System.out.println("\nChampionship Standings");
        Map<Echipa, Integer> clasament = campionatService.vizualizeazaClasament();
        if (clasament.isEmpty()) {
            System.out.println("No teams have played matches yet.");
        } else {
            clasament.forEach((echipa, puncte) -> System.out.println(echipa.getNume() + " - Points: " + puncte));
        }
    }

    public static void generalMenu() {
        System.out.println("\nWelcome to Yanis3Pique's Championship Management System");
        System.out.println("1. Manage Teams");
        System.out.println("2. Manage Matches");
        System.out.println("3. Manage Employees");
        System.out.println("4. Manage Stadiums");
        System.out.println("5. Manage Sponsors");
        System.out.println("6. Manage Contracts");
        System.out.println("7. View Championship Standings");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }
}