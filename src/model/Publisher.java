package model;

import exception.ValidationException;

import java.io.Serializable;

public class Publisher implements Serializable {
    private String name, country, shortcut;

    public Publisher(String name, String country, String shortcut) {
        setName(name);
        setShortcut(shortcut);
        setCountry(country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || "".equals(name.trim()))
            throw new ValidationException("name cannot be empty");
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || "".equals(country.trim()))
            throw new ValidationException("country cannot be empty");
        this.country = country;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        if (shortcut == null || "".equals(shortcut.trim()))
            throw new ValidationException("shortcut cannot be empty");
        this.shortcut = shortcut;
    }
}
