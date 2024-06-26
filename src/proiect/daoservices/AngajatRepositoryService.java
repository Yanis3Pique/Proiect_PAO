package proiect.daoservices;

import proiect.model.*;
import proiect.dao.AntrenorDao;
import proiect.dao.JucatorDao;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.List;

public class AngajatRepositoryService {
    private AntrenorDao antrenorDao = AntrenorDao.getInstance();
    private JucatorDao jucatorDao = JucatorDao.getInstance();

    public AngajatRepositoryService() throws SQLException {}

    public void printAllEmployees() throws SQLException {
        List<Angajat> angajati = getAllAngajati();
        if(angajati != null){
            angajati.forEach(System.out:: println);
        } else {
            System.out.println("There is no employee.");
        }
    }

    public List<Angajat> getAllAngajati() throws SQLException {
        List<Angajat> allAngajati = new java.util.ArrayList<>();

        // Add all Antrenor objects to the list
        allAngajati.addAll(antrenorDao.findAllAntrenor());

        // Add all Jucator objects to the list
        allAngajati.addAll(jucatorDao.findAllJucator());

        return allAngajati; // Return the combined list of Angajat
    }

    public void addAngajat(Angajat angajat) throws InvalidDataException {
        try {
            if(angajat != null){
                if(angajat instanceof Antrenor antrenor) {
                    if(antrenorDao.read(antrenor.getNume() + "_" + antrenor.getPrenume()) != null)
                        throw new InvalidDataException("We already have a coach with this name!");
                    antrenorDao.create(antrenor);
                } else if(angajat instanceof Jucator jucator) {
                    if(jucatorDao.read(jucator.getNume() + "_" + jucator.getPrenume()) != null)
                        throw new InvalidDataException("We already have a player with this name!");
                    jucatorDao.create(jucator);
                }
                System.out.println("Employee added!");
            }
        } catch (SQLException e) {
            System.out.println("Employee could not be added " + e.getMessage());
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

        return null;
    }

    public Angajat getAngajatByID(int id) throws SQLException {
        Antrenor antrenor = antrenorDao.readByID(id);
        if (antrenor != null) {
            return antrenor;
        }

        Jucator jucator = jucatorDao.readByID(id);
        if (jucator != null) {
            return jucator;
        }

        return null;
    }

    public void updateAngajat(Angajat angajat) throws InvalidDataException {
        try {
            if(angajat != null) {
                if(angajat instanceof Antrenor antrenor) {
                    if(antrenorDao.read(antrenor.getNume() + "_" + antrenor.getPrenume()) != null)
                        throw new InvalidDataException("We already have a coach with this name!");
                    antrenorDao.update(antrenor.getNume() + "_" + antrenor.getPrenume(), antrenor);
                } else if(angajat instanceof Jucator jucator) {
                    if(jucatorDao.read(jucator.getNume() + "_" + jucator.getPrenume()) != null && jucatorDao.read(jucator.getNume() + "_" + jucator.getPrenume()).getId() != jucator.getId())
                        throw new InvalidDataException("We already have a player with this name and Team ID!");
                    jucatorDao.update(jucator.getNume() + "_" + jucator.getPrenume(), jucator);
                }
            }
        } catch (SQLException e) {
            System.out.println("Employee could not be updated " + e.getMessage());
        }
    }

    public void removeAngajat(Angajat angajat) {
        try {
            if(angajat == null) return;

            if(angajat instanceof Antrenor antrenor) {
                antrenorDao.delete(antrenor);
            } else if(angajat instanceof Jucator jucator) {
                jucatorDao.delete(jucator);
            }
        } catch (SQLException e) {
            System.out.println("Deletion failed: " + e.getMessage());
        }
    }
}
