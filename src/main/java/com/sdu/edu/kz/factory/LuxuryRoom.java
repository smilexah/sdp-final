package com.sdu.edu.kz.factory;
public class LuxuryRoom implements Room {
    @Override
    public String getDescription() {
        return "Luxury room";
    }

    @Override
    public double getCost() {
        return 50000;
    }
}