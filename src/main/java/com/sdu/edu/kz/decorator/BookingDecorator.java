package com.sdu.edu.kz.decorator;

import com.sdu.edu.kz.factory.Room;

abstract class BookingDecorator implements Room {
    protected Room room;

    public BookingDecorator(Room room) {
        this.room = room;
    }

    public String getDescription() {
        return room.getDescription();
    }

    @Override
    public double getCost() {
        return room.getCost();
    }
}
