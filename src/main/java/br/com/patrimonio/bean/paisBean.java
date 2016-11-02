package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.PaisDao;
import br.com.patrimonio.domain.Pais;
import br.com.patrimonio.util.JSFUtil;
import java.io.Serializable;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBPais") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class paisBean implements Serializable {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Pais> itens;
    private ArrayList<Pais> itensFiltrados;// Vai armazenar os itens filtrados
    PaisDao dao = new PaisDao();
    private Pais pais;

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    public PaisDao getDao() {
        return dao;
    }

    public ArrayList<Pais> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Pais> itens) {
        this.itens = itens;
    }

    public ArrayList<Pais> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Pais> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public void setDao(PaisDao dao) {
        this.dao = dao;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @PostConstruct
    public void prepararPesquisa() {
        try {
            PaisDao dao = new PaisDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null

        pais = new Pais();

    }

    public void salvar() {

        try {
            prepararEditar();

            PaisDao dao = new PaisDao();
            dao.salvar(pais);
            itens = dao.listar();
            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }

    }

    public void prepararEditar() {
        // metodo criado para resolver o problema do objeto= null
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {
            PaisDao dao = new PaisDao();
            dao.alterar(pais);
            JSFUtil.adicionaMensagemSucesso("Alterado com sucesso!");

            // Quando eu alter um novo objeto ele vai atualizar a minha tabela
            // automaticamente
            itens = dao.listar();

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());

        }

    }

    public void excluir() {

        try {

            PaisDao dao = new PaisDao();
            dao.excluir(pais);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }
}
