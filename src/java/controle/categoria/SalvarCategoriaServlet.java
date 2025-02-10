package controle.categoria;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para implementar a ação de inserir ou atualizar um produto sem upload de arquivo
 */
public class SalvarCategoriaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            boolean sucesso = false;

            // Get parameters from request
            String idParam = request.getParameter("id");
            int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : -1;
            
            String descricao = request.getParameter("descricao");
            System.out.println("descricao aqui: "+descricao);
            // Process data using DAO
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria.setDescricao(descricao);
            if (id != -1) {
                sucesso = categoriaDAO.atualizarCategoria(categoria); // Foto is null
            } else {
                sucesso = categoriaDAO.inserirCategoria(descricao); // Foto is null
            }

            // Set response message
            request.setAttribute("mensagem", (sucesso ? "Categoria salva com sucesso" : "Não foi possível salvar a categoria"));
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        // Forward to product listing page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/administrador/ListarCategorias");
        dispatcher.forward(request, response);
    }
}
