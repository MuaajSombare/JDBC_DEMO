import java.sql.*;

public class Jdbc_connection2 {
    //database configurations
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
            Statement statement =connection.createStatement();
            String query = "select * from students";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double marks = resultSet.getDouble("marks");

                System.out.println("id "+ id);
                System.out.println("id "+ name);
                System.out.println("id "+ age);
                System.out.println("id "+ marks);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
