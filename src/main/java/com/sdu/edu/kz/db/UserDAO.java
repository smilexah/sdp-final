package com.sdu.edu.kz.db;

public interface UserDAO {
    int insertUser(User user);
    User getUser(String userName, String password);
}
