package functions;
import java.sql.*;
import java.util.*;

import models.*;

public class ProdutoDAO {
    @SuppressWarnings("CallToPrintStackTrace")
    public void inserir(Produto p) {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?);";
        try (Connection com = Conexao.getConnection()) {
            PreparedStatement stat = com.prepareStatement(sql);
            stat.setString(1, p.getNome());
            stat.setDouble(2, p.getPreco());
            stat.executeUpdate();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos;";
        try (Connection com = Conexao.getConnection()) {
            Statement stmt = com.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Produto p = new Produto(rs.getString("nome"), rs.getDouble("preco"));
                p.setId(rs.getInt("id"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void atualizar(Produto p) {
        String sql = "UPDATE produtos SET nome=?, preco=? WHERE id=?;";
        try (Connection com = Conexao.getConnection()) {
            PreparedStatement stat = com.prepareStatement(sql);
            stat.setString(1, p.getNome());
            stat.setDouble(2, p.getPreco());
            stat.setInt(3, p.getId());
            stat.executeUpdate();
            System.out.println("Produto atualizado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id=?;";
        try (Connection com = Conexao.getConnection()) {
            PreparedStatement stat = com.prepareStatement(sql);
            stat.setInt(1, id);
            stat.executeUpdate();
            System.out.println("Produto deletado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
