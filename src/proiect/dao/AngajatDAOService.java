package proiect.dao;

import proiect.model.Antrenor;
import proiect.model.Jucator;
import proiect.repository.AntrenorRepository;
import proiect.repository.JucatorRepository;

import java.util.List;

public class AngajatDAOService {
    private AntrenorRepository antrenorRepository;
    private JucatorRepository jucatorRepository;

    public AngajatDAOService() {
        this.antrenorRepository = new AntrenorRepository();
        this.jucatorRepository = new JucatorRepository();
    }

    public void addAntrenor(Antrenor antrenor) {
        antrenorRepository.createAntrenor(antrenor);
        System.out.println("Antrenor added: " + antrenor.getNume() + " " + antrenor.getPrenume());
    }

    public Antrenor findAntrenorByName(String nume, String prenume) {
        return antrenorRepository.readAntrenor(nume, prenume);
    }

    public void updateAntrenor(String nume, String prenume, Antrenor updatedAntrenor) {
        antrenorRepository.updateAntrenor(nume, prenume, updatedAntrenor);
        System.out.println("Antrenor updated: " + updatedAntrenor.getNume() + " " + updatedAntrenor.getPrenume());
    }

    public void deleteAntrenor(Antrenor antrenor) {
        antrenorRepository.deleteAntrenor(antrenor);
        System.out.println("Antrenor deleted: " + antrenor.getNume() + " " + antrenor.getPrenume());
    }

    public List<Antrenor> findAllAntrenori() {
        return antrenorRepository.findAllAntrenori();
    }

    public void addJucator(Jucator jucator) {
        jucatorRepository.createJucator(jucator);
        System.out.println("Jucator added: " + jucator.getNume() + " " + jucator.getPrenume());
    }

    public Jucator findJucatorByName(String nume, String prenume) {
        return jucatorRepository.readJucator(nume, prenume);
    }

    public void updateJucator(String nume, String prenume, Jucator updatedJucator) {
        jucatorRepository.updateJucator(nume, prenume, updatedJucator);
        System.out.println("Jucator updated: " + updatedJucator.getNume() + " " + updatedJucator.getPrenume());
    }

    public void deleteJucator(Jucator jucator) {
        jucatorRepository.deleteJucator(jucator.getNume(), jucator.getPrenume());
        System.out.println("Jucator deleted: " + jucator.getNume() + " " + jucator.getPrenume());
    }

    public List<Jucator> findAllJucatori() {
        return jucatorRepository.findAllJucator();
    }
}
