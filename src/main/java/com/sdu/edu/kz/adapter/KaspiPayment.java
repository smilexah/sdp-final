package com.sdu.edu.kz.adapter;
public class KaspiPayment implements PaymentSystem {
    @Override
    public String processPayment(double amount) {
        return "Payment was received from Kaspi in amount: " + amount + " KZT";
    }
}