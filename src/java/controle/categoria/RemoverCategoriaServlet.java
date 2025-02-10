package controle.categoria;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.CategoriaDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe de controle para implementar a ação de remover um produto
 */
public class RemoverCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada de dados */
        int id = Integer.parseInt(request.getParameter("id"));
        /* processamento de dados */
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean sucesso = categoriaDAO.deletarCategoria(id);
        request.setAttribute("mensagem", (sucesso ? "Categoria removida com sucesso" : "Não foi possível remover a categoria"));
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("/administrador/ListarCategorias");
        dispatcher.forward(request, response);
    }

}
