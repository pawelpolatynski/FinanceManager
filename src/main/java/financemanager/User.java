package financemanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();
    private String name;

    public User (String name) {
        this.name = name;
    }
    public static User createNewUser (String name, ArrayList<User> usersList) {
        User newUser = new User(name);
        usersList.add(newUser);
        return newUser;
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

    public static void saveUsers(ArrayList<User> userList) {
        File file = new File("./userData.txt");
        try {
            FileWriter writer = new FileWriter(file); // overwrites the file
            for (User u: userList) {
                writer.write(u.getName());
                writer.write("\n");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("IOException occured while saving users!");
        }
    }

    public static void loadUsers(ArrayList<User> usersList) {
        File file = new File("./userData.txt");
        if (file.exists()) {
            try {
                Scanner reader = new Scanner(file); // overwrites the file
                while (reader.hasNext()) {
                    createNewUser(reader.nextLine(), usersList);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("IOException occured while loading users!");
            }
        } else {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write("Unknown\n");
                writer.close();
            }
            catch (IOException e) {
                System.out.println("IOException occured while saving types!");
            }
            loadUsers(usersList);

        }
    }

    public static User findUserById(long id, ArrayList<User> usersList) {
        for (User u : usersList) {
            if (id == u.getId()) {
                return u;
            }
        }
        System.out.println("ERROR! Can't find the user");
        if (usersList.isEmpty()) {
            return new User("Unknown");
        } else {
            return findUserById(0, usersList);
        }
    }
}
