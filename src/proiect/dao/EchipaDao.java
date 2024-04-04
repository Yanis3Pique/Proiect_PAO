package proiect.dao;

import proiect.model.*;

import java.util.ArrayList;
import java.util.List;

public class EchipaDao {
    private static int nextId = 1;
    private static List<Echipa> echipe = new ArrayList<>();

    public void createEchipa(Echipa echipa) {
        echipa.setId(nextId++);
        echipe.add(echipa);
    }

    public Echipa readEchipa(String nume) {
        for (Echipa echipa : echipe) {
            if (echipa.getNume().equals(nume)) {
                return echipa;
            }
        }
        return null;
    }

    public void updateEchipa(String nume, Echipa echipaUpdated) {
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

    public void deleteEchipa(String nume) {
        echipe.removeIf(echipa -> echipa.getNume().equals(nume));

        MeciDao meciDao = new MeciDao();
        for(Meci meci : meciDao.findAllMeci()) {
            if(meci.getEchipa1().getNume().equals(nume) || meci.getEchipa2().getNume().equals(nume)) {
                meciDao.deleteMeci(meci);
            }
        }

        ContractDao contractDao = new ContractDao();
        for(Contract contract : contractDao.findAllContract()) {
            if(contract.getTeam().getNume().equals(nume)) {
                contractDao.deleteContract(contract.getTeam().getNume(), contract.getSponsor().getName());
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

    public List<Echipa> findAllEchipa() {
        return new ArrayList<>(echipe);
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
}