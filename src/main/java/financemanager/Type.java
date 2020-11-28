package financemanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Type {
    private String name;
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();

    public Type (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public void changeName(String newName) {
        this.name = newName;

    }

}
