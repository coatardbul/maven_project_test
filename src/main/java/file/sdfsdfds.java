package file;

import java.sql.DriverManager;
import java.sql.SQLException;

public class sdfsdfds {
    public static void main(String[] args) {


           // Class.forName("sdfsdfs");
            try {
                DriverManager.getConnection("qweqw", "qweqw",
                        "qweqw");
            } catch (SQLException e) {
                System.out.print(e.getMessage());
            }

    }
}
