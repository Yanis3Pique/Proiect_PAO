package proiect.daoservices;

import proiect.model.Sponsor;
import proiect.dao.SponsorDao;

import java.util.List;

public class SponsorRepositoryService {
    private final SponsorDao sponsorDao = new SponsorDao();

    public void addSponsor(Sponsor sponsor) {
        sponsorDao.create(sponsor);
    }

    public Sponsor getSponsorByName(String name) {
        return sponsorDao.read(name);
    }

    public void updateSponsor(String name, Sponsor sponsor) {
        sponsorDao.update(name, sponsor);
    }

    public void removeSponsor(Sponsor sponsor) {
        sponsorDao.delete(sponsor);
    }

    public List<Sponsor> getAllSponsors() {
        return sponsorDao.findAllSponsor();
    }
}
