package service;

import model.*;
import java.util.*;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.err.println("Error: Cart is empty");
            return;
        }
        double subtotal = 0;
        double shipping = 0;
        List<Shippable> toShip = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int qty = item.getQuantity();
            if (product.isExpired()) {
                System.err.printf("Error: %s is expired\n", product.getName());
                return;
            }
            if (qty > product.getQuantity()) {
                System.err.printf("Error: Not enough stock for %s\n", product.getName());
                return;
            }
            subtotal += product.getPrice() * qty;
            if (product.isShippable() && product instanceof Shippable) {
                for (int i = 0; i < qty; i++) {
                    toShip.add((Shippable) product);
                }
                shipping += 30 * qty;
            }
        }
        double total = subtotal + shipping;
        if (customer.getBalance() < total) {
            System.err.println("Error: Insufficient balance");
            return;
        }
       
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }
        customer.setBalance(customer.getBalance() - total);
        
        ShippingService.ship(toShip);
        
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s = %.0f EGP\n", item.getQuantity(), item.getProduct().getName(), item.getProduct().getPrice() * item.getQuantity());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shipping);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Customer balance after payment: %.0f\n", customer.getBalance());
        System.out.println("END.");
    }
} 