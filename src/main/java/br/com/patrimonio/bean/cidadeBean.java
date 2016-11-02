package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.CidadeDao;
import br.com.patrimonio.dao.EstadoDao;
import br.com.patrimonio.domain.Cidade;
import br.com.patrimonio.domain.Estado;
import br.com.patrimonio.util.JSFUtil;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele foi criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBCidade") // Nome do meu managedBean ele foi usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class cidadeBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Cidade> itens;
    private ArrayList<Cidade> itensFiltrados;// Vai armazenar os itens filtrados
    private ArrayList<Estado> comboEstados; // Carrega o combox com o nome dos
    // Estados
    CidadeDao dao = new CidadeDao();
    private Cidade cidade = new Cidade();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    public CidadeDao getDao() {
        return dao;
    }

    public ArrayList<Estado> getComboEstados() {
        return comboEstados;
    }

    public void setComboEstados(ArrayList<Estado> comboEstados) {
        this.comboEstados = comboEstados;
    }

    public ArrayList<Cidade> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Cidade> itens) {
        this.itens = itens;
    }

    public ArrayList<Cidade> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Cidade> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public void setDao(CidadeDao dao) {
        this.dao = dao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @PostConstruct
    public void prepararPesquisa() {
        try {
            CidadeDao dao = new CidadeDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            cidade = new Cidade();

            EstadoDao dao = new EstadoDao();
            comboEstados = dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {

            CidadeDao dao = new CidadeDao();
            dao.salvar(cidade);
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
            //cidade = new Cidade();
            EstadoDao daoEstado = new EstadoDao();
            comboEstados = daoEstado.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new CidadeDao();
            dao.alterar(cidade);

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

            @SuppressWarnings("LocalVariableHidesMemberVariable")
            CidadeDao dao = new CidadeDao();
            dao.excluir(cidade);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }
}
