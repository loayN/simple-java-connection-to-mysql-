package LoayNaser;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println( "welcome to the first assignment solution " +
                "\nplease make sure you read the instruction file before" );
        System.out.println( "connecting to Server..." );
        String ServerIP = GetConfig.getConfig( "IPAddress" );
        String UserName = GetConfig.getConfig( "username" );
        String Password = GetConfig.getConfig( "password" );

        ServerCon sc = new ServerCon(ServerIP, UserName, Password);
        Connection myConnection = sc.Connect();

        System.out.println( "server ip: " + ServerIP +"\nuser name: "+ UserName +"\npassword: "+ Password );
        System.out.println( "you are connected now..." );
        System.out.println( "\nQuestion 1 \n" );
        String command1 = "select * from product_tbl inner join category_tbl Where category_id = product_id ORDER BY product_name";
        QueryExc qe1 = new QueryExc( myConnection, command1 );
        qe1.Execute();
        System.out.println( " \nQuestion 2\n" +
                "insert X => stock > X " );
        Scanner scanner = new Scanner( System.in );
        int x = scanner.nextInt();
        System.out.println( "insert Y => stock < Y" );
       int y = scanner.nextInt();
        String command2 ="select product_name , category_name " +
                "from product_tbl inner join category_tbl " +
                "where category_id = product_id AND " +
                "count >"+x+" AND count<"+y;
        QueryExc qe2=new QueryExc( myConnection,command2);
        qe2.Execute();
        System.out.println( "\nDo you want to try another query or to close the connection ? \n type [yes/no]" );
        String answer = scanner.next();

        while (true) {
            if (answer.equals( "yes" )) {
                System.out.println( "\n you can insert any query to excute:" );

                Scanner s = new Scanner( new InputStreamReader( System.in, Charset.defaultCharset() ) );
                String insertedQuery = s.nextLine();
                //System.out.println( insertedQuery );
                QueryExc qe3 = new QueryExc( myConnection, insertedQuery );
                qe3.Execute();
                System.out.println( "want to try another one ? [yes/no]" );
                answer = scanner.next();
            } else {
                try {
                    myConnection.close();
                    System.out.println( "connection closed" );
                    break;
                } catch (SQLException e) {
                    System.out.println( e.getMessage() );
                    //e.printStackTrace();
                }
            }
        }
    }
}