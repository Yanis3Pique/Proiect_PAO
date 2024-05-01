package proiect.daoservices;

import proiect.model.Echipa;
import proiect.dao.EchipaDao;

import java.util.List;

public class EchipaRepositoryService {
    private final EchipaDao echipaDao = new EchipaDao();

    public void addEchipa(Echipa echipa) {
        echipaDao.create(echipa);
    }

    public Echipa getEchipaByName(String nume) {
        return echipaDao.read(nume);
    }

    public void updateEchipa(String nume, Echipa echipaUpdated) {
        echipaDao.update(nume, echipaUpdated);
    }

    public void removeEchipa(String nume) {
        Echipa echipa = echipaDao.read(nume);
        echipaDao.delete(echipa);
    }

    public List<Echipa> getAllEchipe() {
        return echipaDao.findAllEchipa();
    }
}