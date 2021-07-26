package com.codegym.formater;

import com.codegym.model.User;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class UserFormatter implements Formatter<User> {
    //UserService

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(User object, Locale locale) {
        return null;
    }
}
