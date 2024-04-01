package proiect.daoservices;

import proiect.model.Meci;
import proiect.dao.MeciDao;

import java.util.List;

public class MeciRepositoryService {
    private final MeciDao meciDao = new MeciDao();

    public void addMeci(Meci meci) {
        meciDao.createMeci(meci);
    }

    public Meci getMeci(String numeEchipa1, String numeEchipa2, String data) {
        return meciDao.readMeci(numeEchipa1, numeEchipa2, data);
    }

    public void updateMeci(String numeEchipa1, String numeEchipa2, String data, int scor1, int scor2) {
        meciDao.updateMeci(numeEchipa1, numeEchipa2, data, scor1, scor2);
    }

    public void removeMeci(Meci meci) {
        meciDao.deleteMeci(meci);
    }

    public List<Meci> getAllMeciuri() {
        return meciDao.findAllMeci();
    }
}
