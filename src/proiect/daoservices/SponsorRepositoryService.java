package proiect.daoservices;

import proiect.model.Sponsor;
import proiect.dao.SponsorDao;

import java.util.List;

public class SponsorRepositoryService {
    private final SponsorDao sponsorDao = new SponsorDao();

    public void addSponsor(Sponsor sponsor) {
        sponsorDao.createSponsor(sponsor);
    }

    public Sponsor getSponsorByName(String name) {
        return sponsorDao.readSponsor(name);
    }

    public void updateSponsor(String name, Sponsor sponsor) {
        sponsorDao.updateSponsor(name, sponsor);
    }

    public void removeSponsor(Sponsor sponsor) {
        sponsorDao.deleteSponsor(sponsor);
    }

    public List<Sponsor> getAllSponsors() {
        return sponsorDao.findAllSponsor();
    }
}
