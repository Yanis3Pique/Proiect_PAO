package proiect.model;

public class Antrenor extends Angajat {
    private int aniExperienta;

    public Antrenor() {
        super();
    }

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
        return "Coach:" + '\n' +
                "ID: " + getId() + '\n' +
                "Name: " + getNume() + " " + getPrenume() + '\n' +
                "Nationality: " + getNationalitate() + '\n' +
                "Age: " + getVarsta() + '\n' +
                "Salary: " + getSalariu() + "$" + '\n' +
                "Experience(years): " + getAniExperienta() + '\n';
    }
}