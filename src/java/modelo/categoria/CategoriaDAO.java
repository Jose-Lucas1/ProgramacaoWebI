package modelo.categoria;

import java.sql.*;
import static config.Constantes.JDBC_DRIVER;
import static config.Constantes.JDBC_SENHA;
import static config.Constantes.JDBC_URL;
import static config.Constantes.JDBC_USUARIO;

public class CategoriaDAO {

    public Categoria obterPorId(int id) {
        Categoria categoria = null;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categoria WHERE id = ?")) {

                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        categoria = new Categoria();
                        categoria.setId(resultSet.getInt("id"));
                        categoria.setDescricao(resultSet.getString("descricao"));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return categoria;
    }

    public boolean inserirCategoria(String descricao) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria (descricao) VALUES (?)")) {

                preparedStatement.setString(1, descricao);
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    public boolean atualizarCategoria(Categoria categoria) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria SET descricao = ? WHERE id = ?")) {

                preparedStatement.setString(1, categoria.getDescricao());
                preparedStatement.setInt(2, categoria.getId());
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return sucesso;
    }

    public boolean deletarCategoria(int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categoria WHERE id = ?")) {

                preparedStatement.setInt(1, id);
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return sucesso;
    }
}