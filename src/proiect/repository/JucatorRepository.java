package proiect.repository;

import proiect.model.Echipa;
import proiect.model.Jucator;

import java.util.ArrayList;
import java.util.List;

public class JucatorRepository {
    private static List<Jucator> jucatori = new ArrayList<>();

    public void createJucator(Jucator jucator) {
        jucatori.add(jucator);
    }

    public Jucator readJucator(String nume, String prenume) {
        for (Jucator jucator : jucatori) {
            if (jucator.getNume().equals(nume) && jucator.getPrenume().equals(prenume)) {
                return jucator;
            }
        }
        return null;
    }

    public void updateJucator(String nume, String prenume, Jucator updatedJucator) {
        for (Jucator jucator : jucatori) {
            if (jucator.getNume().equals(nume) && jucator.getPrenume().equals(prenume)) {
                for(Echipa echipa : new EchipaRepository().findAllEchipa()) {
                    for(Jucator jucatorEchipa : echipa.getJucatori()) {
                        if(jucatorEchipa.getNume().equals(nume) && jucatorEchipa.getPrenume().equals(prenume)) {
                            jucatorEchipa.setNume(updatedJucator.getNume());
                            jucatorEchipa.setPrenume(updatedJucator.getPrenume());
                            jucatorEchipa.setVarsta(updatedJucator.getVarsta());
                            jucatorEchipa.setPozitie(updatedJucator.getPozitie());
                            jucatorEchipa.setNumarTricou(updatedJucator.getNumarTricou());
                        }
                    }
                }
                jucator.setId(updatedJucator.getId());
                jucator.setNume(updatedJucator.getNume());
                jucator.setPrenume(updatedJucator.getPrenume());
                jucator.setVarsta(updatedJucator.getVarsta());
                jucator.setPozitie(updatedJucator.getPozitie());
                jucator.setNumarTricou(updatedJucator.getNumarTricou());
                return;
            }
        }
    }

    public List<Jucator> findAllJucator() {
        return new ArrayList<>(jucatori);
    }

    public void deleteJucator(Jucator jucator) {
        jucatori.remove(jucator);

        EchipaRepository echipaRepository = new EchipaRepository();
        echipaRepository.removeJucatorFromEchipa(jucator);
    }
}