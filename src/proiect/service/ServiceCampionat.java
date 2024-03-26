package proiect.service;

import proiect.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ServiceCampionat {
    private final List<Angajat> angajati;
    private final List<Echipa> echipe;
    private final List<Meci> meciuri;
    private final List<Sponsor> sponsori;
    private final List<Contract> contracte;

    public ServiceCampionat() {
        this.angajati = new ArrayList<>();
        this.echipe = new ArrayList<>();
        this.meciuri = new ArrayList<>();
        this.contracte = new ArrayList<>();
        this.sponsori = new ArrayList<>();
    }

    public void adaugaEchipa(Echipa echipa) {
        echipe.add(echipa);
        angajati.add(echipa.getAntrenor());
        angajati.addAll(echipa.getJucatori());
    }

    public boolean eliminaEchipa(String numeEchipa) {
        Echipa echipa = echipe.stream().filter(e -> e.getNume().equals(numeEchipa)).findFirst().orElse(null);
        if (echipa != null) {
            echipe.remove(echipa);
            angajati.remove(echipa.getAntrenor());
            angajati.removeAll(echipa.getJucatori());

            meciuri.removeIf(meci -> meci.getEchipa1().equals(echipa) || meci.getEchipa2().equals(echipa));

            return true;
        }
        return false;
    }

    public void adaugaJucatorLaEchipa(String numeEchipa, Jucator jucator) {
        Echipa echipa = echipe.stream().filter(e -> e.getNume().equals(numeEchipa)).findFirst().orElse(null);
        if (echipa != null) {
            echipa.adaugaJucator(jucator);
            angajati.add(jucator);
        }
    }

    public boolean eliminaJucatorDinEchipa(String numeEchipa, String numeJucator, String prenumeJucator) {
        Echipa echipa = echipe.stream().filter(e -> e.getNume().equals(numeEchipa)).findFirst().orElse(null);
        if (echipa != null) {
            Optional<Jucator> jucatorDeEliminat = echipa.getJucatori().stream().filter(j -> j.getNume().equals(numeJucator) && j.getPrenume().equals(prenumeJucator)).findFirst();
            if (jucatorDeEliminat.isPresent()) {
                echipa.eliminaJucator(jucatorDeEliminat.get());
                angajati.remove(jucatorDeEliminat.get());
                return true;
            }
        }
        return false;
    }

    public Echipa cautaEchipaDupaNume(String numeEchipa) {
        for (Echipa echipa : echipe) {
            if (echipa.getNume().equalsIgnoreCase(numeEchipa)) {
                return echipa;
            }
        }
        return null;
    }

    public void adaugaMeci(Echipa echipa1, Echipa echipa2, String data, int scor1, int scor2, Stadion stadion) {
        Meci meci = new Meci(echipa1, echipa2, data, scor1, scor2, stadion);
        meciuri.add(meci);
    }

    public boolean actualizeazaRezultatMeci(String data, String numeEchipa1, String numeEchipa2, int scorNouEchipa1, int scorNouEchipa2) {
        for (Meci meci : meciuri) {
            boolean isEchipa1Match = meci.getEchipa1().getNume().equalsIgnoreCase(numeEchipa1);
            boolean isEchipa2Match = meci.getEchipa2().getNume().equalsIgnoreCase(numeEchipa2);
            if (meci.getData().equals(data) && isEchipa1Match && isEchipa2Match) {
                meci.setScor1(scorNouEchipa1);
                meci.setScor2(scorNouEchipa2);
                return true;
            }
        }
        return false;
    }

    public Map<Echipa, Integer> vizualizeazaClasament() {
        Map<Echipa, Integer> clasament = new HashMap<>();
        for (Echipa echipa : echipe) {
            clasament.put(echipa, 0);
        }
        for (Meci meci : meciuri) {
            Echipa echipa1 = meci.getEchipa1();
            Echipa echipa2 = meci.getEchipa2();
            int puncteEchipa1 = clasament.get(echipa1);
            int puncteEchipa2 = clasament.get(echipa2);
            if (meci.getScor1() > meci.getScor2()) {
                clasament.put(echipa1, puncteEchipa1 + 3);
            } else if (meci.getScor1() < meci.getScor2()) {
                clasament.put(echipa2, puncteEchipa2 + 3);
            } else {
                clasament.put(echipa1, puncteEchipa1 + 1);
                clasament.put(echipa2, puncteEchipa2 + 1);
            }
        }
        return clasament.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public List<Jucator> cautaJucatorDupaNume(String nume) {
        return angajati.stream()
                .filter(angajat -> angajat instanceof Jucator && angajat.getNume().equals(nume))
                .map(angajat -> (Jucator) angajat)
                .collect(Collectors.toList());
    }

    public List<Meci> vizualizeazaMeciuriPentruData(String data) {
        return meciuri.stream()
                .filter(meci -> meci.getData().equals(data))
                .collect(Collectors.toList());
    }

    public Antrenor cautaAntrenorDupaNumeSiPrenume(String nume, String prenume) {
        for (Angajat angajat : angajati) {
            if (angajat instanceof Antrenor antrenor) {
                if (antrenor.getNume().equalsIgnoreCase(nume) && antrenor.getPrenume().equalsIgnoreCase(prenume)) {
                    return antrenor;
                }
            }
        }
        return null;
    }

    public void actualizeazaInformatiiAntrenor(Antrenor antrenor, String numeNou, String prenumeNou, int varstaNoua, double salariuNou, int aniExperientaNoi) {
        antrenor.setNume(numeNou);
        antrenor.setPrenume(prenumeNou);
        antrenor.setVarsta(varstaNoua);
        antrenor.setSalariu(salariuNou);
        antrenor.setAniExperienta(aniExperientaNoi);
    }

    public boolean actualizeazaInformatiiJucator(String numeCurent, String prenumeCurent, String numeNou, String prenumeNou, String nationalitateNoua, int varstaNoua, double salariuNou, String pozitieNoua, int numarTricouNou) {
        for (Angajat angajat : angajati) {
            if (angajat instanceof Jucator jucator) {
                if (jucator.getNume().equalsIgnoreCase(numeCurent) && jucator.getPrenume().equalsIgnoreCase(prenumeCurent)) {
                    jucator.setNume(numeNou);
                    jucator.setPrenume(prenumeNou);
                    jucator.setNationalitate(nationalitateNoua);
                    jucator.setVarsta(varstaNoua);
                    jucator.setSalariu(salariuNou);
                    jucator.setPozitie(pozitieNoua);
                    jucator.setNumarTricou(numarTricouNou);
                    return true;
                }
            }
        }
        return false;
    }

    public void createSponsor(String name, String country) {
        Sponsor newSponsor = new Sponsor(name, country);
        sponsori.add(newSponsor);
        System.out.println("Sponsor creat cu succes: " + name + " din " + country);
    }

    public boolean removeSponsor(String name) {
        for (Sponsor sponsor : new ArrayList<>(sponsori)) {
            if (sponsor.getName().equalsIgnoreCase(name)) {
                sponsori.remove(sponsor);
                System.out.println("Sponsor eliminat cu success: " + name);
                return true;
            }
        }
        System.out.println("Sponsorul nu a fost gasit!");
        return false;
    }

    public Sponsor cautaSponsorDupaNumeSiTara(String name, String country) {
        for (Sponsor sponsor : sponsori) {
            if (sponsor.getName().equalsIgnoreCase(name) && sponsor.getCountry().equalsIgnoreCase(country)) {
                return sponsor;
            }
        }
        return null;
    }


    public void creeazaContract(Echipa team, Sponsor sponsor, int durationYears, double sumMoney) {
        Contract newContract = new Contract(team, sponsor, durationYears, sumMoney);
        contracte.add(newContract);
        System.out.println("Contract nou creat cu succes intre " + team.getNume() + " si " + sponsor.getName() + ".");
    }

    public boolean reziliazaContract(Echipa team, Sponsor sponsor) {
        for (Contract contract : contracte) {
            if (contract.getTeam().equals(team) && contract.getSponsor().equals(sponsor)) {
                contracte.remove(contract);
                System.out.println("Contractul dintre " + team.getNume() + " si " + sponsor.getName() + " a fost reziliat.");
                return true;
            }
        }
        System.out.println("Nu s-a gasit niciun contract intre " + team.getNume() + " si " + sponsor.getName() + " pentru a fi reziliat.");
        return false;
    }

    public void afiseazaContracteEchipa(Echipa team) {
        List<Contract> teamContracts = new ArrayList<>();

        for (Contract contract : contracte) {
            if (contract.getTeam().equals(team)) {
                teamContracts.add(contract);
            }
        }

        if (teamContracts.isEmpty()) {
            System.out.println("Echipa " + team.getNume() + " nu are contracte incheiate cu niciun sponsor.");
            return;
        }

        System.out.println("Contractele echipei: " + team.getNume());
        for (Contract contract : teamContracts) {
            String contractDetails = String.format("Sponsor: %s, Tara: %s, Durata: %d years, Suma: %.2f",
                    contract.getSponsor().getName(), contract.getSponsor().getCountry(),
                    contract.getDurationYears(), contract.getSumMoney());
            System.out.println(contractDetails);
        }
    }
}