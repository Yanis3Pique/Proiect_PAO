package proiect.dao;

import proiect.model.Sponsor;
import proiect.repository.SponsorRepository;

import java.util.List;

public class SponsorDAOService {
    private final SponsorRepository sponsorRepository = new SponsorRepository();

    public void addSponsor(Sponsor sponsor) {
        sponsorRepository.createSponsor(sponsor);
    }

    public Sponsor getSponsorByName(String name) {
        return sponsorRepository.readSponsor(name);
    }

    public void updateSponsor(String name, Sponsor sponsor) {
        sponsorRepository.updateSponsor(name, sponsor);
    }

    public void removeSponsor(Sponsor sponsor) {
        sponsorRepository.deleteSponsor(sponsor);
    }

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAllSponsor();
    }
}
