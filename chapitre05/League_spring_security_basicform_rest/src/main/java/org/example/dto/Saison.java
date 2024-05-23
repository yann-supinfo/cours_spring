package org.example.dto;

public class Saison {
    private Long id;
    private String libelle;
    private int year;

    public Saison() {
    }

    public Saison(Long id, String libelle, int year) {
        this.id = id;
        this.libelle = libelle;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}