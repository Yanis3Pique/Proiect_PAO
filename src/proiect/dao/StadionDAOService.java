package proiect.dao;

import proiect.model.Stadion;
import proiect.repository.StadionRepository;

public class StadionDAOService {
    private StadionRepository stadionRepository;

    public StadionDAOService() {
        this.stadionRepository = new StadionRepository();
    }

    public void addStadion(Stadion stadion) {
        stadionRepository.create(stadion);
        System.out.println("Stadion adaugat: " + stadion.getNume());
    }

    public Stadion getStadionByName(String nume) {
        return stadionRepository.read(nume);
    }

    public void updateStadion(String nume, Stadion updatedStadion) {
        stadionRepository.update(nume, updatedStadion);
        System.out.println("Stadion updatat: " + nume);
    }

    public void removeStadion(Stadion stadion) {
        stadionRepository.delete(stadion);
        System.out.println("Stadion sters: " + stadion.getNume());
    }

    public void listAllStadions() {
        stadionRepository.findAllStadion().forEach(System.out::println);
    }
}