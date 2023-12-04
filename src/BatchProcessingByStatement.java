import java.sql.*;
import java.util.Scanner;

public class BatchProcessingByStatement {
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
            Scanner scanner = new Scanner(System.in);

            //batch processing
            while (true) {
//                takin user input
                System.out.println("Enter id");
                int id = scanner.nextInt();
                System.out.println("Enter name");
                String name = scanner.next();
                System.out.println("Enter age");
                int age = scanner.nextInt();
                System.out.println("Enter marks");
                float marks = scanner.nextFloat();

                String query = String.format("insert into students(id,name,age,marks) values (%d,'%s',%d,%f)", id, name, age, marks);
                statement.addBatch(query);
                //taking user choice
                System.out.println("Enter N stop more info");
                String choice = scanner.next();
                if (choice.toUpperCase().equals("N")) break;

            }//while loop end

            //checking for any query is not executed
            int [] arr =statement.executeBatch();
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
