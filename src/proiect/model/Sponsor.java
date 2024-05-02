package proiect.model;

import java.util.Objects;

public class Sponsor {
    int id;
    private String name;
    private String country;

    public Sponsor() {}

    public Sponsor(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sponsor:" + '\n' +
                "ID: " + id + '\n' +
                "Name: " + getName() + '\n' +
                "Country: " + getCountry() + '\n';
    }
}

