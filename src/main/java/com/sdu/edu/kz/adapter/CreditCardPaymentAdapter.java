package com.sdu.edu.kz.adapter;

public class CreditCardPaymentAdapter implements BookingPayment {
    private CreditCardPayment creditCardPayment;

    public CreditCardPaymentAdapter(CreditCardPayment payment) {
        this.creditCardPayment = payment;
    }

    @Override
    public void makePayment(double amount) {
        creditCardPayment.processPayment(amount);
    }
}
