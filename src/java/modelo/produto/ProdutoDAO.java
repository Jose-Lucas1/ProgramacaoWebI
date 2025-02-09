package modelo.produto;

import config.Constantes;
import static config.Constantes.JDBC_DRIVER;
import static config.Constantes.JDBC_SENHA;
import static config.Constantes.JDBC_URL;
import static config.Constantes.JDBC_USUARIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import modelo.categoria.Categoria;
import org.apache.tomcat.util.http.fileupload.FileItem;

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
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); 
                 Statement statement = connection.createStatement(); 
                 ResultSet resultSet = statement.executeQuery(
                    "SELECT p.id, p.descricao, p.preco, p.quantidade, p.foto, p.categoria_id, c.descricao AS categoria_descricao " +
                    "FROM produto p " +
                    "JOIN categoria c ON p.categoria_id = c.id " +
                    "ORDER BY p.id")) {

                while (resultSet.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getDouble("preco"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    produto.setFoto(resultSet.getString("foto"));

                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("categoria_id"));
                    categoria.setDescricao(resultSet.getString("categoria_descricao"));
                    produto.setCategoria(categoria);

                    resultado.add(produto);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); 
                 Statement statement = connection.createStatement(); 
                 ResultSet resultSet = statement.executeQuery(
                    "SELECT p.id, p.descricao, p.preco, p.quantidade, p.foto, p.categoria_id, c.descricao AS categoria_descricao " +
                    "FROM produto p " +
                    "JOIN categoria c ON p.categoria_id = c.id " +
                    "WHERE p.quantidade > 0 " +
                    "ORDER BY p.id")) {

                while (resultSet.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getDouble("preco"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    produto.setFoto(resultSet.getString("foto"));

                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("categoria_id"));
                    categoria.setDescricao(resultSet.getString("categoria_descricao"));
                    produto.setCategoria(categoria); 

                    resultado.add(produto);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }


    /**
     * Método utilizado para obter todos os produtos que estão faltando em
     * estoque
     *
     * @return
     */
    public List<Produto> obterProdutosFaltantesEmEstoque() {
        List<Produto> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); 
                 Statement statement = connection.createStatement(); 
                 ResultSet resultSet = statement.executeQuery(
                    "SELECT p.id, p.descricao, p.preco, p.quantidade, p.foto, p.categoria_id, c.descricao AS categoria_descricao " +
                    "FROM produto p " +
                    "JOIN categoria c ON p.categoria_id = c.id " +
                    "WHERE p.quantidade <= 0 ORDER BY p.id")) {

                while (resultSet.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getDouble("preco"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    produto.setFoto(resultSet.getString("foto"));

                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("categoria_id"));
                    categoria.setDescricao(resultSet.getString("categoria_descricao"));
                    produto.setCategoria(categoria);

                    resultado.add(produto);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); 
                 PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT p.id, p.descricao, p.preco, p.quantidade, p.foto, p.categoria_id, c.descricao AS categoria_descricao " +
                    "FROM produto p " +
                    "JOIN categoria c ON p.categoria_id = c.id " +
                    "WHERE p.id = ?")) {

                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        produto = new Produto();
                        produto.setId(resultSet.getInt("id"));
                        produto.setDescricao(resultSet.getString("descricao"));
                        produto.setPreco(resultSet.getDouble("preco"));
                        produto.setQuantidade(resultSet.getInt("quantidade"));
                        produto.setFoto(resultSet.getString("foto"));

                        Categoria categoria = new Categoria();
                        categoria.setId(resultSet.getInt("categoria_id"));
                        categoria.setDescricao(resultSet.getString("categoria_descricao"));
                        produto.setCategoria(categoria);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
    public boolean inserir(String descricao, double preco, int quantidade, FileItem foto, int categoriaId) {
        boolean sucesso = false;
        int generatedId = -1;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA)) {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO produto (descricao, preco, quantidade, foto, categoria_id) VALUES (?, ?, ?, ?, ?)", 
                        Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, descricao);
                    preparedStatement.setDouble(2, preco);
                    preparedStatement.setInt(3, quantidade);
                    preparedStatement.setNull(4, Types.VARCHAR);
                    preparedStatement.setInt(5, categoriaId);
                    sucesso = (preparedStatement.executeUpdate() == 1);

                    try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = resultSet.getInt(1);
                        }
                    }
                }

                if (!sucesso) {
                    connection.rollback();
                } else {
                    if (!foto.getName().equals("")) {
                        String caminhoFoto = null;
                        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET foto = ? WHERE id = ?")) {
                            caminhoFoto = Constantes.UPLOAD_FOTO_PRODUTO_DIRETORIO + File.separator + generatedId + foto.getName().substring(foto.getName().lastIndexOf("."));
                            preparedStatement.setString(1, caminhoFoto);
                            preparedStatement.setInt(2, generatedId);
                            sucesso = (preparedStatement.executeUpdate() == 1);
                        }
                        if (sucesso) {
                            try {
                                foto.write(new File(caminhoFoto));
                                connection.commit();
                            } catch (Exception ex) {
                                sucesso = false;
                            }
                        }
                    } else {
                        connection.commit();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
    public boolean atualizar(String descricao, double preco, int quantidade, FileItem foto, int id, int categoriaId) {
        boolean sucesso = false;
        Produto produto = obter(id);
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA)) {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE produto SET descricao = ?, preco = ?, quantidade = ?, foto = ?, categoria_id = ? WHERE id = ?")) {
                    preparedStatement.setString(1, descricao);
                    preparedStatement.setDouble(2, preco);
                    preparedStatement.setInt(3, quantidade);
                    String caminhoFoto = null;
                    if (!foto.getName().equals("")) {
                        caminhoFoto = Constantes.UPLOAD_FOTO_PRODUTO_DIRETORIO + File.separator + id + foto.getName().substring(foto.getName().lastIndexOf("."));
                        preparedStatement.setString(4, caminhoFoto);
                        try {
                            if (new File(produto.getFoto()).exists()) {
                                new File(produto.getFoto()).delete();
                            }
                            foto.write(new File(caminhoFoto));
                        } catch (Exception ex) {
                            sucesso = false;
                        }
                    } else {
                        if (produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
                            preparedStatement.setString(4, produto.getFoto());
                        } else {
                            preparedStatement.setNull(4, Types.VARCHAR);
                            if (new File(produto.getFoto()).exists()) {
                                new File(produto.getFoto()).delete();
                            }
                        }
                    }
                    preparedStatement.setInt(5, categoriaId); 
                    preparedStatement.setInt(6, id);          
                    sucesso = (preparedStatement.executeUpdate() == 1);
                    if (sucesso) {
                        connection.commit();
                    } else {
                        connection.rollback();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
        Produto produto = obter(id);
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                sucesso = (preparedStatement.executeUpdate() == 1);
                if (sucesso && produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
                    if (new File(produto.getFoto()).exists()) {
                        new File(produto.getFoto()).delete();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }
        return sucesso;
    }

}
