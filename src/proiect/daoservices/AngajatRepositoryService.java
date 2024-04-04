package proiect.daoservices;

import proiect.model.Angajat;
import proiect.model.Antrenor;
import proiect.model.Jucator;
import proiect.dao.AntrenorDao;
import proiect.dao.JucatorDao;

import java.util.ArrayList;
import java.util.List;

public class AngajatRepositoryService {
    private final AntrenorDao antrenorDao = new AntrenorDao();
    private final JucatorDao jucatorDao = new JucatorDao();

    public void addAngajat(Angajat angajat) {
        if (angajat instanceof Antrenor) {
            antrenorDao.createAntrenor((Antrenor) angajat);
        } else if (angajat instanceof Jucator) {
            jucatorDao.createJucator((Jucator) angajat);
        }
    }

    public Angajat getAngajatByName(String nume, String prenume) {
        Antrenor antrenor = antrenorDao.readAntrenor(nume, prenume);
        if (antrenor != null) {
            return antrenor;
        }

        Jucator jucator = jucatorDao.readJucator(nume, prenume);
        if (jucator != null) {
            return jucator;
        }

        return null;
    }

    public void updateAngajat(Angajat angajat) {
        if (angajat instanceof Antrenor antrenor) {
            antrenorDao.updateAntrenor(antrenor.getNume(), antrenor.getPrenume(), antrenor);
        } else if (angajat instanceof Jucator jucator) {
            jucatorDao.updateJucator(jucator.getNume(), jucator.getPrenume(), jucator);
        }
    }

    public void  removeAngajat(Angajat angajat) {
        if (angajat instanceof Antrenor) {
            antrenorDao.deleteAntrenor((Antrenor) angajat);
        } else if (angajat instanceof Jucator) {
            jucatorDao.deleteJucator((Jucator) angajat);
        }
    }

    public List<Angajat> getAllAngajati() {
        List<Angajat> angajati = new ArrayList<>();
        angajati.addAll(antrenorDao.findAllAntrenori());
        angajati.addAll(jucatorDao.findAllJucator());
        return angajati;
    }
}
