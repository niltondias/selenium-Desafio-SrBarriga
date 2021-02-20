package br.com.nilton.core;

import static br.com.nilton.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    /**
     * Busca um elemento pelo linkText e clica neste elemento
     * @param link - nome do linkText que deve ser clicaco
     */
    public void clicarLink(String link) {
        getDriver().findElement(By.linkText(link)).click();
	}

    /**
     * Escreve um texto no elemento expecificado
     * 
     * @param by    - objeto By para indicar de qual forma localiar o elemento
     * @param texto - texto que será digitado
     */
    public void escreve(By by, String texto) {
        // Limpando o conteúdo do campo antes de mandar escrever
        getDriver().findElement(by).clear();

        // Escrevendo no campo
        getDriver().findElement(by).sendKeys(texto);
    }

    /**
     * Escreve um texto no elemento expecificado
     * @param id - id do elemento
     * @param texto - texto que será digitado
     */
    public void escreve(String id, String texto) {
        // Chamando outra versão do método que utiliza o By como parametro
        escreve(By.id(id), texto);
    }
 
    
    /**
     * Clica em um elemento 
     * @param id - ID do elemento
     */
    public void clica(String id) {
        getDriver().findElement(By.id(id)).click();
    }

   
    /**
     * Seleciona um item de um elemento select através do texto visivel.
     * Método usado para múltiplas selecções
     * @param id - ID do elemento
     * @param lista - Array com texto que está na lista e deve ser selecionado 
     */
    public void selecionaPorVisivel(String id, List<String> lista) {
        
        lista.forEach(texto -> 
                new Select( getDriver().findElement(By.id(id)))
                    .selectByVisibleText(texto)
        );
    }

    /**
     * Seleciona um item de um elemento select através do atributo value do elemento.
     * Método usado para múltiplas selecções
     * @param id - ID do elemento
     * @param lista - Array com texto que está na lista e deve ser selecionado 
     */
    public void selecionaPorValor(String id, List<String> lista) {
        
        lista.forEach(texto -> 
                new Select( getDriver().findElement(By.id(id)))
                    .selectByValue(texto)
        );
    }

    /**
     * Seleciona um item de um elemento select através da propriedade value do elemento.
     * Método usado para uma única seleção
     * @param id - ID do elemento
     * @param texto - Texto que está na lista e deve ser selecionado 
     */
    public void selecionaPorValor(String id, String texto) {
        new Select( getDriver().findElement(By.id(id))).selectByValue(texto);
    }

    /**
     * Seleciona um item de um elemento select através do seu indice.
     * Método usado para uma única seleção
     * @param id - ID do elemento
     * @param index - indice que identifica o item na lista 
     */
    public void selecionaPorIndex(String id, int index) {
        new Select(getDriver().findElement(By.id(id))).selectByIndex(index);
    }

    /**
     * Seleciona um item de um elemento select através do indice do elemento.
     * Método usado para múltiplas selecções
     * @param id - ID do elemento
     * @param lista - Array com os indices que devem ser selecionados 
     */
    public void selecionaPorIndex(String id, List<Integer> lista) {
        
        lista.forEach(index -> 
                new Select( getDriver().findElement(By.id(id)))
                    .selectByIndex(index)
        );
    }


     /**
     * Seleciona um item de um elemento select através do texto visivel.
     * Método usado para uma única seleção
     * @param id - ID do elemento
     * @param texto - Texto que está na lista e deve ser selecionado 
     */
    public void selecionaPorVisivel(String id, String texto) {
        new Select( getDriver().findElement(By.id(id))).selectByVisibleText(texto);
    }
    
    /**
     * Desmarca um item no select basedo pelo item visivel
     * @param id - ID do elemento
     * @param texto - Texto visivel que será desmarcado
     */
    public void deSelecionaPorVisivel(String id, String texto) {
        new Select( getDriver().findElement(By.id(id))).deselectByVisibleText(texto);
    }

    /**
     * Obtem o texto de um elemento textField
     * @param by - objeto que identifica a forma como deve ser localizado o elemento
     * @return - Retorta o texto do elemento textField
     */
    public String obtemValor(By by) {
        return getDriver().findElement(by).getAttribute("value");
    }

    /**
     * Obtem o texdo de um elemento textField
     * @param id
     * @return - Retorna o valor do elemento textField
     */
    public String obtemValor(String id) {
        return obtemValor(By.id(id));
    }

    /**
     * Obtem o texto visível de um elemento
     * @param by - Objeto que indica de qual forma o elemento será localizado
     * @return - Retorna o texto visível de um elemento
     */
    public String obtemTexto(By by) {
        return getDriver().findElement(by).getText();

    }

     /**
     * Obtem o texto visível de um elemento. Faz uma chamada ao mesmo método mas recebendo uma string
     * com o ID
     * @param id - ID do elemento
     * @return - Retorna o texto visível de um elemento
     */

    public String obtemTexto(String id) {
        return obtemTexto(By.id(id));
    }
    
    /**
     * Trás todos os elementos filhos de um elemento buscando pelo ID e filtrando pela tag
     * @param id - ID do elemento pai
     * @param tag - tag para filtro
     * @return - Retorna uma lista de elementos 
     */
    public List<WebElement> listaElementos(String id, String tag) {
        return listaElementos(By.id(id), By.tagName(tag));
    }

    /**
     * Trás todos os elementos filhos de um elemento buscando pelo ID e filtrando pela tag
     * @param ByPai - By do elemento pai
     * @param by - By que define a forma como o elemento será localizado
     * @return - Retorna uma lista de elementos 
     */
    public List<WebElement> listaElementos(By byPai, By by) {
        return getDriver().findElement(byPai).findElements(by);
    }

    /**
     * Verifica se o elemento está marcado 
     * @param id - ID do elemento
     * @return - Retorna se está selecionado
     */
    public boolean estaSelecionado(String id) {
        return getDriver().findElement(By.id(id)).isSelected();
    }

      /**
     * Verifica qual item do combo Box está selecionado e retorna o mesmo 
     * @param id - ID do elemento
     * @return - Retorna o texto do item que está selecionado
     */
    public String comboItemSelecionado(String id) {
          return new Select (getDriver().findElement(By.id(id))).getFirstSelectedOption().getText();
    }

    /**
     * Diz qual é o tamanho de uma lista combo
     * @param id - ID do elemento
     * @return - Retorna o tamanho da lista
     */
    public int comboTamanho(String id) {
        // Converte um elemento em um select, pega sua lista e seu tamanho
        return new Select(getDriver().findElement(By.id(id))).getOptions().size();
    }

    /**
     * Diz qual é a quantidade de itens selecionados em um comboBox
     * @param id - ID do elementp
     * @return - Retorna a quantidade de elementos selecionados
     */
    public int comboQtdSelecionados(String id) {
        
        // Converte um elemento em um select, pega uma lista com todos os selecionados e seu tamanho
        return new Select(getDriver().findElement(By.id(id))).getAllSelectedOptions().size();
    }

    /**
     * Busca um item no comboBox e retorna se localizou ou não
     * @param id - ID do elemento
     * @param item - Item a ser buscado bo comboBox
     * @return - true se achou o item no comboBox
     */
    public boolean comboBuscaItem(String id, String item) {
        // Localizando o elemento pelo ID e armazenando sua instancia em uma variável
        WebElement element = getDriver().findElement(By.id(id));

        // Criando um objeto select para manibular o elemento combo
        Select combo = new Select(element);

        // Retorna uma lista com todos os elementos do combo
        List<WebElement> options = combo.getOptions();

        // variavel logica que indica se localizou um determinado item no combo
        boolean encontrou = false;

        // Varrendo o combo checando os textos e localizando o item 
        for(WebElement option: options) {
            if(option.getText().equals(item)) {
                encontrou = true;
                break;
            }
        }

        return encontrou;
    }

    // Muda para o Alert, obtem o texto do alert e aceita
    public String alertaObtemTextoEAceita() {
        Alert alerta = getDriver().switchTo().alert();
        String msg = alerta.getText();
        alerta.accept();

        return msg;
    }

    /*********  Javascript ***********/
    
    public Object executarJs(String cmd, Object... param) { // Executanto JavaScript direto pelo Selenium
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        
        return js.executeScript(cmd, param);
        
    }
    
    /*********  Tabelas ***********/

    /**
     * Localiza uma linha na tabela e clica no botão que estiver nesta linha
     * @param colunaNome - Nome da coluna a ser buscada
     * @param buscaNome - Texto que será buscado nesta linha
     * @param colunaBotao - Coluna onde encontra-se o botão a ser clicado
     * @param nomeTabela - Id do elemento table que tera as linhas varridas
     */
	public void clicaBotaoDaTabela(String colunaNome, String buscaNome, String colunaBotao, String nomeTabela, int indiceBotao) {
        
        // Obter a coluna do registro
        int idColunaRegistro = obtemIndiceColuna(colunaNome, nomeTabela);
        
        // Obter a linha do registro
        int idLinhaRegistro  = obtemIndiceLinha(idColunaRegistro, buscaNome, nomeTabela);
        
        // Obter a coluna do botão
        int idColunaBotao = obtemIndiceColuna(colunaBotao, nomeTabela);
        
        // Obtendo o elemento do botão
        WebElement tabela = obtemTabela(nomeTabela);
        WebElement botao = tabela.findElement(
                By.xpath("./tbody/tr["+idLinhaRegistro+"]/td["+idColunaBotao+"]//a["+indiceBotao+"]"));
        
        // Clicar no botão da célula encontrada
        botao.click();
    }

    private int obtemIndiceColuna(String colunaNome, String nomeTabela) {
                
        // Obter a coluna do registro
        WebElement tabela = obtemTabela(nomeTabela);
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;
        for(int i=0;i<colunas.size();i++) {
            if(colunas.get(i).getText().equals(colunaNome)) {
                idColuna = i+1;
                break;
            }
        }

        return idColuna;
    }
    
    protected WebElement obtemTabela(String nomeTabela) {
        return getDriver().findElement(By.xpath("//*[@id='"+nomeTabela+"']"));
    }
    
    private int obtemIndiceLinha(int idColunaRegistro, String buscaNome, String nomeTabela) {
        WebElement tabela = obtemTabela(nomeTabela);
        
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColunaRegistro+"]"));

        int idLinhaRegistro = -1;

        for(int i=0; i<linhas.size(); i++) {
            if(linhas.get(i).getText().equals(buscaNome)) {
                idLinhaRegistro = i+1;
                break;
            }
        }

        return idLinhaRegistro;
    }

    /**
     * Clica no botão da tela de login e outras telas 
     */
    public void clicaSubmit() {
        By botaoLocator = By.xpath("//*[@type='submit']");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(botaoLocator));
        getDriver().findElement(botaoLocator).click();

    }

    
}
