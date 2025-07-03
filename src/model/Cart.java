package model;

import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (quantity > product.getQuantity()) throw new IllegalArgumentException("Not enough stock for " + product.getName());
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                int newQty = item.getQuantity() + quantity;
                if (newQty > product.getQuantity()) throw new IllegalArgumentException("Not enough stock for " + product.getName());
                items.remove(item);
                items.add(new CartItem(product, newQty));
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CartItem item : items) {
            sb.append(item.getQuantity()).append("x ").append(item.getProduct().getName()).append("\n");
        }
        return sb.toString();
    }
} 