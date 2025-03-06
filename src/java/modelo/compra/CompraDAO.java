package modelo.compra;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static config.Constantes.*;
import modelo.usuario.UsuarioCompras;

public class CompraDAO {

    public boolean adicionarCompra(Compra compra) {
        String sql = "INSERT INTO compra (id_usuario, quantidade_total_de_produtos_comprados, valortotal, data) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, compra.getId_usuario());
            stmt.setInt(2, compra.getQuantidade_total_de_produtos_comprados());
            stmt.setDouble(3, compra.getValortotal());
            stmt.setDate(4, Date.valueOf(compra.getData()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletarCompra(int id) {
        String sql = "DELETE FROM compra WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarCompra(Compra compra) {
        String sql = "UPDATE compra SET id_usuario = ?, quantidade_total_de_produtos_comprados = ?, valortotal = ?, data = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, compra.getId_usuario());
            stmt.setInt(2, compra.getQuantidade_total_de_produtos_comprados());
            stmt.setDouble(3, compra.getValortotal());
            stmt.setDate(4, Date.valueOf(compra.getData()));
            stmt.setInt(5, compra.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Compra getCompra(int id) {
        String sql = "SELECT * FROM compra WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Compra(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("quantidade_total_de_produtos_comprados"),
                        rs.getDouble("valortotal"),
                        rs.getDate("data").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Compra> getComprasPorUsuario(int idUsuario) {
    String sql = "SELECT * FROM compra WHERE id_usuario = ? ORDER BY data DESC";
    List<Compra> compras = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, idUsuario);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Compra compra = new Compra(
                    rs.getInt("id"),
                    rs.getInt("id_usuario"),
                    rs.getInt("quantidade_total_de_produtos_comprados"),
                    rs.getDouble("valortotal"),
                    rs.getDate("data").toLocalDate()
                );
                compras.add(compra);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return compras;
    }


    public List<Compra> getComprasPorData(LocalDate data) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra WHERE data = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(data));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    compras.add(new Compra(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("quantidade_total_de_produtos_comprados"),
                        rs.getDouble("valortotal"),
                        rs.getDate("data").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }
    
    public List<Compra> getTodasCompras() {
    List<Compra> compras = new ArrayList<>();
    String sql = "SELECT * FROM compra ORDER BY data DESC";

    try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            compras.add(new Compra(
                rs.getInt("id"),
                rs.getInt("id_usuario"),
                rs.getInt("quantidade_total_de_produtos_comprados"),
                rs.getDouble("valortotal"),
                rs.getDate("data").toLocalDate()
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return compras;
    }
    
    public List<Compra> obterTotalRecebidoPorDia(LocalDate dataInicio, LocalDate dataFim) {
    List<Compra> totalPorDia = new ArrayList<>();
    String sql = "SELECT data, SUM(valortotal) as total FROM compra WHERE data BETWEEN ? AND ? GROUP BY data ORDER BY data ASC";

    try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
        stmt.setDate(2, java.sql.Date.valueOf(dataFim));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            totalPorDia.add(new Compra(rs.getDate("data").toLocalDate(), rs.getDouble("total")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return totalPorDia;
    }
    
    public List<UsuarioCompras> obterTotalComprasPorCliente(LocalDate dataInicio, LocalDate dataFim) {
    List<UsuarioCompras> comprasPorCliente = new ArrayList<>();
    String sql = "SELECT u.id, u.nome, SUM(c.quantidade_total_de_produtos_comprados) as total_compras " +
                 "FROM usuario u " +
                 "JOIN compra c ON u.id = c.id_usuario " +
                 "WHERE c.data BETWEEN ? AND ? " +
                 "GROUP BY u.id, u.nome " +
                 "ORDER BY total_compras DESC";

    try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
        stmt.setDate(2, java.sql.Date.valueOf(dataFim));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            comprasPorCliente.add(new UsuarioCompras(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("total_compras")  // Agora representa a soma das quantidades compradas
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return comprasPorCliente;
    }


    /*
    public List<UsuarioCompras> obterTotalComprasPorCliente(LocalDate dataInicio, LocalDate dataFim) {
    List<UsuarioCompras> comprasPorCliente = new ArrayList<>();
    String sql = "SELECT u.id, u.nome, COUNT(c.id) as total_compras " +
                 "FROM usuario u " +
                 "JOIN compra c ON u.id = c.id_usuario " +
                 "WHERE c.data BETWEEN ? AND ? " +
                 "GROUP BY u.id, u.nome " +
                 "ORDER BY total_compras DESC";

    try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
        stmt.setDate(2, java.sql.Date.valueOf(dataFim));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            comprasPorCliente.add(new UsuarioCompras(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("total_compras")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return comprasPorCliente;
    }
    */



}
