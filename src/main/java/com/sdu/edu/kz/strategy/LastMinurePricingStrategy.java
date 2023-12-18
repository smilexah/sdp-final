package com.sdu.edu.kz.strategy;

public class LastMinurePricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 1.3; // 30% +
    }
}
