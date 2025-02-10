package controle.categoria;

import controle.produto.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaDAO;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para preparar um formulário para inserir ou atualizar um
 * produto
 */
public class FormCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        String id = request.getParameter("id");
        
        if (id != null && id.trim().length() > 0) {
            Categoria categoria = categoriaDAO.obterPorId(Integer.parseInt(id));
            request.setAttribute("categoria", categoria);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoria/form.jsp");
        dispatcher.forward(request, response);
    }

}
