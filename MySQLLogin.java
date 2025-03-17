import java.sql.*;
import java.util.Scanner;

public class MySQLLogin {
  public static void main(String[] args) {
    
  // Користувач вводить дані для підключення до MySQL
  Scanner scanner = new Scanner(System.in);
  
    // MySQL server connection details
  System.out.print("Enter MySQL server IP address: ");
  String ipAddress = scanner.nextLine();
  System.out.print("Enter database name: ");
  String dbName = scanner.nextLine();
  System.out.print("Enter MySQL username: ");
  String mysqlUsername = scanner.nextLine();
  System.out.print("Enter MySQL password: ");
  String mysqlPassword = scanner.nextLine();
  
  // Зʼєднання з MySQL
  try {
    // Підключаємо MySQL JDBC Driver
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Формуємо URL для підключення
    String url = "jdbc:mysql://" + ipAddress + ":3306/" + dbName;
    
    // Підключаємося до MySQL на основі введених креденшалів
    Connection conn = DriverManager.getConnection(url, mysqlUsername,mysqlPassword);
          
    // Дозволяємо користувачу ввести з клавіатури логін і пароль
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    // Формуємо запит (квері)
    String query = "SELECT * FROM users WHERE username = ? AND password = ?";
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1, username);
    stmt.setString(2, password);
    // Виконуємо запит
    ResultSet rs = stmt.executeQuery();
          
    // Перевіряємо чи є результат співпадіння з запитом (квері)
    if (rs.next()) {
        System.out.println("ACCESS GRANTED!");
    } else {
        System.out.println("ACCESS DENIED!");
    }
    // Закриваємо зʼєднання
    rs.close();
    stmt.close();
    conn.close();
    
  } catch (ClassNotFoundException e) {
    System.out.println("MySQL JDBC Driver not found.");
  } catch (SQLException e) {
    System.out.println("Error connecting to the database.");
    e.printStackTrace();
  }
  }
}
