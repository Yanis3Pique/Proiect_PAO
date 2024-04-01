package proiect.dao;

import proiect.model.Contract;
import proiect.model.Sponsor;

import java.util.ArrayList;
import java.util.List;

public class SponsorDao {
    private static int nextId = 1;
    private static List<Sponsor> sponsors = new ArrayList<>();

    public void createSponsor(Sponsor sponsor) {
        sponsor.setId(nextId++);
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
                for(Contract contract : new ContractDao().findAllContract()) {
                    if(contract.getSponsor().equals(sponsor)) {
                        contract.setSponsor(sponsorUpdated);
                    }
                }
                sponsor.setId(sponsorUpdated.getId());
                sponsor.setName(sponsorUpdated.getName());
                sponsor.setCountry(sponsorUpdated.getCountry());
                return;
            }
        }
    }

    public void deleteSponsor(Sponsor sponsor) {
        sponsors.remove(sponsor);
        ContractDao contractDao = new ContractDao();
        for(Contract contract : contractDao.findAllContract()) {
            if(contract.getSponsor().equals(sponsor)) {
                contractDao.deleteContract(contract.getTeam().getNume(), contract.getSponsor().getName());
            }
        }
    }

    public List<Sponsor> findAllSponsor() {
        return new ArrayList<>(sponsors);
    }
}