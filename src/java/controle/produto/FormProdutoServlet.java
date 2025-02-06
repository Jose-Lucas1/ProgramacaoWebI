package controle.produto;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para preparar um formulário para inserir ou atualizar um
 * produto
 */
public class FormProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada opcional de dados */
        String id = request.getParameter("id");
        /* processamento de dados */
        if (id != null && id.trim().length() > 0) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.obter(Integer.parseInt(id));
            request.setAttribute("produto", produto);
        }
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/produto/form.jsp");
        dispatcher.forward(request, response);
    }

}
