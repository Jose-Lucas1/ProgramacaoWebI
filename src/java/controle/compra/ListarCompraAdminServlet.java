package controle.compra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.compra.Compra;
import modelo.compra.CompraDAO;
import modelo.usuario.Usuario;
import modelo.usuario.UsuarioDAO;

public class ListarCompraAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        // Lista para armazenar os nomes dos usuários
        List<String> nomesUsuarios = new ArrayList<>();
        
        CompraDAO compraDAO = new CompraDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        List<Compra> compras = compraDAO.getTodasCompras();
        request.setAttribute("compras", compras);

        // Itera sobre as compras e busca os nomes dos usuários
        for (Compra compra : compras) {
            Usuario user = usuarioDAO.obterPorId(compra.getId_usuario());
            nomesUsuarios.add(user != null ? user.getNome() : "Usuário desconhecido");
        }
        request.setAttribute("nomesUsuarios", nomesUsuarios);
       
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/compra/listarCompraAdmin.jsp");
        dispatcher.forward(request, response);
    }
}
