package com.sdu.edu.kz.booking;

import com.sdu.edu.kz.adapter.BookingPayment;
import com.sdu.edu.kz.adapter.PaymentSystem;
import com.sdu.edu.kz.decorator.BookingInterface;
import com.sdu.edu.kz.factory.Room;
import com.sdu.edu.kz.observer.Observer;
import com.sdu.edu.kz.strategy.PricingStrategy;

public class Booking implements Observer, BookingPayment, BookingInterface {
    private static int nextId=1;
    private int id;
    private String client;
    private Room room;
    private PricingStrategy strategy;
    private PaymentSystem paymentSystem;
    private int days;
    public Booking(Room room, PricingStrategy strategy , String client , PaymentSystem paymentSystem,int days) {
        this.paymentSystem=paymentSystem;
        this.client=client;
        this.room = room;
        this.strategy = strategy;
        this.id = nextId;
        nextId++;
        this.days=days;
    }

    @Override
    public double getPrice(){
        return strategy.calculatePrice(room.getCost()*days);
    }
    @Override
    public String getDescription(){
        return room.getDescription();
    }

    @Override
    public void update(String msg) {
        System.out.println("Room type:"+this.room.getDescription());
        System.out.println("Client name:"+this.client);
        System.out.println(msg);
    }


    @Override
    public void makePayment(double amount) {
        paymentSystem.processPayment(amount);
    }

    public void check(){
        makePayment(getPrice());
    }

    public int getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public String getClient() {
        return client;
    }
}
