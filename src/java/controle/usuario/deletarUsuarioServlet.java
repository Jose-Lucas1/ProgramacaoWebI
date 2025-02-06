package controle.usuario;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.usuario.Usuario;
import modelo.usuario.UsuarioDAO;

/*
 * @author Leonardo Oliveira Moreira
 * 
 * Classe de controle para implementar a ação de logout
 */
public class deletarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("paralibu");
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.deletarCliente(usuario);
        session.invalidate();
        request.setAttribute("mensagem", (sucesso ? "Usuario deletado com sucesso" : "Não foi possível deletar o usuario"));
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
        dispatcher.forward(request, response);
    }

}
