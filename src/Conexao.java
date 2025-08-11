// compile command
// javac -d bin -cp lib/mysql-connector-j-9.4.0.jar src/Main.java
// run command
// java -cp "bin;lib/mysql-connector-j-9.4.0.jar" Main

import java.sql.*;

public class Conexao {
    @SuppressWarnings("ConvertToTryWithResources")
    public static Connection getConnection(String connectionUrl, String user, String pass) throws SQLException {
        return DriverManager.getConnection(connectionUrl, user, pass);
    }
    public static void dropConnection(Connection pointer) throws SQLException {
        if (pointer != null) pointer.close();
    }
}
