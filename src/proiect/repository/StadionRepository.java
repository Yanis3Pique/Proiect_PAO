package proiect.repository;

import proiect.model.Echipa;
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
            for(Stadion stadion1 : stadions){
                if(stadion1.getNume().equals(updatedStadion.getNume())){
                    System.out.println("The stadium named " + updatedStadion.getNume() + " already exists!");
                    return;
                }
            }
            EchipaRepository echipaRepository = new EchipaRepository();
            for(Echipa echipa : echipaRepository.findAllEchipa()){
                if(echipa.getStadion().getNume().equals(nume)){
                    echipa.getStadion().setNume(updatedStadion.getNume());
                    echipa.getStadion().setCapacitate(updatedStadion.getCapacitate());
                    echipa.getStadion().setLocatie(updatedStadion.getLocatie());
                }
            }
            if (stadion.getNume().equals(nume)) {
                stadion.setId(updatedStadion.getId());
                stadion.setNume(updatedStadion.getNume());
                stadion.setCapacitate(updatedStadion.getCapacitate());
                stadion.setLocatie(updatedStadion.getLocatie());
            }
        }
    }

    public void delete(Stadion stadion) {
        stadions.remove(stadion);

        EchipaRepository echipaRepository = new EchipaRepository();
        echipaRepository.removeStadionFromEchipa(stadion);
    }

    public List<Stadion> findAllStadion() {
        return new ArrayList<>(stadions);
    }
}