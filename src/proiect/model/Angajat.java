package proiect.model;

import java.util.Objects;

public abstract class Angajat {
    private int id;
    private String nume;
    private String prenume;
    private String nationalitate;
    private int varsta;
    private double salariu;

    public Angajat() {}

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
                "Name: " + getNume() + " " + getPrenume() + '\n' +
                "Nationality: " + getNationalitate() + '\n' +
                "Age: " + getVarsta() + '\n' +
                "Salary: " + getSalariu() + '\n';
    }
}