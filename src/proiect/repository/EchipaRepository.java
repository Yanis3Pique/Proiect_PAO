package proiect.repository;

import proiect.model.Antrenor;
import proiect.model.Echipa;

import java.util.ArrayList;
import java.util.List;

public class EchipaRepository {
    private static List<Echipa> echipe = new ArrayList<>();

    public void createEchipa(Echipa echipa) {
        echipe.add(echipa);
    }

    public Echipa readEchipa(String nume) {
        for (Echipa echipa : echipe) {
            if (echipa.getNume().equals(nume)) {
                return echipa;
            }
        }
        return null;
    }

    public void updateEchipa(String nume, Echipa echipaUpdated) {
        for (Echipa echipa : echipe) {
            if (echipa.getNume().equals(nume)) {
                echipa.setId(echipaUpdated.getId());
                echipa.setNume(echipaUpdated.getNume());
                echipa.setAntrenor(echipaUpdated.getAntrenor());
                echipa.setJucatori(echipaUpdated.getJucatori());
                echipa.setStadion(echipaUpdated.getStadion());
                return;
            }
        }
    }

    public void deleteEchipa(String nume) {
        echipe.removeIf(echipa -> echipa.getNume().equals(nume));
    }

    public void removeAntrenorFromEchipa(Antrenor antrenor) {
        for (Echipa echipa : echipe) {
            if (echipa.getAntrenor() != null && echipa.getAntrenor().equals(antrenor)) {
                echipa.setAntrenor(null);
            }
        }
    }

    public List<Echipa> findAllEchipa() {
        return new ArrayList<>(echipe);
    }
}
