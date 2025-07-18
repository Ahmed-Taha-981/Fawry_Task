package model;

public class Biscuits extends ExpirableProduct implements Shippable {
    private double weight;

    public Biscuits(String name, double price, int quantity, boolean isExpired, double weight) {
        super(name, price, quantity, isExpired);
        this.weight = weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
} 