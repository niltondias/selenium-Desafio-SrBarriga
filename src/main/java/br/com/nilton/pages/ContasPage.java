package br.com.nilton.pages;

import org.openqa.selenium.By;

import br.com.nilton.core.BasePage;

public class ContasPage extends BasePage {
    public void setNome(String nome) {
        escreve("nome", nome);
    }
    
    public void salvar() {
        clicaSubmit();
    }

    public String obterMensagemSucesso() {
        return obtemTexto(By.xpath("//div[@class='alert alert-success']"));
    }

    public String obterMensagemErro() {
        return obtemTexto(By.xpath("//div[@class='alert alert-danger']"));
    }



	public void clicaAlterarConta(String conta) {
        clicaBotaoDaTabela("Conta", conta, "Ações", "tabelaContas",1 );

	}
}
