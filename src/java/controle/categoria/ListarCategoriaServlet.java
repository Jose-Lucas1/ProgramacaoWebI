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
 * Classe de controle para implementar a ação de listar produtos
 */
public class ListarCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* processamento de dados */
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.obterTodasCategorias();
        request.setAttribute("categorias", categorias);
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoria/listar.jsp");
        dispatcher.forward(request, response);
    }

}
