package com.codegym.formater;

import com.codegym.model.Room;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoomFormatter implements Formatter<Room> {
    @Override
    public Room parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Room object, Locale locale) {
        return null;
    }
}
