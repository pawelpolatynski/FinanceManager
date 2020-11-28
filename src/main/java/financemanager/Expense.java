package financemanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Expense {
    private User user;
    private BigDecimal price;
    private Type type;
    private String name;
    private LocalDate date;
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();

    public Expense (User user, String name, BigDecimal value, Type type, LocalDate date) {
        this.user = user;
        this.price = value;
        this.type = type;
        this.name = name;
        this.date = date;

    }

    public User getUser() {
        return this.user;
    }

    public long getId() {
        return this.id;
    }

    public BigDecimal getValue() {
        return this.price;
    }

    public Type getType() {
        return this.type;
    }
    public String getName() {
        return this.name;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setUser(User newUser) {
        this.user = newUser;
    }

    public void setValue(BigDecimal newValue) {
        this.price = newValue;
    }

    public void setType(Type newType) {
        this.type = newType;
    }
    public void setName(String newName) {
        this.name = newName;
    }

    public void setDate(LocalDate newDate) {
        this.date = newDate;
    }

}
