package proiect.dao;

import proiect.model.*;

import java.util.ArrayList;
import java.util.List;

public class EchipaDao implements DaoInterface<Echipa> {
    private static int nextId = 1;
    private static List<Echipa> echipe = new ArrayList<>();

    @Override
    public void create(Echipa echipa) {
        echipa.setId(nextId++);
        echipe.add(echipa);
    }

    @Override
    public Echipa read(String nume) {
        for (Echipa echipa : echipe) {
            if (echipa.getNume().equals(nume)) {
                return echipa;
            }
        }
        return null;
    }

    @Override
    public void update(String nume, Echipa echipaUpdated) {
        for (Echipa echipa : echipe) {
            for(Meci meci : new MeciDao().findAllMeci()) {
                if(meci.getEchipa1().getNume().equals(nume)) {meci.setEchipa1(echipaUpdated);}
                if(meci.getEchipa2().getNume().equals(nume)) {meci.setEchipa2(echipaUpdated);}
            }
            for(Contract contract : new ContractDao().findAllContract()) {
                if(contract.getTeam().getNume().equals(nume)) {contract.setTeam(echipaUpdated);}
            }
            if (echipa.getNume().equals(nume)) {
                echipa.setId(echipaUpdated.getId());
                echipa.setNume(echipaUpdated.getNume());
                echipa.setAntrenor(echipaUpdated.getAntrenor());
                echipa.setJucatori(echipaUpdated.getJucatori());
                echipa.setStadion(echipaUpdated.getStadion());
                return;
            }
        }
    }

    @Override
    public void delete(Echipa echipa) {
        echipe.remove(echipa);

        MeciDao meciDao = new MeciDao();
        for(Meci meci : meciDao.findAllMeci()) {
            if(meci.getEchipa1().getNume().equals(echipa.getNume()) || meci.getEchipa2().getNume().equals(echipa.getNume())) {
                meciDao.delete(meci);
            }
        }

        ContractDao contractDao = new ContractDao();
        for(Contract contract : contractDao.findAllContract()) {
            if(contract.getTeam().getNume().equals(echipa.getNume())) {
                contractDao.delete(contract);
            }
        }
    }

    public void removeAntrenorFromEchipa(Antrenor antrenor) {
        for (Echipa echipa : echipe) {
            if (echipa.getAntrenor() != null && echipa.getAntrenor().equals(antrenor)) {
                echipa.setAntrenor(null);
            }
        }
    }

    public void removeJucatorFromEchipa(Jucator jucator) {
        for (Echipa echipa : echipe) {
            if (echipa.getJucatori() != null) {
                echipa.getJucatori().remove(jucator);
            }
        }
    }

    public void removeStadionFromEchipa(Stadion stadion) {
        for (Echipa echipa : echipe) {
            if (echipa.getStadion() != null && echipa.getStadion().equals(stadion)) {
                echipa.setStadion(null);
            }
        }
    }

    public List<Echipa> findAllEchipa() {
        return new ArrayList<>(echipe);
    }
}
