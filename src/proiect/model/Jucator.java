package proiect.model;

import java.util.Objects;

public class Jucator extends Angajat {
    private int id_echipa;
    private String pozitie;
    private int numarTricou;

    public Jucator() {
        super();
    }

    public Jucator(int id, String nume, String prenume, String nationalitate, int varsta, double salariu, int id_echipa, String pozitie, int numarTricou) {
        super(id, nume, prenume, nationalitate, varsta, salariu);
        this.id_echipa = id_echipa;
        this.pozitie = pozitie;
        this.numarTricou = numarTricou;
    }

    public int getId_echipa() {
        return id_echipa;
    }

    public void setId_echipa(int id_echipa) {
        this.id_echipa = id_echipa;
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
        return "Player:" + '\n' +
                "ID: " + getId() + '\n' +
                "Team ID: " + id_echipa + '\n' +
                "Name: " + getNume() + " " + getPrenume() + '\n' +
                "Nationality: " + getNationalitate() + '\n' +
                "Age: " + getVarsta() + '\n' +
                "Salary: " + getSalariu() + "$" + '\n' +
                "Position: " + getPozitie() + '\n' +
                "Shirt no.: " + getNumarTricou() + '\n';
    }
}