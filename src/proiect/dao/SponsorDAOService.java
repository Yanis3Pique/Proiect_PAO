package proiect.dao;

import proiect.model.Sponsor;
import proiect.repository.SponsorRepository;

import java.util.ArrayList;

public class SponsorDAOService {
    private SponsorRepository sponsorRepository;

    public SponsorDAOService() {
        this.sponsorRepository = new SponsorRepository();
    }

    public void addSponsor(String name, String country) {
        Sponsor newSponsor = new Sponsor(name, country);
        sponsorRepository.createSponsor(newSponsor);
        System.out.println("Sponsor creat cu succes: " + name + " din " + country);
    }

    public void updateSponsor(String name, Sponsor sponsor) {
        sponsorRepository.updateSponsor(name, sponsor);
        System.out.println("Sponsor updated: " + name);
    }

    public boolean removeSponsor(String name) {
        for (Sponsor sponsor : sponsorRepository.findAllSponsor()) {
            if (sponsor.getName().equalsIgnoreCase(name)) {
                sponsorRepository.deleteSponsor(sponsor);
                System.out.println("Sponsor eliminat cu success: " + name);
                return true;
            }
        }
        System.out.println("Sponsorul nu a fost gasit!");
        return false;
    }

    public void listAllSponsors() {
        sponsorRepository.findAllSponsor().forEach(sponsor -> System.out.println(sponsor));
    }
}