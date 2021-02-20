package br.com.nilton.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.nilton.core.BasePage;

public class MovimentacaoPage extends BasePage {
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
	public void setConta(String conta) {
        selecionaPorVisivel("conta", conta);
    }

    public void setStatusPago() {
        clica("status_pago");
    }

    public void salvar() {
        clicaSubmit();
    }

    public String obterMensagemSucesso() {
        return obtemTexto(By.xpath("//div[@class='alert alert-success']"));
    }
    
    public List<String> obterErros() {
        List<WebElement> erros = listaElementos(By.xpath("//div[@class='alert alert-danger']/ul"),
            By.tagName("li"));
        List<String> retorno = new ArrayList<String>();
        for(WebElement erro : erros) {
            retorno.add(erro.getText());
        }
        return retorno;
    }
}
