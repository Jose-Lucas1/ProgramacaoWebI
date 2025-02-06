package controle.usuario;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.usuario.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para a inserir um novo usuário do tipo cliente
 */
public class InserirClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada de dados */
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        /* processamento de dados */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.inserirCliente(nome, endereco, email, login, senha);
        request.setAttribute("mensagem", (sucesso ? "Cliente inserido com sucesso" : "Não foi possível inserir o cliente"));
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio");
        dispatcher.forward(request, response);
    }

}
