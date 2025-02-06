package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.usuario.Usuario;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Filtro que implementação a segurança que somente usuários administradores
 * acessem os recursos que são ações de usuários administradores
 */
public class AdministradorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // se na requisição tem na URL o prefixo /administrador/
        if (req.getRequestURI().startsWith(req.getContextPath() + "/administrador/")) {
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            // se o usuário na sessão não existe ou não é administrador
            if (usuario == null || !usuario.isAdministrador()) {
                request.setAttribute("mensagem", "Você não tem permissão de usuário administrador");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio");
                dispatcher.forward(request, response);
                // se é um administrador
            } else {
                chain.doFilter(request, response);
            }
            // se não for requisição de ações de usuário administrador
        } else {
            chain.doFilter(request, response);
        }
    }

}
