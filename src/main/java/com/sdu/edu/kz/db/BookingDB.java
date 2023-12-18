package com.sdu.edu.kz.db;

import java.sql.Date;

public class BookingDB {
    private int id;
    private User user;
    private double price;
    private String description;
    private Date registrationDate;


    public BookingDB(int id, User user, double price, String description,Date registrationDate){
        this.id = id;
        this.user = user;
        this.price = price;
        this.description = description;
        this.registrationDate=registrationDate;
    }
}
