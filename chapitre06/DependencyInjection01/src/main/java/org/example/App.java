package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        BuyCard buyCard = new BuyCard(100, 0.1);
        System.out.println("Final price: " + buyCard.calculatePrice());

    }
}
