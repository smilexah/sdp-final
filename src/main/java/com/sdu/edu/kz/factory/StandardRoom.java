package com.sdu.edu.kz.factory;

public class StandardRoom implements Room {
    @Override
    public String getDescription() {
    return "Standard room";
}

    @Override
    public double getCost() {
        return 20000;
    }
}
