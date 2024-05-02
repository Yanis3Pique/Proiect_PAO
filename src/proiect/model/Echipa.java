package proiect.model;

import java.util.ArrayList;
import java.util.Objects;

public class Echipa {
    int id;
    private String nume;
    private Antrenor antrenor;
    private Stadion stadion;

    public Echipa() {}

    public Echipa(int id, String nume, Antrenor antrenor, Stadion stadion) {
        this.id = id;
        this.nume = nume;
        this.antrenor = antrenor;
        this.stadion = stadion;
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
        return "Team:" + '\n' +
                "ID: " + getId() + '\n' +
                "Name: " + getNume() + '\n' +
                "Coach: " + getAntrenor().getNume() + '\n' +
                "Stadium: " + getStadion().getNume() + '\n';
    }
}