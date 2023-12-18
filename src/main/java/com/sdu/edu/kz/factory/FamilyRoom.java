package com.sdu.edu.kz.factory;

public class FamilyRoom implements Room {
    @Override
    public String getDescription() {
        return "Family room";
    }

    @Override
    public double getCost() {
        return 35000;
    }
}
