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
    public static Type createNewType (String name, ArrayList<Type> typesList) {
        Type newType = new Type(name);
        typesList.add(newType);
        return newType;
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

    public static void saveTypes(ArrayList<Type> typesList) {
        File file = new File("./typesData.txt");
        try {
            FileWriter writer = new FileWriter(file); // overwrites the file
            for (Type t: typesList) {
                writer.write(t.getName());
                writer.write("\n");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("IOException occured while saving types!");
        }
    }

    public static void loadTypes(ArrayList<Type> typesList) {
        File file = new File("./typesData.txt");
        if (file.exists()) {

            try {
                Scanner reader = new Scanner(file); // overwrites the file
                while (reader.hasNext()) {
                    createNewType(reader.nextLine(), typesList);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("IOException occured while loading types!");
            }
        } else {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write("Food\nBills\nGas\nNonessentials\n");
                writer.close();
            }
            catch (IOException e) {
                System.out.println("IOException occured while saving types!");
            }
            loadTypes(typesList);

        }
    }

    public static Type findTypeById(long id, ArrayList<Type> typesList) {
        for (Type t : typesList) {
            if (id == t.getId()) {
                return t;
            }
        }
        System.out.println("ERROR! Can't find the user");
        return new Type("Unknown");
    }
}
