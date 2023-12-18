package com.sdu.edu.kz.singleton;

import com.sdu.edu.kz.booking.Booking;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private List<Booking> bookings = new ArrayList<>();
    private static HotelManager instance;

    private HotelManager() {
        // Private constructor to prevent external instantiation.
    }

    public static HotelManager getInstance() {
        if (instance == null) {
            instance = new HotelManager();
        }
        return instance;
    }

    public void BookRoom(Booking booking) {
        this.bookings.add(booking);
    }

    public void RemoveBooking(Booking booking) {
        this.bookings.remove(booking);
    }

    public void FireAlarm() {
        notifyRooms("Fire alarm was triggered");
    }


    private void notifyRooms(String msg) {
        for (Booking b : this.bookings) {
            b.update(msg);
        }
    }
}
