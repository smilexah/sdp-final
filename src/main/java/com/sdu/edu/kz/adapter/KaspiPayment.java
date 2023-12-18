package com.sdu.edu.kz.adapter;
public class KaspiPayment implements PaymentSystem {
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment was received from Kaspi in amount: " + amount + " KZT");
    }
}