package proiect.dao;

import proiect.model.Angajat;
import proiect.model.Antrenor;
import proiect.model.Jucator;
import proiect.repository.AntrenorRepository;
import proiect.repository.JucatorRepository;

import java.util.ArrayList;
import java.util.List;

public class AngajatDAOService {
    private final AntrenorRepository antrenorRepository = new AntrenorRepository();
    private final JucatorRepository jucatorRepository = new JucatorRepository();

    public void addAngajat(Angajat angajat) {
        if (angajat instanceof Antrenor) {
            antrenorRepository.createAntrenor((Antrenor) angajat);
        } else if (angajat instanceof Jucator) {
            jucatorRepository.createJucator((Jucator) angajat);
        }
    }

    public Angajat getAngajatByName(String nume, String prenume) {
        Antrenor antrenor = antrenorRepository.readAntrenor(nume, prenume);
        if (antrenor != null) {
            return antrenor;
        }

        Jucator jucator = jucatorRepository.readJucator(nume, prenume);
        if (jucator != null) {
            return jucator;
        }

        return null;
    }

    public void updateAngajat(Angajat angajat) {
        if (angajat instanceof Antrenor antrenor) {
            antrenorRepository.updateAntrenor(antrenor.getNume(), antrenor.getPrenume(), antrenor);
        } else if (angajat instanceof Jucator jucator) {
            jucatorRepository.updateJucator(jucator.getNume(), jucator.getPrenume(), jucator);
        }
    }

    public void removeAngajat(Angajat angajat) {
        if (angajat instanceof Antrenor) {
            antrenorRepository.deleteAntrenor((Antrenor) angajat);
        } else if (angajat instanceof Jucator) {
            jucatorRepository.deleteJucator((Jucator) angajat);
        }
    }

    public List<Angajat> getAllAngajati() {
        List<Angajat> angajati = new ArrayList<>();
        angajati.addAll(antrenorRepository.findAllAntrenori());
        angajati.addAll(jucatorRepository.findAllJucator());
        return angajati;
    }
}
