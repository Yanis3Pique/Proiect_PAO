package proiect.daoservices;

import proiect.model.Stadion;
import proiect.dao.StadionDao;

import java.util.List;

public class StadionRepositoryService {
    private final StadionDao stadionDao = new StadionDao();

    public void addStadion(Stadion stadion) {
        stadionDao.create(stadion);
    }

    public Stadion getStadionByName(String name) {
        Stadion stadion = stadionDao.read(name);

        if (stadion != null) {
            return stadion;
        } else {
            System.out.println("Stadium has not been found!");
        }

        return null;
    }

    public void updateStadion(String name, Stadion updatedStadion) {
        stadionDao.update(name, updatedStadion);
    }

    public void removeStadion(Stadion stadion) {
        stadionDao.delete(stadion);
    }

    public List<Stadion> getAllStadions() {
        return stadionDao.findAll();
    }
}
