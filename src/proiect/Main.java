package proiect;

import proiect.service.ServiceCampionat;
import proiect.model.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final ServiceCampionat service = new ServiceCampionat();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            menu();

            int action = scanner.nextInt();
            scanner.nextLine();

            String numeEchipa;
            String prenumeJucator;
            String numeJucator;
            Stadion stadion = null;
            String numeStadion;
            int capacitateStadion;
            String locatieStadion;
            String dataMeciului;
            String numeEchipa1;
            String numeEchipa2;
            Echipa echipa;
            boolean actualizareReusita;
            String numeAntrenor;
            String prenumeAntrenor;
            Antrenor antrenor;
            Sponsor sponsor;
            String taraSponsor;
            String numeSponsor;

            switch (action) {
                case 1:
                    System.out.print("Introdu numele echipei: ");
                    numeEchipa = scanner.nextLine();


                    System.out.print("Introdu numele antrenorului: ");
                    numeAntrenor = scanner.nextLine();

                    System.out.print("Introdu prenumele antrenorului: ");
                    prenumeAntrenor = scanner.nextLine();

                    System.out.print("Introdu nationalitatea antrenorului: ");
                    String nationalitateAntrenor = scanner.nextLine();

                    System.out.print("Introdu varsta antrenorului: ");
                    int varstaAntrenor = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Introdu salariul antrenorului: ");
                    double salariuAntrenor = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Introdu anii de experienta ai antrenorului: ");
                    int aniExperienta = scanner.nextInt();
                    scanner.nextLine();

                    antrenor = new Antrenor(numeAntrenor, prenumeAntrenor, nationalitateAntrenor, varstaAntrenor, salariuAntrenor, aniExperienta);


                    System.out.print("Introdu numele stadionului: ");
                    numeStadion = scanner.nextLine();

                    System.out.print("Introdu capacitatea stadionului: ");
                    capacitateStadion = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Introdu locatia stadionului: ");
                    locatieStadion = scanner.nextLine();

                    stadion = new Stadion(numeStadion, capacitateStadion, locatieStadion);


                    echipa = new Echipa(numeEchipa, antrenor, stadion);
                    service.adaugaEchipa(echipa);
                    System.out.println("Echipa adaugata cu succes.");
                    break;

                case 2:
                    System.out.print("Introdu numele echipei pe care vrei sa o elimini: ");
                    String numeEchipaDeEliminat = scanner.nextLine();

                    boolean echipaEliminata = service.eliminaEchipa(numeEchipaDeEliminat);
                    if (echipaEliminata) {
                        System.out.println("Echipa a fost eliminata cu succes.");
                    } else {
                        System.out.println("Echipa nu a fost gasita.");
                    }
                    break;

                case 3:
                    System.out.print("Introdu numele echipei unde vrei sa adaugi jucatorul: ");
                    numeEchipa = scanner.nextLine();

                    System.out.print("Introdu numele jucatorului: ");
                    numeJucator = scanner.nextLine();

                    System.out.print("Introdu prenumele jucatorului: ");
                    prenumeJucator = scanner.nextLine();

                    System.out.print("Introdu nationalitatea jucatorului: ");
                    String nationalitateJucator = scanner.nextLine();

                    System.out.print("Introdu varsta jucatorului: ");
                    int varstaJucator = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Introdu salariul jucatorului: ");
                    double salariuJucator = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Introdu pozitia jucatorului: ");
                    String pozitie = scanner.nextLine();

                    System.out.print("Introdu numarul tricoului: ");
                    int numarTricou = scanner.nextInt();
                    scanner.nextLine();

                    Jucator jucator = new Jucator(numeJucator, prenumeJucator, nationalitateJucator, varstaJucator, salariuJucator, pozitie, numarTricou);

                    service.adaugaJucatorLaEchipa(numeEchipa, jucator);
                    System.out.println("Jucatorul a fost adaugat cu succes in echipa.");
                    break;

                case 4:
                    System.out.print("Introdu numele echipei de unde vrei sa elimini jucatorul: ");
                    numeEchipa = scanner.nextLine();

                    System.out.print("Introdu numele jucatorului pe care vrei sa il elimini: ");
                    numeJucator = scanner.nextLine();

                    System.out.print("Introdu prenumele jucatorului pe care vrei sa il elimini: ");
                    prenumeJucator = scanner.nextLine();

                    boolean jucatorEliminat = service.eliminaJucatorDinEchipa(numeEchipa, numeJucator, prenumeJucator);
                    if (jucatorEliminat) {
                        System.out.println("Jucatorul a fost eliminat cu succes din echipa.");
                    } else {
                        System.out.println("Jucatorul nu a fost gasit in echipa specificata.");
                    }
                    break;

                case 5:
                    System.out.print("Introdu numele echipei gazda: ");
                    numeEchipa1 = scanner.nextLine();
                    Echipa echipa1 = service.cautaEchipaDupaNume(numeEchipa1);
                    if (echipa1 == null) {
                        System.out.println("Echipa gazda nu a fost gasita.");
                        break;
                    }

                    System.out.print("Introdu numele echipei oaspete: ");
                    numeEchipa2 = scanner.nextLine();
                    Echipa echipa2 = service.cautaEchipaDupaNume(numeEchipa2);
                    if (echipa2 == null) {
                        System.out.println("Echipa oaspete nu a fost gasita.");
                        break;
                    }

                    System.out.print("Introdu data meciului (format YYYY-MM-DD): ");
                    dataMeciului = scanner.nextLine();

                    System.out.print("Introdu scorul echipei gazda: ");
                    int scorEchipa1 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Introdu scorul echipei oaspete: ");
                    int scorEchipa2 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Vreti ca meciul sa se joace pe teren neutru? Raspundeti cu Da/Nu");
                    boolean quit1 = false;

                    while(!quit1) {
                        String optiuneStadion = scanner.nextLine().toLowerCase();
                        switch(optiuneStadion){
                            case "da":
                                quit1 = true;
                                System.out.print("Introduceti numele stadionului: ");
                                numeStadion = scanner.nextLine();

                                System.out.print("Introduceti capacitatea stadionului: ");
                                capacitateStadion = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Introduceti locatia stadionului: ");
                                locatieStadion = scanner.nextLine();

                                stadion = new Stadion(numeStadion, capacitateStadion, locatieStadion);
                                break;

                            case "nu":
                                quit1 = true;
                                stadion = echipa1.getStadion();
                                break;

                            default:
                                System.out.println("Optiune invalida. Te rog alege din nou.");
                                break;
                        }
                    }

                    service.adaugaMeci(echipa1, echipa2, dataMeciului, scorEchipa1, scorEchipa2, stadion);
                    System.out.println("Meciul a fost adaugat cu succes.");
                    break;

                case 6:
                    System.out.print("Introdu data meciului care trebuie actualizat (format YYYY-MM-DD): ");
                    dataMeciului = scanner.nextLine();

                    System.out.print("Introdu numele echipei gazda a meciului care trebuie actualizat: ");
                    numeEchipa1 = scanner.nextLine();

                    System.out.print("Introdu numele echipei oaspete a meciului care trebuie actualizat: ");
                    numeEchipa2 = scanner.nextLine();

                    System.out.print("Introdu noul scor pentru echipa gazda: ");
                    int scorNouEchipa1 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Introdu noul scor pentru echipa oaspete: ");
                    int scorNouEchipa2 = scanner.nextInt();
                    scanner.nextLine();

                    actualizareReusita = service.actualizeazaRezultatMeci(dataMeciului, numeEchipa1, numeEchipa2, scorNouEchipa1, scorNouEchipa2);
                    if (actualizareReusita) {
                        System.out.println("Rezultatul meciului a fost actualizat cu succes.");
                    } else {
                        System.out.println("Actualizarea nu a reusit. Verifica informatiile introduse.");
                    }
                    break;

                case 7:
                    Map<Echipa, Integer> clasament = service.vizualizeazaClasament();
                    System.out.println("Clasamentul campionatului este urmatorul:");
                    int pozitieClasament = 1;
                    for (Map.Entry<Echipa, Integer> entry : clasament.entrySet()) {
                        echipa = entry.getKey();
                        Integer puncte = entry.getValue();
                        System.out.println(pozitieClasament + ". " + echipa.getNume() + "   ---   " + puncte);
                        pozitieClasament++;
                    }
                    break;

                case 8:
                    System.out.print("Introdu numele jucatorului pe care vrei sa il cauti: ");
                    String numeJucatorCautat = scanner.nextLine();

                    List<Jucator> jucatoriGasiti = service.cautaJucatorDupaNume(numeJucatorCautat);
                    if (jucatoriGasiti.isEmpty()) {
                        System.out.println("Nu a fost gasit niciun jucator cu numele " + numeJucatorCautat + ".");
                    } else {
                        System.out.println("Jucatori gasiti:");
                        for (Jucator jucatorIterator : jucatoriGasiti) {
                            System.out.println(jucatorIterator);
                        }
                    }
                    break;

                case 9:
                    System.out.print("Introdu data pentru care vrei sa vizualizezi meciurile (format YYYY-MM-DD): ");
                    String dataCautata = scanner.nextLine();

                    List<Meci> meciuriGasite = service.vizualizeazaMeciuriPentruData(dataCautata);
                    if (meciuriGasite.isEmpty()) {
                        System.out.println("Nu au fost gasite meciuri pentru data " + dataCautata + ".");
                    } else {
                        System.out.println("Meciuri gasite pentru data " + dataCautata + ":");
                        for (Meci meci : meciuriGasite) {
                            System.out.println(meci);
                        }
                    }
                    break;

                case 10:
                    System.out.print("Introdu numele curent al antrenorului: ");
                    String numeCurent = scanner.nextLine();

                    System.out.print("Introdu prenumele curent al antrenorului: ");
                    String prenumeCurent = scanner.nextLine();

                    antrenor = service.cautaAntrenorDupaNumeSiPrenume(numeCurent, prenumeCurent);

                    if (antrenor != null) {
                        System.out.print("Introdu noul nume al antrenorului: ");
                        String numeNou = scanner.nextLine();

                        System.out.print("Introdu noul prenume al antrenorului: ");
                        String prenumeNou = scanner.nextLine();

                        System.out.print("Introdu noua varsta a antrenorului: ");
                        int varstaNoua = scanner.nextInt();

                        System.out.print("Introdu noul salariu al antrenorului: ");
                        double salariuNou = scanner.nextDouble();

                        System.out.print("Introdu noii ani de experienta ai antrenorului: ");
                        int aniExperientaNoi = scanner.nextInt();
                        scanner.nextLine();

                        service.actualizeazaInformatiiAntrenor(antrenor, numeNou, prenumeNou, varstaNoua, salariuNou, aniExperientaNoi);
                        System.out.println("Informatiile antrenorului au fost actualizate cu succes.");
                    } else {
                        System.out.println("Antrenorul nu a fost gasit.");
                    }
                    break;

                case 11:
                    System.out.print("Introdu numele curent al jucatorului: ");
                    String numeCurentJucator = scanner.nextLine();

                    System.out.print("Introdu prenumele curent al jucatorului: ");
                    String prenumeCurentJucator = scanner.nextLine();

                    // Assuming you can uniquely identify a player by their name and prename
                    System.out.print("Introdu noul nume al jucatorului: ");
                    String numeNou = scanner.nextLine();

                    System.out.print("Introdu noul prenume al jucatorului: ");
                    String prenumeNou = scanner.nextLine();

                    System.out.print("Introdu noua nationalitate a jucatorului: ");
                    String nationalitateNoua = scanner.nextLine();

                    System.out.print("Introdu noua varsta a jucatorului: ");
                    int varstaNoua = scanner.nextInt();

                    System.out.print("Introdu noul salariu al jucatorului: ");
                    double salariuNou = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Introdu noua pozitie a jucatorului: ");
                    String pozitieNoua = scanner.nextLine();

                    System.out.print("Introdu noul numar de tricou al jucatorului: ");
                    int numarTricouNou = scanner.nextInt();
                    scanner.nextLine();

                    actualizareReusita = service.actualizeazaInformatiiJucator(numeCurentJucator, prenumeCurentJucator, numeNou, prenumeNou, nationalitateNoua, varstaNoua, salariuNou, pozitieNoua, numarTricouNou);
                    if (actualizareReusita) {
                        System.out.println("Informatiile jucatorului au fost actualizate cu succes.");
                    } else {
                        System.out.println("Actualizarea informatiilor jucatorului a esuat.");
                    }
                    break;

                case 12:
                    System.out.print("Introdu numele echipei pe care vrei sa o afisezi: ");
                    numeEchipa = scanner.nextLine();
                    Echipa echipaCautata = service.cautaEchipaDupaNume(numeEchipa);
                    if (echipaCautata != null) {
                        System.out.println(echipaCautata);
                    } else {
                        System.out.println("Echipa nu a fost gasita.");
                    }
                    break;

                case 13:
                    System.out.print("Introdu numele antrenorului pe care vrei sa il afisezi: ");
                    numeAntrenor = scanner.nextLine();
                    System.out.print("Introdu prenumele antrenorului pe care vrei sa il afisezi: ");
                    prenumeAntrenor = scanner.nextLine();
                    Antrenor antrenorCautat = service.cautaAntrenorDupaNumeSiPrenume(numeAntrenor, prenumeAntrenor);
                    if (antrenorCautat != null) {
                        System.out.println(antrenorCautat);
                    } else {
                        System.out.println("Antrenorul nu a fost gasit.");
                    }
                    break;

                case 14: // Creează un sponsor nou
                    System.out.print("Introdu numele sponsorului: ");
                    numeSponsor = scanner.nextLine();
                    System.out.print("Introdu țara sponsorului: ");
                    taraSponsor = scanner.nextLine();
                    service.createSponsor(numeSponsor, taraSponsor);
                    break;

                case 15: // Șterge un sponsor
                    System.out.print("Introdu numele sponsorului de șters: ");
                    String numeSponsorDeSters = scanner.nextLine();
                    if (service.removeSponsor(numeSponsorDeSters)) {
                        System.out.println("Sponsorul a fost șters cu succes.");
                    } else {
                        System.out.println("Sponsorul nu a fost găsit.");
                    }
                    break;

                case 16:
                    System.out.print("Introdu numele echipei: ");
                    numeEchipa = scanner.nextLine();
                    echipa = service.cautaEchipaDupaNume(numeEchipa);

                    System.out.print("Introdu numele sponsorului: ");
                    numeSponsor = scanner.nextLine();
                    System.out.print("Introdu țara sponsorului: ");
                    taraSponsor = scanner.nextLine();
                    sponsor = service.cautaSponsorDupaNumeSiTara(numeSponsor, taraSponsor);

                    if (echipa != null && sponsor != null) {
                        System.out.print("Introdu durata contractului (în ani): ");
                        int durata = scanner.nextInt();
                        System.out.print("Introdu suma contractului: ");
                        double suma = scanner.nextDouble();
                        scanner.nextLine();
                        service.creeazaContract(echipa, sponsor, durata, suma);
                    } else {
                        System.out.println("Echipa sau sponsorul nu au fost găsiți.");
                    }
                    break;

                case 17:
                    System.out.print("Introdu numele echipei: ");
                    numeEchipa = scanner.nextLine();
                    echipa = service.cautaEchipaDupaNume(numeEchipa);

                    System.out.print("Introdu numele sponsorului: ");
                    numeSponsor = scanner.nextLine();
                    System.out.print("Introdu țara sponsorului: ");
                    taraSponsor = scanner.nextLine();
                    sponsor = service.cautaSponsorDupaNumeSiTara(numeSponsor, taraSponsor);

                    if (echipa != null && sponsor != null) {
                        if (service.reziliazaContract(echipa, sponsor)) {
                            System.out.println("Contractul a fost reziliat cu succes.");
                        } else {
                            System.out.println("Contractul nu a fost găsit.");
                        }
                    } else {
                        System.out.println("Echipa sau sponsorul nu au fost găsiți.");
                    }
                    break;

                case 18:
                    System.out.print("Introdu numele echipei pentru care vrei să vezi contractele: ");
                    numeEchipa = scanner.nextLine();
                    echipa = service.cautaEchipaDupaNume(numeEchipa);

                    if (echipa != null) {
                        service.afiseazaContracteEchipa(echipa);
                    } else {
                        System.out.println("Echipa nu a fost găsită.");
                    }
                    break;

                case 0:
                    quit = true;
                    break;

                default:
                    System.out.println("Task failed successfully. Joking :)" + '\n' + "Te rog alege din nou o optiune.");
            }
        }
    }

    public static void menu(){
        System.out.println("\nMeniu:");
        System.out.println("1. Adauga echipa");
        System.out.println("2. Elimina echipa");
        System.out.println("3. Adauga jucator la echipa");
        System.out.println("4. Elimina jucator din echipa");
        System.out.println("5. Adauga meci");
        System.out.println("6. Actualizeaza rezultat meci");
        System.out.println("7. Vizualizeaza clasament");
        System.out.println("8. Afiseaza jucator dupa nume");
        System.out.println("9. Vizualizeaza meciuri pentru o data");
        System.out.println("10. Actualizeaza informatii antrenor");
        System.out.println("11. Actualizeaza informatii jucator");
        System.out.println("12. Afiseaza echipa dupa nume");
        System.out.println("13. Afiseaza antrenor dupa nume");
        System.out.println("14. Creeaza un sponsor nou");
        System.out.println("15. Sterge un sponsor");
        System.out.println("16. Semneaza contract intre o echipa si un sponsor");
        System.out.println("17. Reziliaza un contract dintre o echipa si un sponsor");
        System.out.println("18. Afiseaza contractele unei echipe cu sponsorii ei");
        System.out.println("0. Iesire");
        System.out.print("Alege o optiune: ");
    }
}