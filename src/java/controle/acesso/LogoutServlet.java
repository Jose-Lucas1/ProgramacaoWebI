package controle.acesso;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Leonardo Oliveira Moreira
 * 
 * Classe de controle para implementar a ação de logout
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("mensagem", "Sua sessão foi encerrada");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println(ex);
        }
    }
}
