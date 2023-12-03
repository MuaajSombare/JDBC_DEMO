import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;

//prepared-statement query used to insert data multiple times without writing query multiple
//times, it will comopile query only once

public class PreparedStatement {
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
            String query = "insert into students (id, name, age ,marks) values(?,?,?,?)";
            java.sql.PreparedStatement pre = connection.prepareStatement(query);
            pre.setInt(1,3);
            pre.setString(2,"muaaj");
            pre.setInt(3,34);
            pre.setFloat(4,68.9f);
            int affectedRows = pre.executeUpdate();
            if(affectedRows>0) System.out.println("data inserted");
            else System.out.println("not inserted");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
