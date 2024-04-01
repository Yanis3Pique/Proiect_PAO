package proiect.dao;

import proiect.model.Antrenor;
import proiect.model.Echipa;

import java.util.ArrayList;
import java.util.List;

public class AntrenorDao {

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
                EchipaDao echipaDao = new EchipaDao();
                for(Echipa echipa : echipaDao.findAllEchipa()) {
                    if(echipa.getAntrenor() != null && echipa.getAntrenor().equals(antrenor)) {
                        echipa.setAntrenor(antrenorUpdated);
                        echipaDao.updateEchipa(echipa.getNume(), echipa);
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

        EchipaDao echipaDao = new EchipaDao();
        echipaDao.removeAntrenorFromEchipa(antrenor);
    }

    public List<Antrenor> findAllAntrenori() { return new ArrayList<>(antrenori); }
}
