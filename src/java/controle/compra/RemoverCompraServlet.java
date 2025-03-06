package controle.compra;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.compra.CompraDAO;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe de controle para implementar a ação de remover um produto
 */
public class RemoverCompraServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada de dados */
        int id = Integer.parseInt(request.getParameter("id"));
        /* processamento de dados */
        CompraDAO compraDAO = new CompraDAO();
        boolean sucesso = compraDAO.deletarCompra(id);
        request.setAttribute("mensagem", (sucesso ? "Compra deletada com sucesso" : "Não foi possível deletar a compra"));
        /* saída do processamento de dados */
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarCompraAdminServlet");
        dispatcher.forward(request, response);
    }

}
