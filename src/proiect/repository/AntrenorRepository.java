package repository;

import proiect.model.Antrenor;

import java.util.ArrayList;
import java.util.List;

public class AntrenorRepository {

    private final List<Antrenor> antrenori = new ArrayList<>();

    public void create(Antrenor antrenor) {
        antrenori.add(antrenor);
    }

    public Antrenor read(String nume) {
        for (Antrenor antrenor : antrenori) {
            if (antrenor.getNume().equals(nume)) {
                return antrenor;
            }
        }
        return null;
    }

    public void update(Antrenor updatedAntrenor) {
        antrenori.forEach(antrenor -> {
            if (antrenor.getNume().equals(updatedAntrenor.getNume())) {
                antrenor.setPrenume(updatedAntrenor.getPrenume());
                antrenor.setNationalitate(updatedAntrenor.getNationalitate());
                antrenor.setVarsta(updatedAntrenor.getVarsta());
                antrenor.setSalariu(updatedAntrenor.getSalariu());
                antrenor.setAniExperienta(updatedAntrenor.getAniExperienta());
            }
        });
    }

    public void delete(String nume) {
        antrenori.removeIf(antrenor -> antrenor.getNume().equals(nume));
    }

}
