package com.application.main;

import com.application.models.User;


public class UserManager {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User aUser) {
        user = aUser;
    }
}
