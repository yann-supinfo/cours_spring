package org.example;

public class BuyCardModular {
    private double cardPrice;
    private ComputeReductionInterface reductionStrategy;

    public BuyCardModular(double cardPrice, ComputeReductionInterface reductionStrategy) {
        this.cardPrice = cardPrice;
        this.reductionStrategy = reductionStrategy;
    }

    public double calculatePrice() {
        return reductionStrategy.compute(cardPrice);
    }

}