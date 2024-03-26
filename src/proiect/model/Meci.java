package proiect.model;

import java.util.Objects;

public class Meci {
    private Echipa echipa1;
    private Echipa echipa2;
    private String data;
    private int scor1;
    private int scor2;
    private Stadion stadion;

    public Meci(Echipa echipa1, Echipa echipa2, String data, int scor1, int scor2, Stadion stadion) {
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

    @Override
    public String toString() {
        return "Meci:" + '\n' +
                "Echipa gazda: " + getEchipa1().getNume() + '\n' +
                "Echipa oaspete: " + getEchipa2().getNume() + '\n' +
                "Data: " + getData() + '\n' +
                "Rezultat: " + getScor1() + " - " + getScor2() + '\n' +
                "Stadion: " + getStadion().getNume() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meci meci = (Meci) o;
        return scor1 == meci.scor1 && scor2 == meci.scor2 && Objects.equals(echipa1, meci.echipa1) && Objects.equals(echipa2, meci.echipa2) && Objects.equals(data, meci.data) && Objects.equals(stadion, meci.stadion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(echipa1, echipa2, data, scor1, scor2, stadion);
    }
}