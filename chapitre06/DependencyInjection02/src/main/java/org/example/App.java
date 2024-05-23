package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ComputeReductionInterface fixedReduction = new FixedReduction(0.1);
        BuyCardModular buyCardFixed = new BuyCardModular(100, fixedReduction);
        System.out.println("Final price with fixed reduction: " + buyCardFixed.calculatePrice());

        ComputeReductionInterface percentageReduction = new PercentageReduction(0.1);
        BuyCardModular buyCardPercentage = new BuyCardModular(100, percentageReduction);
        System.out.println("Final price with percentage reduction: " + buyCardPercentage.calculatePrice());

    }
}
