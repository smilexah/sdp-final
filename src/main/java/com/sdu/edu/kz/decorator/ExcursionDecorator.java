package com.sdu.edu.kz.decorator;


import com.sdu.edu.kz.factory.Room;

public class ExcursionDecorator extends BookingDecorator {
    public ExcursionDecorator(Room room) {
        super(room);
    }

    @Override
    public String getDescription() {
        return room.getDescription() + ", with Excursion";
    }

    @Override
    public double getCost() {
        return room.getCost() + 10000.0;
    }
}
