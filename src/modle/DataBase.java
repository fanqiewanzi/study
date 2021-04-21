package modle;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
private final String USER_NAME="root";
private final String PASSWORD="123456";
private final String URL="jdbc:mysql://localhost:3306/bookmanage";

public void init(){
    try {getConnection();
        System.out.println("连接成功");
    }catch (Exception e){
        System.out.println("连接失败");
    }

}

    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        return connection;
    }

    public void closeConnection(Connection connection) throws Exception{
        if(connection!=null){
            connection.close();
        }
    }
}
