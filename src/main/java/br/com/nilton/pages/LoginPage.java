package br.com.nilton.pages;

import static br.com.nilton.core.DriverFactory.getDriver;

import br.com.nilton.core.BasePage;

public class LoginPage extends BasePage {
    public void acessarTelaInicial() {
        getDriver().get("https://srbarriga.herokuapp.com/");

    }

    public void setEmail(String email) {
        escreve("email", email);
    }

    public void setSenha(String senha) {
        escreve("senha",senha);
    }

    public void entrar() {
        clicaSubmit();
    }
}
