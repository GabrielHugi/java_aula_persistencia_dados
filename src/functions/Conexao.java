package functions;
// compile command
// javac -d bin -cp lib/mysql-connector-j-9.4.0.jar src/Main.java
// run command
// java -cp "bin;lib/mysql-connector-j-9.4.0.jar" Main

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import globalvars.*;

public class Conexao {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(global.url, global.usuario, global.senha);
    }
    
    public static void dropConnection(Connection pointer) throws SQLException {
        if (pointer != null) pointer.close();
    }
}
