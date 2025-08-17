package functions;
import java.sql.*;
import java.util.*;

import models.*;

public class CategoriaDAO {
    @SuppressWarnings("CallToPrintStackTrace")
    public void inserir(Categoria c) {
        String sql = "INSERT INTO categorias (nome, ativo) VALUES (?, ?);";
        try (Connection com = Conexao.getConnection()) {
            PreparedStatement stat = com.prepareStatement(sql);
            stat.setString(1, c.getNome());
            stat.setBoolean(2, c.getAtivo());
            stat.executeUpdate();
            System.out.println("Categoria inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias;";
        try (Connection com = Conexao.getConnection()) {
            Statement stmt = com.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Categoria c = new Categoria(rs.getString("nome"), rs.getBoolean("ativo"));
                c.setId(rs.getInt("id"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void atualizar(Categoria c) {
        String sql = "UPDATE categorias SET nome=?, ativo=? WHERE id=?;";
        try (Connection com = Conexao.getConnection()) {
            PreparedStatement stat = com.prepareStatement(sql);
            stat.setString(1, c.getNome());
            stat.setBoolean(2, c.getAtivo());
            stat.setInt(3, c.getId());
            stat.executeUpdate();
            System.out.println("Categoria atualizada!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void deletar(int id) {
        String sql = "DELETE FROM categorias WHERE id=?;";
        try (Connection com = Conexao.getConnection()) {
            PreparedStatement stat = com.prepareStatement(sql);
            stat.setInt(1, id);
            stat.executeUpdate();
            System.out.println("Categoria deletada!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
