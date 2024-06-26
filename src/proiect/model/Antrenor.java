package proiect.model;

import java.util.Objects;

public class Antrenor extends Angajat {
    private int angajatId;
    private int aniExperienta;

    public Antrenor() {
        super();
    }

    public Antrenor(int id, int angajatId, String nume, String prenume, String nationalitate, int varsta, double salariu, int aniExperienta) {
        super(id, nume, prenume, nationalitate, varsta, salariu);
        this.angajatId = angajatId;
        this.aniExperienta = aniExperienta;
    }

    public int getAngajatId() {
        return angajatId;
    }

    public void setAngajatId(int angajatId) {
        this.angajatId = angajatId;
    }

    public int getAniExperienta() {
        return aniExperienta;
    }

    public void setAniExperienta(int aniExperienta) {
        this.aniExperienta = aniExperienta;
    }

    @Override
    public String toString() {
        return "Coach:" + '\n' +
                "ID: " + getId() + '\n' +
                "Name: " + getNume() + " " + getPrenume() + '\n' +
                "Nationality: " + getNationalitate() + '\n' +
                "Age: " + getVarsta() + '\n' +
                "Salary: " + getSalariu() + "$" + '\n' +
                "Experience(years): " + getAniExperienta() + '\n';
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