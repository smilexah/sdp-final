package com.sdu.edu.kz.adapter;

public class KaspiPaymentAdapter implements BookingPayment {
    private KaspiPayment kaspiPayment;

    public KaspiPaymentAdapter(KaspiPayment payment) {
        this.kaspiPayment = payment;
    }

    @Override
    public void makePayment(double amount) {
        kaspiPayment.processPayment(amount);
    }
}