package br.com.nilton.core;

import static br.com.nilton.core.DriverFactory.getDriver;
import static br.com.nilton.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.com.nilton.pages.LoginPage;

public class BaseTest {
    private LoginPage page = new LoginPage();
    @Rule
    public TestName testName = new TestName();

    @Before
    public void inicializa() {
        page.acessarTelaInicial();
        page.setEmail("nilton@email.com.br");
        page.setSenha("1234");
        page.entrar();

    }

    @After
    public void finaliza() throws IOException {
        // Print Screen da tela no final do teste
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot"+ 
                File.separator + testName.getMethodName() + ".jpg"));

        // Encerrando o driver do Browse
        if(Propriedades.FECHAR_BROWSE) {
            killDriver();
        }
    }
    
}
