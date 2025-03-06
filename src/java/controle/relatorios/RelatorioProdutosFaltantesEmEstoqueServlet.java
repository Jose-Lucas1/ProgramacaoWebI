package controle.relatorios;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.compra.Compra;
import modelo.compra.CompraDAO;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;
import modelo.usuario.UsuarioCompras;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import java.time.format.DateTimeFormatter;

public class RelatorioProdutosFaltantesEmEstoqueServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"relatorio.pdf\"");

        // Capturando as datas do request
        String dataInicioStr = request.getParameter("dataInicio");
        String dataFimStr = request.getParameter("dataFim");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInicio = (dataInicioStr != null && !dataInicioStr.isEmpty()) ? LocalDate.parse(dataInicioStr, formatter) : null;
        LocalDate dataFim = (dataFimStr != null && !dataFimStr.isEmpty()) ? LocalDate.parse(dataFimStr, formatter) : null;

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.beginText();
                
                float startX = 50;
                float startY = 750;
                float leading = 18f;
                contentStream.setLeading(leading);
                contentStream.newLineAtOffset(startX, startY);

                // Título
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.showText("Relatório de Produtos Faltantes no Estoque");
                contentStream.newLine();
                contentStream.newLine();

                // Cabeçalhos
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.showText("ID");
                contentStream.newLineAtOffset(50, 0);
                contentStream.showText("Descrição");
                contentStream.newLineAtOffset(200, 0);
                contentStream.showText("Qtd. Faltante");
                contentStream.newLineAtOffset(150, 0);
                contentStream.showText("Preço");
                contentStream.newLine();
                contentStream.newLineAtOffset(-400, 0);
                contentStream.showText("----------------------------------------------------------------------------------------------------------------");
                contentStream.newLine();

                // Listagem dos produtos
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List<Produto> produtosFaltantes = produtoDAO.obterProdutosFaltantesEmEstoque();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                for (Produto p : produtosFaltantes) {
                    contentStream.showText(String.valueOf(p.getId()));
                    contentStream.newLineAtOffset(50, 0);
                    contentStream.showText(p.getDescricao());
                    contentStream.newLineAtOffset(200, 0);
                    contentStream.showText(String.valueOf(p.getQuantidade()));
                    contentStream.newLineAtOffset(150, 0);
                    contentStream.showText(String.format("R$ %.2f", p.getPreco()));
                    contentStream.newLineAtOffset(-400, -leading);
                }

                contentStream.newLine();
                contentStream.newLine();
                
                // Segunda tabela: Relatório de Receita Diária
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.showText("Relatório de Receita Diária");
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.showText("Data");
                contentStream.newLineAtOffset(200, 0);
                contentStream.showText("Total Recebido");
                contentStream.newLine();
                contentStream.newLineAtOffset(-200, 0);
                contentStream.showText("------------------------------------------------------");
                contentStream.newLine();

                CompraDAO compraDAO = new CompraDAO();
                List<Compra> totalPorDia = compraDAO.obterTotalRecebidoPorDia(dataInicio, dataFim);
                
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                for (Compra c : totalPorDia) {
                    contentStream.showText(c.getData().format(formatter));
                    contentStream.newLineAtOffset(200, 0);
                    contentStream.showText(String.format("R$ %.2f", c.getValortotal()));
                    contentStream.newLineAtOffset(-200, -leading);
                }

                contentStream.newLine();
                contentStream.newLine();
                
                // Terceira tabela: Relatório de Compras por Cliente
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.showText("Relatório de Compras por Cliente");
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.showText("ID Usuário");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Nome");
                contentStream.newLineAtOffset(200, 0);
                contentStream.showText("Total de Compras");
                contentStream.newLine();
                contentStream.newLineAtOffset(-300, 0);
                contentStream.showText("-------------------------------------------------------------");
                contentStream.newLine();

                List<UsuarioCompras> comprasPorCliente = compraDAO.obterTotalComprasPorCliente(dataInicio, dataFim);
                
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                for (UsuarioCompras uc : comprasPorCliente) {
                    contentStream.showText(String.valueOf(uc.getId()));
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(uc.getNome());
                    contentStream.newLineAtOffset(200, 0);
                    contentStream.showText(String.valueOf(uc.getTotal_compras()));
                    contentStream.newLineAtOffset(-300, -leading);
                }

                contentStream.endText();
            }

            document.save(response.getOutputStream());
        }
    }
}
