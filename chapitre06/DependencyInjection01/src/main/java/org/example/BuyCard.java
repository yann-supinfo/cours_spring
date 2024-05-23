package org.example;

public class BuyCard {
    private double cardPrice;
    private double percentageReduction;

    public BuyCard(double cardPrice, double percentageReduction) {
        this.cardPrice = cardPrice;
        this.percentageReduction = percentageReduction;
    }

    public double calculatePrice() {
        return cardPrice * (1 - percentageReduction);
    }

}