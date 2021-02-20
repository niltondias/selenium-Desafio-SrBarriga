package br.com.nilton;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import br.com.nilton.core.BaseTest;
import br.com.nilton.pages.ContasPage;
import br.com.nilton.pages.MenuPage;
import br.com.nilton.pages.SrBarrigaPage;

public class ContaTest extends BaseTest {
    private SrBarrigaPage page;
    private MenuPage menuPage = new MenuPage();
    private ContasPage contaPage = new ContasPage();

    // @Before
    // public void inicia() {
    //     page = new SrBarrigaPage();
    //     page.login("nilton@email.com.br", "1234");
    // }

    @Test
    public void test() {
    }

    @Test
    public void testaIncluiContaComSucesso() {
        menuPage.acessarTelaInserirConta();
        contaPage.setNome("conta do teste");
        contaPage.salvar();
        assertEquals("Conta adicionada com sucesso!", contaPage.obterMensagemSucesso());
    }
    
    @Test
    public void testaAlteraConta() {
        menuPage.acessarTelaListaConta();
        contaPage.clicaAlterarConta("conta do teste");
        contaPage.setNome("Conta de Teste ALTERADA");
        contaPage.salvar();
        
        assertEquals("Conta alterada com sucesso!",contaPage.obterMensagemSucesso());
    }

    @Test
    public void testaIncluiContaJaExiste() {
        menuPage.acessarTelaInserirConta();
        contaPage.setNome("Conta de Teste ALTERADA");
        contaPage.salvar();
        assertEquals("Já existe uma conta com esse nome!",contaPage.obterMensagemErro());
    }

    @Test
    public void testaIncluiMovimento() {
        page.abreIncluiMov();
        page.setDataTransacao("24/01/2021");
        page.setDataPagamento("24/01/2021");
        page.setDescricao("Conta de luz");
        page.setInteressado("Jose Aldo");
        page.setValor("250.33");
        page.setContaMov("contanova");
        
        // Validando se gravou o movimento
        assertTrue(page.gravaConta());
    }

    @Test
    public void testaExcluiMov() {
        page.abreMovMensal();
        page.filtraMov("Janeiro","2020");
        page.clicaSubmit();
        assertTrue( page.excluiMov("teste") );
    }

    @Test
    public void testaExcluiContaComMov() {
        page.listaContas();
        assertFalse( page.excluiConta("contamov") );
    }

    @Test
    public void testaSaldoContas() {
        page.ClicaHome();
        assertTrue("Deve haver pelo menos uma conta com saldo", page.contasComSaldo() > 0);
    }

    @Test
    public void testaResumoConta() {
        page.ClicaResumoMensal();
        page.clicaSubmit();
        assertTrue("Deve haver pelo menos uma movimentação", page.resumoMensal() > 0);
    }
}
