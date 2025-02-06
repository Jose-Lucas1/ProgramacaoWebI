package controle.produto;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe de controle para implementar a ação de remover um produto
 */
public class RemoverProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada de dados */
        int id = Integer.parseInt(request.getParameter("id"));
        /* processamento de dados */
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean sucesso = produtoDAO.remover(id);
        request.setAttribute("mensagem", (sucesso ? "Produto removido com sucesso" : "Não foi possível remover o produto"));
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("/administrador/ListarProdutos");
        dispatcher.forward(request, response);
    }

}
