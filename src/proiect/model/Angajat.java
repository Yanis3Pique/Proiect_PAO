package proiect.model;

import java.util.Objects;

public abstract class Angajat {
    private int id;
    private String nume;
    private String prenume;
    private String nationalitate;
    private int varsta;
    private double salariu;

    public Angajat(int id, String nume, String prenume, String nationalitate, int varsta, double salariu) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.nationalitate = nationalitate;
        this.varsta = varsta;
        this.salariu = salariu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNationalitate() {
        return nationalitate;
    }

    public void setNationalitate(String nationalitate) {
        this.nationalitate = nationalitate;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Angajat:" + '\n' +
                "ID: " + getId() + '\n' +
                "Nume: " + getNume() + " " + getPrenume() + '\n' +
                "Nationalitate: " + getNationalitate() + '\n' +
                "Varsta: " + getVarsta() + '\n' +
                "Salariu: " + getSalariu() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angajat angajat = (Angajat) o;
        return varsta == angajat.varsta && Double.compare(salariu, angajat.salariu) == 0 && Objects.equals(nume, angajat.nume) && Objects.equals(prenume, angajat.prenume) && Objects.equals(nationalitate, angajat.nationalitate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, nationalitate, varsta, salariu);
    }
}