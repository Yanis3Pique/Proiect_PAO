package proiect.dao;

import proiect.model.Contract;
import proiect.model.Sponsor;

import java.util.ArrayList;
import java.util.List;

public class SponsorDao implements DaoInterface<Sponsor> {
    private static int nextId = 1;
    private static List<Sponsor> sponsors = new ArrayList<>();

    @Override
    public void create(Sponsor sponsor) {
        sponsor.setId(nextId++);
        sponsors.add(sponsor);
    }

    @Override
    public Sponsor read(String name) {
        for (Sponsor sponsor : sponsors) {
            if (sponsor.getName().equalsIgnoreCase(name)) {
                return sponsor;
            }
        }
        return null;
    }

    @Override
    public void update(String name, Sponsor sponsorUpdated) {
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

    @Override
    public void delete(Sponsor sponsor) {
        sponsors.remove(sponsor);
        ContractDao contractDao = new ContractDao();
        for(Contract contract : contractDao.findAllContract()) {
            if(contract.getSponsor().equals(sponsor)) {
                contractDao.delete(contract);
            }
        }
    }

    public List<Sponsor> findAllSponsor() {
        return new ArrayList<>(sponsors);
    }
}