package proiect.model;

import java.util.Objects;

public class Stadion {
    private String nume;
    private int capacitate;
    private String locatie;

    public Stadion(String nume, int capacitate, String locatie) {
        this.nume = nume;
        this.capacitate = capacitate;
        this.locatie = locatie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    @Override
    public String toString() {
        return "Stadion:" +
                "Nume: " + getNume() + '\n' +
                "Capacitate: " + getCapacitate() + '\n' +
                "Locatie: " + getLocatie() + 'n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stadion stadion = (Stadion) o;
        return capacitate == stadion.capacitate && Objects.equals(nume, stadion.nume) && Objects.equals(locatie, stadion.locatie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, capacitate, locatie);
    }
}