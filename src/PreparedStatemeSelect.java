//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;
public class PreparedStatemeSelect {
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
            //making database connection
            Connection connection = DriverManager.getConnection(url,username,password);
            //preparing for sql query
//            Statement statement =connection.createStatement();
            String query = "select marks from students where id = ?";
            java.sql.PreparedStatement pre = connection.prepareStatement(query);
            pre.setInt(1,1);
            ResultSet resultSet = pre.executeQuery();
            if(resultSet.next()){
                double marks = resultSet.getDouble("marks");
                System.out.println("marks is "+ marks);
            }
            else {
                System.out.println("some error");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
