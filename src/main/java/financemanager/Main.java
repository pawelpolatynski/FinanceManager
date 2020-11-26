package financemanager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ParseException, Exception {
        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.
                getConnection("jdbc:h2:~/test", "sa", "");
        System.out.println("CHyba działa");
        conn.close();
        ArrayList<User> usersList = new ArrayList();
        ArrayList<Type> typesList = new ArrayList();
        ArrayList<Expense> expensesList = new ArrayList();
/*
        User u1 = User.createNewUser("Pawel", usersList);
        User u2 = User.createNewUser("Gawel", usersList);

        Type t1 = Type.createNewType("Jedzenie", typesList);
        Type t2 = Type.createNewType("Rachunki", typesList);

        Expense e1 = Expense.createNewExpense(u1, "Zakupy Lidl", 67.45, t1, LocalDate.of(2015, 5, 30), expensesList);
        Expense e2 = Expense.createNewExpense(u1, "Prad", 120.99, t2, LocalDate.of(2015, 5, 28), expensesList);
        Expense e3 = Expense.createNewExpense(u2, "Piwo", 24.01, t1, LocalDate.of(2015, 5, 27), expensesList);
        Expense e4 = Expense.createNewExpense(u2, "Gaz", 30.00, t2, LocalDate.of(2015, 5, 26), expensesList);

        User.saveUsers(usersList);
        Type.saveTypes(typesList);
        Expense.saveExpenses(expensesList);*/
        User.loadUsers(usersList);
        Type.loadTypes(typesList);
        Expense.loadExpenses(expensesList, usersList, typesList);
        for (Expense e : expensesList) {
            System.out.println(e.getName());
        }
        Type t1 = Type.createNewType("Gierki", typesList);
        for (Type t : typesList) {
            System.out.println(t.getId() + " " + t.getName());
        }
        User u1 = User.createNewUser("Pawel", usersList);
        Expense.createNewExpense(u1, "Zakupy Lidl", new BigDecimal("67.45"), t1, LocalDate.of(2015, 5, 30), expensesList);


    }
}
