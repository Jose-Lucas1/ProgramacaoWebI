package modelo.produto;

import static config.Constantes.JDBC_DRIVER;
import static config.Constantes.JDBC_SENHA;
import static config.Constantes.JDBC_URL;
import static config.Constantes.JDBC_USUARIO;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o padrão DAO para a entidade Produto
 */
public class ProdutoDAO {

    /**
     * Método utilizado para obter todos os produtos
     *
     * @return
     */
    public List<Produto> obterTodos() {
        List<Produto> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT id, descricao, preco, quantidade, foto FROM produto")) {
                while (resultSet.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getDouble("preco"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    produto.setFoto(resultSet.getString("foto"));
                    resultado.add(produto);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
    
        }
        return resultado;
    }
    
    /**
     * Método utilizado para obter todos os produtos que existem em estoque
     *
     * @return
     */
    public List<Produto> obterProdutosEmEstoque() {
        List<Produto> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT id, descricao, preco, quantidade, foto FROM produto WHERE quantidade > 0")) {
                while (resultSet.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getDouble("preco"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    produto.setFoto(resultSet.getString("foto"));
                    resultado.add(produto);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
    
        }
        return resultado;
    }
    
    /**
     * Método utilizado para obter o produto pelo id
     *
     * @param id
     * @return
     */
    public Produto obter(int id) {
        Produto produto = null;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, quantidade, foto FROM produto WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        produto = new Produto();
                        produto.setId(resultSet.getInt("id"));
                        produto.setDescricao(resultSet.getString("descricao"));
                        produto.setPreco(resultSet.getDouble("preco"));
                        produto.setQuantidade(resultSet.getInt("quantidade"));
                        produto.setFoto(resultSet.getString("foto"));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
    
        }
        return produto;
    }

    /**
     * Método utilizado para inserir um novo produto
     * 
     * @param descricao
     * @param preco
     * @param quantidade
     * @param foto
     * @return 
     */
    public boolean inserir(String descricao, double preco, int quantidade, String foto) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (descricao, preco, quantidade, foto) VALUES (?, ?, ?, ?)")) {
                preparedStatement.setString(1, descricao);
                preparedStatement.setDouble(2, preco);
                preparedStatement.setInt(3, quantidade);
                preparedStatement.setString(4, foto);
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
        return sucesso;
    }
    
    /**
     * Método utilizado para atualizar um produto existente
     * 
     * @param descricao
     * @param preco
     * @param quantidade
     * @param foto
     * @param id
     * @return 
     */
    public boolean atualizar(String descricao, double preco, int quantidade, String foto, int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET descricao = ?, preco = ?, quantidade = ?, foto = ? WHERE id = ?")) {
                preparedStatement.setString(1, descricao);
                preparedStatement.setDouble(2, preco);
                preparedStatement.setInt(3, quantidade);
                preparedStatement.setString(4, foto);
                preparedStatement.setInt(5, id);
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
        return sucesso;
    }
    
    /**
     * Método utilizado para remover um produto existente
     * 
     * @param id
     * @return 
     */
    public boolean remover(int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
        return sucesso;
    }
}
