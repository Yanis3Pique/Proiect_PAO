package proiect.repository;

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
                jucator.setNume(updatedJucator.getNume());
                jucator.setPrenume(updatedJucator.getPrenume());
                jucator.setVarsta(updatedJucator.getVarsta());
                jucator.setPozitie(updatedJucator.getPozitie());
                jucator.setNumarTricou(updatedJucator.getNumarTricou());
                return;
            }
        }
    }
    public void deleteJucator(String nume, String prenume) {
        jucatori.removeIf(jucator -> jucator.getNume().equals(nume) && jucator.getPrenume().equals(prenume));
    }

    public List<Jucator> findAllJucator() {
        return new ArrayList<>(jucatori);
    }
}