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
    public static Expense createNewExpense(User user, String name, BigDecimal value, Type type, LocalDate date, ArrayList<Expense> expensesList) {
        Expense exp = new Expense(user, name, value, type, date);
        expensesList.add(exp);
        return exp;
    }
    public static void saveExpenses(ArrayList<Expense> expensesList) {
        File file = new File("./expensesData.txt");
        try {
            FileWriter writer = new FileWriter(file);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            for (Expense e: expensesList) {
                writer.write(e.getUser().getId() + ";" +
                                e.getName() + ";" +
                                e.getValue() + ";" +
                                e.getType().getId() + ";" +
                                formatter.format(e.getDate()));
                writer.write("\n");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("IOException occured while savin expenses!");
        }
    }

    public static void loadExpenses(ArrayList<Expense> expensesList, ArrayList<User> usersList, ArrayList<Type> typesList) throws ParseException {
        File file = new File("./expensesData.txt");
        if (file.exists()) {
            try {
                Scanner reader = new Scanner(file);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
                while (reader.hasNext()) {
                    String text = reader.nextLine();
                    String[] line = text.split(";");
                    createNewExpense(User.findUserById(Long.parseLong(line[0]), usersList),
                            line[1],
                            new BigDecimal(line[2]),
                            Type.findTypeById(Long.parseLong(line[3]), typesList),
                            LocalDate.parse(line[4], formatter),
                            expensesList);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("IOException occured while loading users!");
            }
        }
    }
}
