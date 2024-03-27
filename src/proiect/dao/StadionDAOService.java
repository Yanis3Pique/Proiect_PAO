package proiect.dao;

import proiect.model.Stadion;
import proiect.repository.StadionRepository;

import java.util.List;

public class StadionDAOService {
    private final StadionRepository stadionRepository = new StadionRepository();

    public void addStadion(Stadion stadion) {
        stadionRepository.create(stadion);
    }

    public Stadion getStadionByName(String name) {
        return stadionRepository.read(name);
    }

    public void updateStadion(String name, Stadion updatedStadion) {
        stadionRepository.update(name, updatedStadion);
    }

    public void removeStadion(Stadion stadion) {
        stadionRepository.delete(stadion);
    }

    public List<Stadion> getAllStadions() {
        return stadionRepository.findAllStadion();
    }
}
