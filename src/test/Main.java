package test;

import model.*;
import service.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Use case 1: normal checkout
        Cheese cheese = new Cheese("Cheese ", 100, 5, LocalDate.now().plusDays(5), 0.4);
        Biscuits biscuits = new Biscuits("Biscuits ", 150, 2, LocalDate.now().plusDays(10), 0.7);
        TV tv = new TV("TV", 200, 3, 5.0);
        MobileScratchCard scratchCard = new MobileScratchCard("Scratch Card", 50, 10);

        
        Customer customer = new Customer("Ahmed", 5000);

        
        Cart cart = new Cart();
        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        CheckoutService.checkout(customer, cart);

        // Use case 2: expired product
        Cheese expiredCheese = new Cheese("Old Cheese", 80, 1, LocalDate.now().minusDays(1), 0.3);
        Cart cart2 = new Cart();
        try {
            cart2.add(expiredCheese, 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        CheckoutService.checkout(customer, cart2);

        // Use case 3: insufficient balance
        Customer poorCustomer = new Customer("Ali", 10);
        Cart cart3 = new Cart();
        try {
            cart3.add(tv, 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        CheckoutService.checkout(poorCustomer, cart3);

        // Use case 4: out of stock
        Cart cart4 = new Cart();
        try {
            cart4.add(biscuits, 10);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        CheckoutService.checkout(customer, cart4);

        // Use case 5: empty cart
        Cart cart5 = new Cart();
        CheckoutService.checkout(customer, cart5);
    }
} 