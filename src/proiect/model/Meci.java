package proiect.model;

import java.util.Objects;

public class Meci {
    int id;
    private Echipa echipa1;
    private Echipa echipa2;
    private String data;
    private int scor1;
    private int scor2;
    private Stadion stadion;

    public Meci() {}

    public Meci(int id, Echipa echipa1, Echipa echipa2, String data, int scor1, int scor2, Stadion stadion) {
        this.id = id;
        this.echipa1 = echipa1;
        this.echipa2 = echipa2;
        this.data = data;
        this.scor1 = scor1;
        this.scor2 = scor2;
        this.stadion = stadion;
    }

    public Echipa getEchipa1() {
        return echipa1;
    }

    public void setEchipa1(Echipa echipa1) {
        this.echipa1 = echipa1;
    }

    public Echipa getEchipa2() {
        return echipa2;
    }

    public void setEchipa2(Echipa echipa2) {
        this.echipa2 = echipa2;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getScor1() {
        return scor1;
    }

    public void setScor1(int scor1) {
        this.scor1 = scor1;
    }

    public int getScor2() {
        return scor2;
    }

    public void setScor2(int scor2) {
        this.scor2 = scor2;
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
        return "Game:" + '\n' +
                "Home Team: " + getEchipa1().getNume() + '\n' +
                "Away Team: " + getEchipa2().getNume() + '\n' +
                "Date: " + getData() + '\n' +
                "Result: " + getScor1() + " - " + getScor2() + '\n' +
                "Stadium: " + getStadion().getNume() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meci meci = (Meci) o;
        return id == meci.id && scor1 == meci.scor1 && scor2 == meci.scor2 && Objects.equals(echipa1, meci.echipa1) && Objects.equals(echipa2, meci.echipa2) && Objects.equals(data, meci.data) && Objects.equals(stadion, meci.stadion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, echipa1, echipa2, data, scor1, scor2, stadion);
    }
}