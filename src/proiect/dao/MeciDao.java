package proiect.dao;

import proiect.model.Meci;

import java.util.ArrayList;
import java.util.List;

public class MeciDao implements DaoInterface<Meci> {
    private static int nextId = 1;
    private static List<Meci> meciuri = new ArrayList<>();

    @Override
    public void create(Meci meci) {
        meci.setId(nextId++);
        meciuri.add(meci);
    }

    @Override
    public Meci read(String numeEchipa1_numeEchipa2_data) {
        String numeEchipa1 = numeEchipa1_numeEchipa2_data.split("_")[0];
        String numeEchipa2 = numeEchipa1_numeEchipa2_data.split("_")[1];
        String data = numeEchipa1_numeEchipa2_data.split("_")[2];
        return meciuri.stream()
                .filter(meci -> meci.getEchipa1().getNume().equals(numeEchipa1)
                        && meci.getEchipa2().getNume().equals(numeEchipa2)
                        && meci.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(String numeEchipa1_numeEchipa2_date, Meci newMeci) {
        String numeEchipa1 = numeEchipa1_numeEchipa2_date.split("_")[0];
        String numeEchipa2 = numeEchipa1_numeEchipa2_date.split("_")[1];
        String date = numeEchipa1_numeEchipa2_date.split("_")[2];
        for(Meci meci : meciuri) {
            if(meci.getEchipa1().getNume().equals(numeEchipa1)
                    && meci.getEchipa2().getNume().equals(numeEchipa2)
                    && meci.getData().equals(date)) {
                meci.setScor1(newMeci.getScor1());
                meci.setScor2(newMeci.getScor2());
                return;
            }
        }
    }

    @Override
    public void delete(Meci meci) {
        meciuri.remove(meci);
    }

    public List<Meci> findAllMeci() {
        return new ArrayList<>(meciuri);
    }
}