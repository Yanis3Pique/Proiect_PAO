package proiect.model;

import java.util.Objects;

public class Contract {
    int id;
    private Echipa team;
    private Sponsor sponsor;
    private int durationYears;
    private double sumMoney;

    public Contract() {}

    public Contract(int id, Echipa team, Sponsor sponsor, int durationYears, double sumMoney) {
        this.id = id;
        this.team = team;
        this.sponsor = sponsor;
        this.durationYears = durationYears;
        this.sumMoney = sumMoney;
    }

    public Echipa getTeam() {
        return team;
    }

    public void setTeam(Echipa team) {
        this.team = team;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public int getDurationYears() {
        return durationYears;
    }

    public void setDurationYears(int durationYears) {
        this.durationYears = durationYears;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contract no. " + getId() + ", signed between " + team.getNume() + " and " + sponsor.getName() + '\n' +
                "Duration: " + getDurationYears() + " years" + '\n' +
                "Amount: " + getSumMoney() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id && durationYears == contract.durationYears && Double.compare(sumMoney, contract.sumMoney) == 0 && Objects.equals(team, contract.team) && Objects.equals(sponsor, contract.sponsor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, sponsor, durationYears, sumMoney);
    }
}
