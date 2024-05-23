package org.example;

public class FixedReduction implements ComputeReductionInterface {
    private double reduction;

    public FixedReduction(double reduction) {
        this.reduction = reduction;
    }

    @Override
    public double compute(double cardPrice) {
        return cardPrice * (1 - reduction);
    }
}