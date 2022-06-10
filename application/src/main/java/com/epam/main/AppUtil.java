package com.epam.main;

import com.epam.dto.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class AppUtil {

    public static List<User> generateUsers() {
        var users = new ArrayList<User>();
        users.add(new User("John", "Doe", LocalDate.of(1999, Month.JULY, 4)));
        users.add(new User("Brad", "Pitt", LocalDate.of(1968, Month.OCTOBER, 14)));
        users.add(new User("Leo", "Messi", LocalDate.of(2010, Month.APRIL, 1)));
        users.add(new User("Michael", "Jordan", LocalDate.of(2005, Month.DECEMBER, 22)));
        return users;
    }
}
