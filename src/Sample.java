import java.sql.*;
public class Sample {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String username = "root";
    private static final String password = "1234";
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");//for loads the drivers

        try {
            Connection connection = DriverManager.getConnection(url,username,password);//link with connection
            Statement statement = connection.createStatement();//used to execute query
            String query = "select * from students";//created query
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ //till the database has next rows
                int id = resultSet.getInt("id");
                String name  = resultSet.getString("name");
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
