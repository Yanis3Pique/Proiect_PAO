package proiect.repository;

import proiect.model.Stadion;
import java.util.ArrayList;
import java.util.List;

public class StadionRepository {
    private static List<Stadion> stadions = new ArrayList<>();

    public void create(Stadion stadion) {
        stadions.add(stadion);
    }

    public Stadion read(String nume) {
        for (Stadion stadion : stadions) {
            if (stadion.getNume().equals(nume)) {
                return stadion;
            }
        }
        return null;
    }

    public void update(String nume, Stadion updatedStadion) {
        for (Stadion stadion : stadions) {
            if (stadion.getNume().equals(nume)) {
                stadion.setNume(updatedStadion.getNume());
                stadion.setCapacitate(updatedStadion.getCapacitate());
                stadion.setLocatie(updatedStadion.getLocatie());
            }
        }
    }

    public void delete(Stadion stadion) {
        stadions.remove(stadion);
    }

    public List<Stadion> findAllStadion() {
        return new ArrayList<>(stadions);
    }
}