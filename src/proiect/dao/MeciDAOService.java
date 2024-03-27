package proiect.dao;

import proiect.model.Meci;
import proiect.model.Echipa;
import proiect.model.Stadion;
import proiect.repository.MeciRepository;
import proiect.repository.StadionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MeciDAOService {
    private MeciRepository meciRepository;
    private StadionRepository stadionRepository;

    public MeciDAOService(MeciRepository meciRepository, StadionRepository stadionRepository) {
        this.meciRepository = meciRepository;
        this.stadionRepository = stadionRepository;
    }

    public void addMeci(Meci meci) {
        meciRepository.createMeci(meci);
        System.out.println("Meci adaugat: " + meci.getEchipa1().getNume() + " vs " + meci.getEchipa2().getNume() + " la data de " + meci.getData());
    }

    public Meci getMeci(String numeEchipa1, String numeEchipa2, String data) {
        return meciRepository.readMeci(numeEchipa1, numeEchipa2, data);
    }

    public void updateMeci(String numeEchipa1, String numeEchipa2, String data, int scorEchipa1, int scorEchipa2) {
        Meci meci = meciRepository.readMeci(numeEchipa1, numeEchipa2, data);
        meciRepository.updateMeci(numeEchipa1, numeEchipa2, data, scorEchipa1, scorEchipa2);
        System.out.println("Meci actualizat: " + meci.getEchipa1().getNume() + " vs " + meci.getEchipa2().getNume() + " la data de " + meci.getData());
    }

    public void removeMeci(Meci meci) {
        meciRepository.deleteMeci(meci);
        System.out.println("Meci sters: " + meci.getEchipa1().getNume() + " vs " + meci.getEchipa2().getNume());
    }

    public List<Meci> getAllMeciuri() {
        return meciRepository.findAllMeci();
    }

    public List<Meci> getMeciuriForStadion(String stadionNume) {
        Stadion stadion = stadionRepository.read(stadionNume);
        return meciRepository.findAllMeci().stream()
                .filter(meci -> meci.getStadion().equals(stadion))
                .collect(Collectors.toList());
    }

    public void updateStadionForMeci(Meci meci, Stadion newStadion) {
        if(meci != null && newStadion != null) {
            meci.setStadion(newStadion);
            System.out.println("Actualizat stadionul meciului: " + meci.getEchipa1().getNume() + " vs " + meci.getEchipa2().getNume());
        }
    }

    public List<Meci> vizualizeazaMeciuriPentruData(String data) {
        return meciRepository.findAllMeci().stream()
                .filter(meci -> meci.getData().equals(data))
                .collect(Collectors.toList());
    }
}