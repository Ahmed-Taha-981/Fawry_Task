package service;

import model.Shippable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        if (items.isEmpty()) return;
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Double> weights = new HashMap<>();
        
        for (Shippable item : items) {
            String name = item.getName();
            counts.put(name, counts.getOrDefault(name, 0) + 1);
            weights.put(name, item.getWeight());
            totalWeight += item.getWeight();
        }
        
        for (String name : counts.keySet()) {
            double weight = weights.get(name);
            if (weight >= 1.0) {
                System.out.printf("%dx %s %.1fkg\n", counts.get(name), name, weight);
            } else {
                System.out.printf("%dx %s %.0fg\n", counts.get(name), name, weight * 1000);
            }
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
} 