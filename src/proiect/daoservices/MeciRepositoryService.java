package proiect.daoservices;

import proiect.model.Meci;
import proiect.dao.MeciDao;

import java.util.List;

public class MeciRepositoryService {
    private final MeciDao meciDao = new MeciDao();

    public void addMeci(Meci meci) {
        meciDao.create(meci);
    }

    public Meci getMeci(String numeEchipa1, String numeEchipa2, String data) {
        return meciDao.read(numeEchipa1 + "_" + numeEchipa2 + "_" + data);
    }

    public void updateMeci(String numeEchipa1, String numeEchipa2, String data, Meci newMeci) {
        meciDao.update(numeEchipa1 + "_" + numeEchipa2 + "_" + data, newMeci);
    }

    public void removeMeci(Meci meci) {
        meciDao.delete(meci);
    }

    public List<Meci> getAllMeciuri() {
        return meciDao.findAllMeci();
    }
}
