package br.com.patrimonio.bean;

import br.com.patrimonio.dao.AssistenciaDao;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.domain.Assistencia;
import br.com.patrimonio.util.JSFUtil;

@ManagedBean(name = "MBAssistencia") // Nome do meu managedBean ele � usado para

@ViewScoped
public class AssistenciaBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Assistencia> itens;
    private ArrayList<Assistencia> itensFiltrados;// Vai armazenar os itens
    // filtrados

    AssistenciaDao dao = new AssistenciaDao();
    private Assistencia assistencia = new Assistencia();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            AssistenciaDao dao = new AssistenciaDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public ArrayList<Assistencia> getItens() {
        return itens;
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            assistencia= new Assistencia();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {
            AssistenciaDao dao = new AssistenciaDao();
            dao.salvar(assistencia);
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
            // categoria = new Categoria();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new AssistenciaDao();
            dao.alterar(assistencia);

            itens = dao.listar();
            JSFUtil.adicionaMensagemSucesso("Alterado com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }

    }

    public void excluir() {

        try {

            AssistenciaDao dao = new AssistenciaDao();
            dao.excluir(assistencia);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }
}
