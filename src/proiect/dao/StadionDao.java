package proiect.dao;

import proiect.model.Echipa;
import proiect.model.Stadion;
import java.util.ArrayList;
import java.util.List;

public class StadionDao implements DaoInterface<Stadion> {
    private static int nextId = 1;
    private static List<Stadion> stadions = new ArrayList<>();

    @Override
    public void create(Stadion stadion) {
        stadion.setId(nextId++);
        stadions.add(stadion);
    }

    @Override
    public Stadion read(String nume) {
        for (Stadion stadion : stadions) {
            if (stadion.getNume().equals(nume)) {
                return stadion;
            }
        }
        return null;
    }

    @Override
    public void update(String nume, Stadion updatedStadion) {
        for (Stadion stadion : stadions) {
            updateEchipaBasedOnStadion(nume, updatedStadion);

            if (stadion.getNume().equals(nume)) {
                stadion.setId(updatedStadion.getId());
                stadion.setNume(updatedStadion.getNume());
                stadion.setCapacitate(updatedStadion.getCapacitate());
                stadion.setLocatie(updatedStadion.getLocatie());
            }
        }
    }

    private void updateEchipaBasedOnStadion(String nume, Stadion updatedStadion) {
        EchipaDao echipaDao = new EchipaDao();
        for(Echipa echipa : echipaDao.findAllEchipa()){
            if(echipa.getStadion() != null && echipa.getStadion().getNume().equals(nume)){
                echipa.getStadion().setNume(updatedStadion.getNume());
                echipa.getStadion().setCapacitate(updatedStadion.getCapacitate());
                echipa.getStadion().setLocatie(updatedStadion.getLocatie());
            }
        }
    }

    @Override
    public void delete(Stadion stadion) {
        stadions.remove(stadion);

        EchipaDao echipaDao = new EchipaDao();
        echipaDao.removeStadionFromEchipa(stadion);
    }

    public List<Stadion> findAllStadion() {
        return new ArrayList<>(stadions);
    }
}