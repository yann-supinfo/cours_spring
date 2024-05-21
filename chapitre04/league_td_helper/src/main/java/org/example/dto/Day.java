package org.example.dto;

public class Day {
    private Long id;
    private int numero;
    private Long saisonId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Long getSaisonId() {
        return saisonId;
    }

    public void setSaisonId(Long saisonId) {
        this.saisonId = saisonId;
    }
}
