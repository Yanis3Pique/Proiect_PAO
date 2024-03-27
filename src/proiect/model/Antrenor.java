package proiect.model;

import java.util.Objects;

public class Antrenor extends Angajat {
    private int aniExperienta;

    public Antrenor(int id, String nume, String prenume, String nationalitate, int varsta, double salariu, int aniExperienta) {
        super(id, nume, prenume, nationalitate, varsta, salariu);
        this.aniExperienta = aniExperienta;
    }

    public int getAniExperienta() {
        return aniExperienta;
    }

    public void setAniExperienta(int aniExperienta) {
        this.aniExperienta = aniExperienta;
    }

    @Override
    public String toString() {
        return "Antrenor:" + '\n' +
                "ID: " + getId() + '\n' +
                "Nume: " + getNume() + " " + getPrenume() + '\n' +
                "Nationalitate: " + getNationalitate() + '\n' +
                "Varsta: " + getVarsta() + '\n' +
                "Salariu: " + getSalariu() + "$" + '\n' +
                "Ani de experienta: " + getAniExperienta() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Antrenor antrenor = (Antrenor) o;
        return aniExperienta == antrenor.aniExperienta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), aniExperienta);
    }
}