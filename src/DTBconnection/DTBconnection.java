package DTBconnection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class DTBconnection {
    private  static Connection connect ;
    private static String drive = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost:3306/tarea6";

    public DTBconnection(){
        connectToDTB();
    }

    private void  connectToDTB(){
        connect = null ;
        try{
            Class.forName(drive);
            connect = (Connection) DriverManager.getConnection(url , user ,password);
            if(connect != null){
                System.out.println("Connection to DTB on");
            }
        }catch ( Exception e){
                System.out.println("the error to connect to the DTB is:  " +  e );
        }
    }

    public  Connection getConnection(){
        return  connect ;
    }

    public void connectionOff(){
        connect = null ;
        if(connect == null){
            System.out.println("DTB off");
        }
    }
}
