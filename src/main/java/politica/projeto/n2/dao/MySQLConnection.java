package politica.projeto.n2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    String url = "jdbc:mysql://localhost:3306/politicasprojeto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String usuario ="root";
    String psw = "";

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, psw);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
