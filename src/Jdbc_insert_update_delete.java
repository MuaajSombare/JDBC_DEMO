import java.sql.*;

public class Jdbc_insert_update_delete {
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

            //inserting data
//            String query = String.format("insert into students(id,name,age,marks) values (%o,'%s',%o,%f)",2,"ram",23,56.5);

            //updating data
//            String query = String.format("update students set marks = %f where id = %d",89.5,2);

            //deleting data
            String query = String.format("delete from students where id = %d",2);
            int affectedRows = statement.executeUpdate(query);

            if(affectedRows>0) System.out.println("data deleted");
            else System.out.println("not deleted");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
