package proiect.dao;

import proiect.model.Meci;
import proiect.repository.MeciRepository;

import java.util.List;

public class MeciDAOService {
    private final MeciRepository meciRepository = new MeciRepository();

    public void addMeci(Meci meci) {
        meciRepository.createMeci(meci);
    }

    public Meci getMeci(String numeEchipa1, String numeEchipa2, String data) {
        return meciRepository.readMeci(numeEchipa1, numeEchipa2, data);
    }

    public void updateMeci(String numeEchipa1, String numeEchipa2, String data, int scor1, int scor2) {
        meciRepository.updateMeci(numeEchipa1, numeEchipa2, data, scor1, scor2);
    }

    public void removeMeci(Meci meci) {
        meciRepository.deleteMeci(meci);
    }

    public List<Meci> getAllMeciuri() {
        return meciRepository.findAllMeci();
    }
}
