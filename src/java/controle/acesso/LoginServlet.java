package controle.acesso;

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
 * 
 * 
 * Classe de controle para implementar a ação de login
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada de dados */
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        /* processamento de dados */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.identificar(login, senha);
        if (sucesso) {
            HttpSession session = request.getSession(true);
            Usuario usuario = usuarioDAO.obterPorLogin(login);
            session.setAttribute("usuario", usuario);
        } else {
            request.setAttribute("mensagem", "Login ou senha inválida");
        }
        /* saída do processamento de dados */
        if (sucesso) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("protegida.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

}
