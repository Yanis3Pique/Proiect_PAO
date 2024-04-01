package proiect.daoservices;

import proiect.model.Echipa;
import proiect.dao.EchipaDao;

import java.util.List;

public class EchipaRepositoryService {
    private final EchipaDao echipaDao = new EchipaDao();

    public void addEchipa(Echipa echipa) {
        echipaDao.createEchipa(echipa);
    }

    public Echipa getEchipaByName(String nume) {
        return echipaDao.readEchipa(nume);
    }

    public void updateEchipa(String nume, Echipa echipaUpdated) {
        echipaDao.updateEchipa(nume, echipaUpdated);
    }

    public void removeEchipa(String nume) {
        echipaDao.deleteEchipa(nume);
    }

    public List<Echipa> getAllEchipe() {
        return echipaDao.findAllEchipa();
    }
}