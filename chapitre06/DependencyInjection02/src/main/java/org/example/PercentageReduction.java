package org.example;

public class PercentageReduction implements ComputeReductionInterface {
    private double percentage;

    public PercentageReduction(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double compute(double cardPrice) {
        return cardPrice - (cardPrice * percentage);
    }
}