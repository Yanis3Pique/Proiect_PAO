package proiect.repository;

import proiect.model.Antrenor;
import proiect.model.Echipa;

import java.util.ArrayList;
import java.util.List;

public class AntrenorRepository {

    private static int nextId = 1;
    private static List<Antrenor> antrenori = new ArrayList<>();

    public void createAntrenor(Antrenor antrenor) {
        antrenor.setId(nextId++);
        antrenori.add(antrenor);
    }

    public Antrenor readAntrenor(String nume, String prenume) {
        for (Antrenor antrenor : antrenori) {
            if (antrenor.getNume().equals(nume) && antrenor.getPrenume().equals(prenume)) {
                return antrenor;
            }
        }
        return null;
    }

    public void updateAntrenor(String nume, String prenume, Antrenor antrenorUpdated) {
        for (Antrenor antrenor : antrenori) {
            if (antrenor.getNume().equals(nume) && antrenor.getPrenume().equals(prenume)) {
                EchipaRepository echipaRepository = new EchipaRepository();
                for(Echipa echipa : echipaRepository.findAllEchipa()) {
                    if(echipa.getAntrenor() != null && echipa.getAntrenor().equals(antrenor)) {
                        echipa.setAntrenor(antrenorUpdated);
                        echipaRepository.updateEchipa(echipa.getNume(), echipa);
                    }
                }
                antrenor.setId(antrenorUpdated.getId());
                antrenor.setNume(antrenorUpdated.getNume());
                antrenor.setPrenume(antrenorUpdated.getPrenume());
                antrenor.setVarsta(antrenorUpdated.getVarsta());
                antrenor.setSalariu(antrenorUpdated.getSalariu());
                antrenor.setAniExperienta(antrenorUpdated.getAniExperienta());
                antrenor.setNationalitate(antrenorUpdated.getNationalitate());
                return;
            }
        }
    }

    public void deleteAntrenor(Antrenor antrenor) {
        antrenori.remove(antrenor);

        EchipaRepository echipaRepository = new EchipaRepository();
        echipaRepository.removeAntrenorFromEchipa(antrenor);
    }

    public List<Antrenor> findAllAntrenori() { return new ArrayList<>(antrenori); }
}
