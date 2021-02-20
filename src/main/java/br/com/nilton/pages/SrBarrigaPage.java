package br.com.nilton.pages;

import static br.com.nilton.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.nilton.core.BasePage;

public class SrBarrigaPage extends BasePage {

    /**
     * Preenche os campos de email e senha na tela de login
     * @param email - email do usuario
     * @param senha - senha do usuario
     */
    private void setEmailLogin(String email, String senha) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        this.escreve("email", email);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("senha")));
        this.escreve("senha", senha);
    }
    
	public void login(String email, String senha) {
        this.setEmailLogin(email, senha);
        this.clicaSubmit();     
    }
    
    /**
     * Clina na opção de incluir a conta
     */
    public void abreIncluiConta() {
        getDriver().findElement(By.xpath("//*[@id='navbar']//li[2]")).click();
        getDriver().findElement(By.xpath("//*[@id='navbar']//li[2]/ul/li[1]/a")).click();        
    }

    /**
     * Clica na opção de listar as contas para alterar
     */
    public void listaContas() {
        getDriver().findElement(By.xpath("//*[@id='navbar']//li[2]")).click();
        getDriver().findElement(By.xpath("//*[@id='navbar']//li[2]/ul/li[2]/a")).click();        
    }

    /**
     * Clina na opção de Movimentação mensal
     */
    public void abreMovMensal() {
        getDriver().findElement(By.xpath("//*[@id='navbar']//li[4]")).click();
    }


    /**
     * Preenche o campo nome da conta
     * @param texto - texto que será inserido no nome da conta
     */
	public void setConta(String texto) {
        escreve("nome", texto);
	}

    /**
     * Clica no botão salvar da tela de contas
     * Verifica se cadastrou com sucesso através da localização da div class
     * @return - Retorna verdadeiro se conseguiu gravar com sucesso
     */
	public boolean gravaConta() {
        this.clicaSubmit();
        // "Conta adicionada com sucesso!"
        List<WebElement> elementos = getDriver().findElements(By.xpath("//div[@class='alert alert-success']"));
        return (elementos.size() != 0);
    }

    /**
     * Solicitada a alteração da conta
     * @param conta - nome da conta que será pesquisada
     * @param contaNova - nome da conta que substituirá na alteração
     * @return - retorna se obteve sucesso ao gravar
     */
    public boolean alteraConta(String conta, String contaNova) {
        clicaBotaoDaTabela(conta, "Conta", "Ações", "tabelaContas",1 );
        setConta(contaNova);    
        return gravaConta();    
    }

    /**
     * Clina na opção de movimentação
     */
	public void abreIncluiMov() {
        getDriver().findElement(By.xpath("//*[@id='navbar']//li[3]")).click();        
    }
    
    /**
     * Preenche o campo data da transação
     * @param texto - Texto que será preenchido no elemento
     */
    public void setDataTransacao(String texto) {
        escreve("data_transacao", texto);
    }

    /**
     * Preenche o campo data de pagamento
     * @param texto - Texto que será preenchido no elemento
     */
    public void setDataPagamento(String texto) {
        escreve("data_pagamento", texto);
    }

    /**
     * Preenche o campo descrição da movimentação
     * @param texto - Texto que será preenchido no elemento
     */
    public void setDescricao(String texto) {
        escreve("descricao", texto);
    }

    /**
     * Preenche o campo interessado na movimentação
     * @param texto - Texto que será preenchido no elemento
     */
    public void setInteressado(String texto) {
        escreve("interessado", texto);
    }

    public void setValor(String valor) {
        escreve("valor", valor);
    }

    // Seleciona a conta do movimento
	public void setContaMov(String conta) {
        selecionaPorVisivel("conta", conta);
    }
    
    /**
     * Verifica se todas as mensagens de campo obrigatório estão visiveis
     * na pagina
     * @return returna TRUE se achar todos os alerts e FALSE se não
     */
    public boolean mensagensCamposObrigatoriosMov() {
                
        List<WebElement> alerts = listaElementos(By.xpath("//div[@class='alert alert-danger']/ul"), 
            By.tagName("li"));

        String[] mensagens = {"Data da Movimentação é obrigatório",
            "Data do pagamento é obrigatório",
            "Descrição é obrigatório",
            "Interessado é obrigatório",
            "Valor é obrigatório",
            "Valor deve ser um número"};

        boolean mostrouTodosOsErros = true;
        for(int i = 0; i<alerts.size(); i++) {
            if( !( alerts.get(i).getText() ).equals(mensagens[i]) ) {
                mostrouTodosOsErros = false;
                break;
            }
        }

        return mostrouTodosOsErros;

    }

	public void filtraMov(String mes, String ano) {
        selecionaPorVisivel("mes", mes);
        selecionaPorVisivel("ano", ano);
    }
    
        /**
     * Tenta excluir a conta
     * Se a conta tiver movimentação deve retornar FALSE
     * @param conta - nome da conta que será excluída
     * @return - retorna TRUE se conseguir excluir e FALSE se não conseguir
     */
    public boolean excluiMov(String descricao) {
        clicaBotaoDaTabela("Descrição", descricao, "Ações", "tabelaExtrato",1 );

        return true;    
    }

    public boolean excluiConta(String conta) {
        clicaBotaoDaTabela("Conta", conta, "Ações", "tabelaContas",2);  
        List<WebElement> alerts = listaElementos(By.xpath("//div[@class='alert alert-danger']/ul"), 
        By.tagName("li"));
                
        // Se o alert tiver se tamanho 0 quer dizer que não houve erro e deve retornar
        // TRUE
        //
        return ( alerts.size() == 0 );
    }

    public int contasComSaldo() {
        WebElement tabelaSaldos = obtemTabela("tabelaSaldo");
        List<WebElement> linhas = tabelaSaldos.findElements(By.xpath("./tbody/tr"));
        return linhas.size();
    }

    public int resumoMensal() {
        WebElement tabelaExtrato = obtemTabela("tabelaExtrato");
        List<WebElement> linhas = tabelaExtrato.findElements(By.xpath("./tbody/tr"));
        return linhas.size();
    }

    public void ClicaHome() {
        getDriver().findElement(By.xpath("//*[@id='navbar']//a[@href='/']")).click();
    }

    public void ClicaResumoMensal() {
        getDriver().findElement(By.xpath("//*[@id='navbar']//a[@href='/extrato']")).click();
    }
}
