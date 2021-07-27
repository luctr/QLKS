package com.codegym.formatter;

import com.codegym.model.Role;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class RoleFormatter implements Formatter<Role> {

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Role object, Locale locale) {
        return null;
    }
}
