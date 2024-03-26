package proiect.model;

import java.util.Objects;

public class Sponsor {
    private String name;
    private String country;

    public Sponsor(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Sponsor:" + '\n' +
                "Nume: " + getName() + '\n' +
                "Tara de origine: " + getCountry() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sponsor sponsor = (Sponsor) o;
        return Objects.equals(name, sponsor.name) && Objects.equals(country, sponsor.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}

