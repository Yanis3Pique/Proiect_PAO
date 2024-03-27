package proiect.dao;

import proiect.model.Echipa;
import proiect.repository.EchipaRepository;

import java.util.List;

public class EchipaDAOService {
    private final EchipaRepository echipaRepository = new EchipaRepository();

    public void addEchipa(Echipa echipa) {
        echipaRepository.createEchipa(echipa);
    }

    public Echipa getEchipaByName(String nume) {
        return echipaRepository.readEchipa(nume);
    }

    public void updateEchipa(String nume, Echipa echipaUpdated) {
        echipaRepository.updateEchipa(nume, echipaUpdated);
    }

    public void removeEchipa(String nume) {
        echipaRepository.deleteEchipa(nume);
    }

    public List<Echipa> getAllEchipe() {
        return echipaRepository.findAllEchipa();
    }
}