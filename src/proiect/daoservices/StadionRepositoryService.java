package proiect.daoservices;

import proiect.dao.EchipaDao;
import proiect.model.Echipa;
import proiect.model.Stadion;
import proiect.dao.StadionDao;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.List;

public class StadionRepositoryService {
    private StadionDao stadionDao = StadionDao.getInstance();

    public StadionRepositoryService() throws SQLException {}

    public void printAllStadiums() throws SQLException {
        List<Stadion> stadions = stadionDao.findAllStadion();
        if(stadions != null){
            stadions.forEach(System.out:: println);
        } else {
            System.out.println("There is no category.");
        }
    }

    public List<Stadion> getAllStadions() throws SQLException {
        return stadionDao.findAllStadion();
    }

    public void addStadion(Stadion stadion) throws InvalidDataException {
        try {
            if(stadion != null){
                if(stadionDao.read(String.valueOf(stadion.getId())) != null)
                    throw new InvalidDataException("We already have a stadium with this ID!");
                if(stadionDao.read(stadion.getNume()) != null)
                    throw new InvalidDataException("We already have a stadium with this name!");

                stadionDao.create(stadion);
                System.out.println("Stadium added!");
            }
        } catch (SQLException e) {
            System.out.println("Creation failed: " + e.getMessage());
        }
    }

    public Stadion getStadionByName(String name) throws SQLException {
        Stadion stadion = stadionDao.read(name);

        if (stadion != null) {
            return stadion;
        } else {
            System.out.println("Stadium has not been found!");
        }

        return null;
    }

    public Stadion getStadionById(int id) throws SQLException {
        Stadion stadion = stadionDao.readByID(id);

        if (stadion != null) {
            return stadion;
        } else {
            System.out.println("Stadium has not been found!");
        }

        return null;
    }

    public void updateStadion(String name, Stadion updatedStadion) throws InvalidDataException {
        try {
            Stadion stadion = stadionDao.read(name);
            if (stadion != null) {
                if(!stadionDao.checkUniqueName(updatedStadion.getNume()))
                    throw new InvalidDataException("We already have a stadium with this name!");
            }

            stadionDao.update(name, updatedStadion);
            System.out.println("Stadium updated!");
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void removeStadion(Stadion stadion) {
        try {
            if (stadion == null) return;

            List<Echipa> echipe = new EchipaRepositoryService().getAllEchipe();
            if(echipe != null) {
                for(Echipa e : echipe) {
                    if(e.getStadion().getId() == stadion.getId()) {
                        e.setStadion(null);
                    }
                }
            }
            stadionDao.delete(stadion);

        } catch (SQLException e) {
            System.out.println("SQLException " + e.getSQLState() + " " + e.getMessage());
        }
    }
}
