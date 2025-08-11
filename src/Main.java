// compile command
// javac -d bin -cp lib/mysql-connector-j-9.4.0.jar src/Main.java
// run command
// java -cp "bin;lib/mysql-connector-j-9.4.0.jar" Main

import java.sql.*;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        // Dados de conexão
        String url = "jdbc:mysql://localhost:3309/java";
        String usuario = "root";
        String senha = ""; // Substitua pela sua senha do MySQL
        
        // input
        Scanner scanf = new Scanner(System.in);
        System.out.println("1 - Get all items\n2 - Insert item");
        int option = scanf.nextInt();
        switch (option) {
            case 1:
                try {
                    // Estabelece a conexão com o banco de dados
                    Connection conexao = DriverManager.getConnection(url, usuario, senha);
                    System.out.println("Conexão estabelecida com sucesso!");

                    // Cria e executa uma consulta SQL
                    String sql = "SELECT * FROM produtos";
                    Statement stmt = conexao.createStatement();
                    ResultSet resultado = stmt.executeQuery(sql);

                    // Exibe os produtos no console
                    System.out.println("Lista de Produtos:");
                    while (resultado.next()) {
                        int id = resultado.getInt("id");
                        String nome = resultado.getString("nome");
                        double preco = resultado.getDouble("preco");

                        System.out.println(id + " - " + nome + " - R$ " + preco);
                    }

                    // Fecha a conexão
                    resultado.close();
                    stmt.close();
                    conexao.close();

                } catch (SQLException e) {
                    System.out.println("Erro ao conectar: " + e.getMessage());
                }
                break;

            case 2:
                try {
                    System.out.println("Insira o nome do produto");
                    String nome = scanf.nextLine();
                    System.out.println("Insira o preço do produto");
                    Double preco = scanf.nextDouble();
                    


                    // Estabelece a conexão com o banco de dados
                    Connection conexao = DriverManager.getConnection(url, usuario, senha);
                    System.out.println("Conexão estabelecida com sucesso!");

                    // Cria e executa uma consulta SQL
                    String sqlInsert = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
                    PreparedStatement pstmt = conexao.prepareStatement(sqlInsert);
                    pstmt.setString(1, nome); // nome
                    pstmt.setDouble(2, preco); // preço

                    int rowsAffected = pstmt.executeUpdate(); // runs the insert
                    if (rowsAffected > 0) {
                        System.out.println("Produto inserido com sucesso!");
                    }

                    // Fecha a conexão
                    pstmt.close();
                    conexao.close();

                } catch (SQLException e) {
                    System.out.println("Erro ao conectar: " + e.getMessage());
                }
                break;

            default:
                throw new AssertionError();
        }
    }
}
