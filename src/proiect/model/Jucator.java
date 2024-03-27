package proiect.model;

import java.util.Objects;

public class Jucator extends Angajat {
    private String pozitie;
    private int numarTricou;

    public Jucator(int id, String nume, String prenume, String nationalitate, int varsta, double salariu, int numarTricou) {
        super(id, nume, prenume, nationalitate, varsta, salariu);
        this.numarTricou = numarTricou;
    }

    public String getPozitie() {
        return pozitie;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    public int getNumarTricou() {
        return numarTricou;
    }

    public void setNumarTricou(int numarTricou) {
        this.numarTricou = numarTricou;
    }

    @Override
    public String toString() {
        return "Jucator:" + '\n' +
                "ID: " + getId() + '\n' +
                "Nume: " + getNume() + " " + getPrenume() + '\n' +
                "Nationalitate: " + getNationalitate() + '\n' +
                "Varsta: " + getVarsta() + '\n' +
                "Salariu: " + getSalariu() + "$" + '\n' +
                "Pozitie: " + getPozitie() + '\n' +
                "Nr. tricou: " + getNumarTricou() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Jucator jucator = (Jucator) o;
        return numarTricou == jucator.numarTricou && Objects.equals(pozitie, jucator.pozitie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pozitie, numarTricou);
    }
}