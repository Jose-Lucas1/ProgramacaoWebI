package modelo.usuario;

import java.sql.*;
import static config.Constantes.JDBC_DRIVER;
import static config.Constantes.JDBC_SENHA;
import static config.Constantes.JDBC_URL;
import static config.Constantes.JDBC_USUARIO;
import utils.Utils;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o padrão DAO para a entidade Usuario
 */
public class UsuarioDAO {

    /**
     * Método utilizado para obter um usuário pelo login
     *
     * @param login
     * @return
     */
    public Usuario obterPorLogin(String login) {
        Usuario usuario = null;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuario WHERE login = ?")) {
                preparedStatement.setString(1, login);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        usuario = new Usuario();
                        usuario.setId(resultSet.getInt("id"));
                        usuario.setNome(resultSet.getString("nome"));
                        usuario.setEndereco(resultSet.getString("endereco"));
                        usuario.setEmail(resultSet.getString("email"));
                        usuario.setLogin(resultSet.getString("login"));
                        usuario.setSenha(resultSet.getString("senha"));
                        usuario.setAdministrador(resultSet.getBoolean(("administrador")));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            usuario = null;
        }
        return usuario;
    }
    
    public Usuario obterPorId(int id) {
    Usuario usuario = null;
    try {
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuario WHERE id = ?")) {
             
            preparedStatement.setInt(1, id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario();
                    usuario.setId(resultSet.getInt("id"));
                    usuario.setNome(resultSet.getString("nome"));
                    usuario.setEndereco(resultSet.getString("endereco"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setLogin(resultSet.getString("login"));
                    usuario.setSenha(resultSet.getString("senha"));
                    usuario.setAdministrador(resultSet.getBoolean("administrador"));
                }
            }
        }
    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
        usuario = null;
    }
    return usuario;
    }

    
    /**
     * Método utilizado para identificar um usuário pelo login e senha
     *
     * @param login
     * @param senha
     * @return
     */
    public boolean identificar(String login, String senha) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?")) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, Utils.gerarMD5(senha));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        sucesso = true;
                    }
                }
            }
        } catch (Exception ex) {
            sucesso = false;
        }
        return sucesso;
    }

    /**
     * Método utilizado para inserir um novo cliente
     *
     * @param nome
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @return
     */
    public boolean inserirCliente(String nome, String endereco, String email, String login, String senha) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuario (nome, endereco, email, login, senha, administrador) VALUES (?, ?, ?, ?, ?, false)")) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, endereco);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, login);
                preparedStatement.setString(5, Utils.gerarMD5(senha));
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (Exception ex) {
            sucesso = false;
            ex.printStackTrace();
        }
        return sucesso;
    }
    
    public boolean atualizarCliente(Usuario usuario, String nome, String endereco, String email, String login, String senha) throws Exception {
        boolean sucesso = false;
        int idUsuario = usuario.getId();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuario SET nome=?, endereco=?, login=?, senha=?, email=?, administrador=false WHERE id = ?")) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, endereco);
                preparedStatement.setString(3, login);
                preparedStatement.setString(4, Utils.gerarMD5(senha));
                preparedStatement.setString(5, email);
                preparedStatement.setInt(6, idUsuario);
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            sucesso = false;
            System.out.println(ex);
        }
        return sucesso;
    }
    
    public boolean deletarCliente(Usuario usuario) {
        boolean sucesso = false;
        int idUsuario = usuario.getId();
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usuario WHERE id = ?")) {
                preparedStatement.setInt(1, idUsuario);
                sucesso = (preparedStatement.executeUpdate() == 1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            sucesso = false;
            System.out.println(ex);
        }
        return sucesso;
    }

}
