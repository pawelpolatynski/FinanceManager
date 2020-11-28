package financemanager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        Repository repository = new Repository("jdbc:h2:~/test");
        repository.getUsers();
        repository.close();


    }
}
