package proiect.daoservices;

import proiect.model.Angajat;
import proiect.model.Antrenor;
import proiect.model.Jucator;
import proiect.dao.AntrenorDao;
import proiect.dao.JucatorDao;

import java.sql.SQLException;

public class AngajatRepositoryService {
    private AntrenorDao antrenorDao = AntrenorDao.getInstance();
    private JucatorDao jucatorDao = JucatorDao.getInstance();

    public AngajatRepositoryService() throws SQLException {}

    public void addAngajat(Angajat angajat) throws SQLException {
        if (angajat instanceof Antrenor) {
            antrenorDao.create((Antrenor) angajat);
        } else if (angajat instanceof Jucator) {
            jucatorDao.create((Jucator) angajat);
        }
    }

    public Angajat getAngajatByName(String nume, String prenume) throws SQLException {
        Antrenor antrenor = antrenorDao.read(nume + "_" + prenume);
        if (antrenor != null) {
            return antrenor;
        }

        Jucator jucator = jucatorDao.read(nume + "_" + prenume);
        if (jucator != null) {
            return jucator;
        }

        System.out.println("Employee has not been found!");
        return null;
    }

    public void updateAngajat(Angajat angajat) throws SQLException {
        if (angajat instanceof Antrenor antrenor) {
            antrenorDao.update(antrenor.getNume() + "_" + antrenor.getPrenume(), antrenor);
        } else if (angajat instanceof Jucator jucator) {
            jucatorDao.update(jucator.getNume() + "_" + jucator.getPrenume(), jucator);
        }
    }

    public void removeAngajat(Angajat angajat) throws SQLException {
        if (angajat instanceof Antrenor) {
            antrenorDao.delete((Antrenor) angajat);
        } else if (angajat instanceof Jucator) {
            jucatorDao.delete((Jucator) angajat);
        }
    }
}
