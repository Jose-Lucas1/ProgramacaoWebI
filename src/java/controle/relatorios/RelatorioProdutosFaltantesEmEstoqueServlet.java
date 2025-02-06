package controle.relatorios;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle que implementa um relatório que lista os produtos que
 * estão faltando em estoque
 */
public class RelatorioProdutosFaltantesEmEstoqueServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configuração da resposta HTTP para exportar o PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_produtos_faltantes.pdf\"");
        // Criando o documento PDF
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            // Criando o fluxo de conteúdo da página
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.beginText();
                // Configurações iniciais do texto
                contentStream.setLeading(14.5f); // Espaçamento entre linhas
                contentStream.newLineAtOffset(50, 750); // Posição inicial (x, y)
                // Título
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD),
                        16);
                contentStream.showText("Relatório de Produtos Faltantes no Estoque");
                contentStream.newLine();
                contentStream.newLine();
                // Resetando para fonte normal
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List<Produto> produtos = produtoDAO.obterProdutosFaltantesEmEstoque();
                // Adicionando os nomes à página
                for (Produto p : produtos) {
                    contentStream.showText("- " + p.getId() + " - " + p.getDescricao() + " - "
                            + p.getPreco());
                    contentStream.newLine();
                }
                contentStream.endText();
            }
            // Escrevendo o PDF na resposta HTTP
            document.save(response.getOutputStream());
        }
    }

}
