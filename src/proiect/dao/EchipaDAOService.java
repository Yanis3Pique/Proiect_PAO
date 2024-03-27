package proiect.dao;

import proiect.model.Echipa;
import proiect.model.Jucator;
import proiect.repository.EchipaRepository;

import java.util.List;

public class EchipaDAOService {
    private EchipaRepository echipaRepository;

    public EchipaDAOService(EchipaRepository echipaRepository) {
        this.echipaRepository = echipaRepository;
    }

    public void addEchipa(Echipa echipa) {
        echipaRepository.createEchipa(echipa);
        System.out.println("Echipa adaugata: " + echipa.getNume());
    }

    public Echipa getEchipaByName(String nume) {
        return echipaRepository.readEchipa(nume);
    }

    public void updateEchipa(String nume, Echipa echipaUpdated) {
        echipaRepository.updateEchipa(nume, echipaUpdated);
        System.out.println("Echipa updatata: " + nume);
    }

    public void removeEchipa(String nume) {
        Echipa echipa = echipaRepository.readEchipa(nume);
        if (echipa != null) {
            echipaRepository.deleteEchipa(nume);
            System.out.println("Echipa stearsa: " + nume);
        }
    }

    public List<Echipa> getAllEchipe() {
        return echipaRepository.findAllEchipa();
    }

    public void addJucatorToEchipa(String numeEchipa, Jucator jucator) {
        Echipa echipa = getEchipaByName(numeEchipa);
        if (echipa != null) {
            echipa.getJucatori().add(jucator);
            System.out.println("Jucator " + jucator.getNume() + " adaugat la " + numeEchipa);
        }
    }

    public void removeJucatorFromEchipa(String numeEchipa, String numeJucator, String prenumeJucator) {
        Echipa echipa = getEchipaByName(numeEchipa);
        if (echipa != null) {
            echipa.getJucatori().removeIf(jucator -> jucator.getNume().equals(numeJucator) && jucator.getPrenume().equals(prenumeJucator));
            System.out.println("Jucator " + numeJucator + " eliminat de la " + numeEchipa);
        }
    }
}
