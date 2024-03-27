package proiect.model;

import java.util.ArrayList;
import java.util.Objects;

public class Echipa {
    int id;
    private String nume;
    private Antrenor antrenor;
    private ArrayList<Jucator> jucatori;
    private Stadion stadion;

    public Echipa(int id, String nume, Antrenor antrenor, Stadion stadion) {
        this.id = id;
        this.nume = nume;
        this.antrenor = antrenor;
        this.stadion = stadion;
        this.jucatori = new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Antrenor getAntrenor() {
        return antrenor;
    }

    public void setAntrenor(Antrenor antrenor) {
        this.antrenor = antrenor;
    }

    public ArrayList<Jucator> getJucatori() {
        return jucatori;
    }

    public void setJucatori(ArrayList<Jucator> jucatori) {
        this.jucatori = jucatori;
    }

    public Stadion getStadion() {
        return stadion;
    }

    public void setStadion(Stadion stadion) {
        this.stadion = stadion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Echipa:").append('\n');
        sb.append("Nume: ").append(getNume()).append('\n');
        sb.append("Antrenor: ").append(getAntrenor().getNume()).append(' ').append(getAntrenor().getPrenume()).append('\n');

        sb.append("Jucatori:\n");
        for (Jucator jucator : getJucatori()) {
            sb.append(jucator.getNume()).append(' ').append(jucator.getPrenume()).append('\n');
        }

        sb.append("Stadion: ").append(getStadion().getNume()).append('\n');

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Echipa echipa = (Echipa) o;
        return Objects.equals(nume, echipa.nume) && Objects.equals(antrenor, echipa.antrenor) && Objects.equals(jucatori, echipa.jucatori) && Objects.equals(stadion, echipa.stadion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, antrenor, jucatori, stadion);
    }
}