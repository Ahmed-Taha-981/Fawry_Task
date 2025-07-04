package model;

public abstract class ExpirableProduct extends Product {
    protected boolean isExpired;

    public ExpirableProduct(String name, double price, int quantity, boolean isExpired) {
        super(name, price, quantity);
        this.isExpired = isExpired;
    }

    @Override
    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        this.isExpired = expired;
    }
} 