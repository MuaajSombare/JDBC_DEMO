import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BactchProcessingByPreparedStatement {

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
            String query = "insert into students(id,name,age,marks) values (?,?,?,?)";
            java.sql.PreparedStatement pre = connection.prepareStatement(query);

            Scanner scanner = new Scanner(System.in);

            //batch processing
            while (true) {
                //takin user input
                System.out.println("Enter id");
                int id = scanner.nextInt();
                System.out.println("Enter name");
                String name = scanner.next();
                System.out.println("Enter age");
                int age = scanner.nextInt();
                System.out.println("Enter marks");
                float marks = scanner.nextFloat();

                pre.setInt(1,id);
                pre.setString(2,name);
                pre.setInt(3,age);
                pre.setFloat(4,marks);
                pre.addBatch();
                //taking user choice
                System.out.println("Enter N stop more info");
                String choice = scanner.next();
                if (choice.toUpperCase().equals("N")) break;

            }//while loop end

            //checking for any query is not executed
            int [] arr =pre.executeBatch();
            int length = arr.length;
            for(int i=0;i<=length-1;i++){
                if(arr[i]==0) System.out.println("query at index"+i+"is not executed");
                else System.out.println("all executed");
            }

        }//main try block

        catch (SQLException e){
            e.printStackTrace();
        }

    }

}
