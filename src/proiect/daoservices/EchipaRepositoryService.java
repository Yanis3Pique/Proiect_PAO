package proiect.daoservices;

import proiect.model.Contract;
import proiect.model.Echipa;
import proiect.dao.EchipaDao;
import proiect.model.Jucator;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.List;

public class EchipaRepositoryService {
    private EchipaDao echipaDao = EchipaDao.getInstance();

    public EchipaRepositoryService() throws SQLException {}

    public List<Echipa> getAllEchipe() throws SQLException {
        return echipaDao.findAllEchipa();
    }

    public void addEchipa(Echipa echipa) throws InvalidDataException {
        try {
            if(echipa != null){
                if(echipaDao.read(echipa.getNume()) != null)
                    throw new InvalidDataException("We already have a team with this name!");

                echipaDao.create(echipa);
//                System.out.println("Team added!");
            }
        } catch (SQLException e) {
            System.out.println("Creation failed: " + e.getMessage());
        }
    }

    public Echipa getEchipaByName(String nume) throws SQLException {
        Echipa echipa = echipaDao.read(nume);

        if (echipa != null) {
            return echipa;
        }

        return null;
    }

    public Echipa getEchipaById(int id) throws SQLException {
        Echipa echipa = echipaDao.readByID(id);

        if (echipa != null) {
            return echipa;
        }

        return null;
    }

    public void updateEchipa(String nume, Echipa echipaUpdated) throws InvalidDataException {
        try {
            Echipa echipa = echipaDao.read(nume);
            if (echipa == null) {
                throw new InvalidDataException("Team not found!");
            }

            echipaDao.update(nume, echipaUpdated);
//            System.out.println("Team updated!");
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void removeEchipa(String nume) throws SQLException {
        Echipa echipa = echipaDao.read(nume);
        try {
            if (echipa == null) return;

            echipaDao.delete(echipa);

        } catch (SQLException e) {
            System.out.println("Deletion failed: " + e.getMessage());
        }
    }

    public void getJucatori(Echipa echipa) throws SQLException {
        List<Jucator> jucatori = echipaDao.getJucatoriByEchipa(echipa);
        if(jucatori != null) {
            for(Jucator jucator : jucatori) {
                System.out.println(jucator);
            }
        }
    }
}