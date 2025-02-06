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
public class atualizarDadosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        /* entrada de dados */
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        /* processamento de dados */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.atualizarCliente(usuario,nome, endereco, email, login, senha);
        request.setAttribute("mensagem", (sucesso ? "Dados atualizados com sucesso" : "Não foi possível atualizar os dados"));
        usuario = usuarioDAO.obterPorLogin(login);
        session.setAttribute("usuario", usuario);
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
        dispatcher.forward(request, response);
    }
}
