package proiect.repository;

import proiect.model.Sponsor;

import java.util.ArrayList;
import java.util.List;

public class SponsorRepository {
    private static List<Sponsor> sponsors = new ArrayList<>();

    public void createSponsor(Sponsor sponsor) {
        sponsors.add(sponsor);
    }

    public Sponsor readSponsor(String name) {
        for (Sponsor sponsor : sponsors) {
            if (sponsor.getName().equalsIgnoreCase(name)) {
                return sponsor;
            }
        }
        return null;
    }

    public void updateSponsor(String name, Sponsor sponsorUpdated) {
        for(Sponsor sponsor : sponsors) {
            if(sponsor.getName().equalsIgnoreCase(name)) {
                sponsor.setName(sponsorUpdated.getName());
                sponsor.setCountry(sponsorUpdated.getCountry());
                return;
            }
        }
    }

    public void deleteSponsor(Sponsor sponsor) {
        sponsors.remove(sponsor);
    }

    public List<Sponsor> findAllSponsor() {
        return new ArrayList<>(sponsors);
    }
}