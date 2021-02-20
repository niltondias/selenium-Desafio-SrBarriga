package br.com.nilton;

import static br.com.nilton.utils.DataUtils.obterDataComDiferencaDias;
import static br.com.nilton.utils.DataUtils.obterDataFormatada;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.nilton.core.BaseTest;
import br.com.nilton.pages.MenuPage;
import br.com.nilton.pages.MovimentacaoPage;

public class MovimentacaoTest extends BaseTest {
    private MenuPage menuPage = new MenuPage();
    private MovimentacaoPage movPage = new MovimentacaoPage();

    @Test
    public void testaIncluiMovimento() {
        menuPage.acessarTelaMovimentacao();
        movPage.setDataTransacao("01/01/2020");
        movPage.setDataPagamento("01/02/2020");
        movPage.setDescricao("Aluguel");
        movPage.setInteressado("eu");
        movPage.setValor("100");
        movPage.setConta("Conta de Teste ALTERADA");
        movPage.setStatusPago();
        movPage.salvar();
        Assert.assertEquals("Movimentação adicionada com sucesso!", movPage.obterMensagemSucesso());
    }

    @Test
    public void testaCamposObrigatorios() {
        menuPage.acessarTelaMovimentacao();
        movPage.salvar();
        List<String> erros = movPage.obterErros();

        assertTrue(erros.containsAll(Arrays.asList("Data da Movimentação é obrigatório",
                "Data do pagamento é obrigatório", "Descrição é obrigatório", "Interessado é obrigatório",
                "Valor é obrigatório", "Valor deve ser um número")));

        assertEquals(6, erros.size(), "Deve ter 6 mensagens de erro");
    }

    @Test
    public void testaMovFutura() {
        Date dataFutura = obterDataComDiferencaDias(5);
        menuPage.acessarTelaMovimentacao();
        movPage.setDataTransacao(obterDataFormatada(dataFutura));
        movPage.setDataPagamento(obterDataFormatada(dataFutura));
        movPage.setDescricao("Aluguel");
        movPage.setInteressado("eu");
        movPage.setValor("100");
        movPage.setConta("Conta de Teste ALTERADA");
        movPage.setStatusPago();
        movPage.salvar();

        List<String> erros = movPage.obterErros();

        assertTrue(erros.contains("Data da Movimentação deve ser menor ou igual à data atual"));

        assertEquals(1, erros.size());

    }
        
}
