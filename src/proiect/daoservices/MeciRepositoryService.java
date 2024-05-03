package proiect.daoservices;

import proiect.model.Meci;
import proiect.dao.MeciDao;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.List;

public class MeciRepositoryService {
    private MeciDao meciDao = MeciDao.getInstance();

    public MeciRepositoryService() throws SQLException {}

    public void printAllMatches() throws SQLException {
        List<Meci> meciuri = meciDao.findAllMeci();
        if(meciuri != null){
            meciuri.forEach(System.out:: println);
        } else {
            System.out.println("There are no matches.");
        }
    }

    public List<Meci> getAllMeciuri() throws SQLException {
        return meciDao.findAllMeci();
    }

    public void addMeci(Meci meci) throws InvalidDataException {
        try {
            if(meci != null){
                // test if we already have a match between these two teams on this date
                if(meciDao.read(meci.getEchipa1().getNume() + "_" + meci.getEchipa2().getNume() + "_" + meci.getData()) != null)
                    throw new InvalidDataException("We already have a match between these two teams on this date!");

                meciDao.create(meci);
                System.out.println("Match added!");
            }
        } catch (SQLException e) {
            System.out.println("Creation failed: " + e.getMessage());
        }
    }

    public Meci getMeci(String numeEchipa1, String numeEchipa2, String data) throws SQLException {
        Meci meci = meciDao.read(numeEchipa1 + "_" + numeEchipa2 + "_" + data);

        if (meci != null) {
            return meci;
        } else {
            System.out.println("Match has not been found!");
        }

        return null;
    }

    public Meci getMeciById(int id) throws SQLException {
        Meci meci = meciDao.readByID(id);

        if (meci != null) {
            return meci;
        } else {
            System.out.println("Match has not been found!");
        }

        return null;
    }

    public void updateMeci(String numeEchipa1, String numeEchipa2, String data, Meci newMeci) throws InvalidDataException {
        try {
            if(newMeci != null) {
                if (meciDao.read(numeEchipa1 + "_" + numeEchipa2 + "_" + data) == null)
                    throw new InvalidDataException("Match not found!");
            }

            meciDao.update(numeEchipa1 + "_" + numeEchipa2 + "_" + data, newMeci);
            System.out.println("Match updated!");
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void removeMeci(Meci meci) {
        try {
            if (meci == null) return;

            meciDao.delete(meci);
            System.out.println("Match removed!");
        } catch (SQLException e) {
            System.out.println("Removal failed: " + e.getMessage());
        }
    }
}
