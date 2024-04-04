package proiect.dao;

import proiect.model.Meci;

import java.util.ArrayList;
import java.util.List;

public class MeciDao {
    private static int nextId = 1;
    private static List<Meci> meciuri = new ArrayList<>();

    public void createMeci(Meci meci) {
        meci.setId(nextId++);
        meciuri.add(meci);
    }

    public Meci readMeci(String numeEchipa1, String numeEchipa2, String data) {
        return meciuri.stream()
                .filter(meci -> meci.getEchipa1().getNume().equals(numeEchipa1) && meci.getEchipa2().getNume().equals(numeEchipa2) && meci.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    public void updateMeci(String numeEchipa1, String numeEchipa2, String date, int newScorEchipa1, int newScorEchipa2) {
        for(Meci meci : meciuri) {
            if(meci.getEchipa1().getNume().equals(numeEchipa1) && meci.getEchipa2().getNume().equals(numeEchipa2) && meci.getData().equals(date)) {
                meci.setScor1(newScorEchipa1);
                meci.setScor2(newScorEchipa2);
                return;
            }
        }
    }

    public void deleteMeci(Meci meci) {
        meciuri.remove(meci);
    }

    public List<Meci> findAllMeci() {
        return new ArrayList<>(meciuri);
    }
}