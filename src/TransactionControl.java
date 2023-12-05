import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionControl {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String username = "root";
    private static final String password = "1234";
    public static void main(String[] args) {
        //loading necessary drivers
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
            String debit_query = "update account set balance = balance - ? where account_number = ?";
            String credit_query = "update account set balance = balance + ? where account_number = ?";
            java.sql.PreparedStatement dpre = connection.prepareStatement(debit_query);
            java.sql.PreparedStatement cpre = connection.prepareStatement(credit_query);
            Scanner sc = new Scanner(System.in);
            System.out.println("enter account number");
            int account_number = sc.nextInt();
            System.out.println("enter amount");
            double amount = sc.nextDouble();
            dpre.setDouble(1,amount);
            dpre.setInt(2,101);
            cpre.setDouble(1,amount);
            cpre.setInt(2,102);
            dpre.executeUpdate();
            cpre.executeUpdate();
            if(isSufficient(connection,account_number,amount)){
                connection.commit();
                System.out.println("transaction successful");
            }
            else {
                connection.rollback();
                System.out.println("transaction failed");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    static boolean isSufficient(Connection connection, int account_number, double amout){
        try {
            String query = "select balance from account where account_number = ?";
            java.sql.PreparedStatement pre = connection.prepareStatement(query);
            pre.setInt(1,account_number);
            ResultSet resultSet = pre.executeQuery();
            if(resultSet.next()){
                double current_balance = resultSet.getDouble("balance");
                if(amout > current_balance)return false;
                else return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
