package org.example.dto;

public class SaisonFormDto {
    private String libelle;
    private int year;

    public SaisonFormDto() {
    }

    public SaisonFormDto(String libelle, int year) {
        this.libelle = libelle;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
