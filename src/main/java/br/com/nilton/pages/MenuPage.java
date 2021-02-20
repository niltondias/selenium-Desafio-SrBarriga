package br.com.nilton.pages;

import br.com.nilton.core.BasePage;

public class MenuPage extends BasePage {
    public void acessarTelaInserirConta() {
        clicarLink("Contas");
        clicarLink("Adicionar");
    }

    public void acessarTelaListaConta() {
        clicarLink("Contas");
        clicarLink("Listar");
    }

    public void acessarTelaMovimentacao() {
        clicarLink("Criar Movimentação");
    }
    
}
