package controle.compra;

import java.io.IOException;
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

public class ListarCompraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        int idUsuario = usuario.getId();
        
        CompraDAO compraDAO = new CompraDAO();
        List<Compra> compras = compraDAO.getComprasPorUsuario(idUsuario);
        request.setAttribute("compras", compras);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/compra/listarCompra.jsp");
        dispatcher.forward(request, response);
    }
}
