package proiect.daoservices;

import proiect.model.Contract;
import proiect.model.Sponsor;
import proiect.dao.SponsorDao;
import proiect.utils.InvalidDataException;

import java.sql.SQLException;
import java.util.List;

public class SponsorRepositoryService {
    private SponsorDao sponsorDao = SponsorDao.getInstance();
    public SponsorRepositoryService() throws SQLException {}

    public void printAllSponsors() {
        try {
            List<Sponsor> sponsors = sponsorDao.findAllSponsor();
            if(sponsors != null){
                sponsors.forEach(System.out:: println);
            } else {
                System.out.println("There is no sponsor.");
            }

        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    public List<Sponsor> getAllSponsors() throws SQLException {
        return sponsorDao.findAllSponsor();
    }

    public Sponsor getSponsorByName(String name) throws SQLException {
        Sponsor sponsor = sponsorDao.read(name);

        if (sponsor != null) {
            return sponsor;
        }

        return sponsor;
    }

    public Sponsor getSponsorById(int id) throws SQLException {
        Sponsor sponsor = sponsorDao.readByID(id);

        if (sponsor != null) {
            return sponsor;
        }

        return sponsor;
    }

    public void addSponsor(Sponsor sponsor) throws InvalidDataException {
        try {
            if(sponsor != null){
                if(sponsorDao.read(sponsor.getName()) != null)
                    throw new InvalidDataException("Sponsor already exists!");

                sponsorDao.create(sponsor);
                System.out.println("Sponsor added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void updateSponsor(String name, Sponsor sponsor) throws InvalidDataException {
        try {
            if(sponsor != null){
                if(!sponsorDao.checkUniqueName(sponsor.getName()))
                    throw new InvalidDataException("Sponsor already exists!");

                sponsorDao.update(name, sponsor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void removeSponsor(Sponsor sponsor) {
        try {
            if (sponsor == null) return;

            sponsorDao.delete(sponsor);
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getSQLState() + " " + e.getMessage());
        }
    }
}
