package br.com.patrimonio.bean;

import br.com.patrimonio.dao.DocumentoFiscalDao;
import br.com.patrimonio.dao.FornecedorDao;
import br.com.patrimonio.domain.DocumentoFiscal;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.util.JSFUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele o criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBRelatorio") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class Relatorio {
    // variavel que guarda o resultado de consulta (variavel de tela)

    ArrayList<Relatorio> itens;
    Relatorio relatorio = new Relatorio();

    public ArrayList<Relatorio> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Relatorio> itens) {
        this.itens = itens;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

}
