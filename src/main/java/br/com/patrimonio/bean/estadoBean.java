package br.com.patrimonio.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.patrimonio.dao.EstadoDao;
import br.com.patrimonio.dao.PaisDao;
import br.com.patrimonio.domain.Estado;
import br.com.patrimonio.domain.Pais;
import br.com.patrimonio.util.JSFUtil;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBEstado") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class estadoBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Estado> itens;
    private ArrayList<Estado> itensFiltrados;// Vai armazenar os itens filtrados
    private ArrayList<Pais> comboPaises; // Carrega o combox com o nome dos
    // paises
    EstadoDao dao = new EstadoDao();
    private Estado estado = new Estado();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    public EstadoDao getDao() {
        return dao;
    }

    public ArrayList<Pais> getComboPaises() {
        return comboPaises;
    }

    public void setComboPaises(ArrayList<Pais> comboPaises) {
        this.comboPaises = comboPaises;
    }

    public ArrayList<Estado> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Estado> itens) {
        this.itens = itens;
    }

    public ArrayList<Estado> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Estado> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public void setDao(EstadoDao dao) {
        this.dao = dao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @PostConstruct
    public void prepararPesquisa() {
        try {
            EstadoDao dao = new EstadoDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            estado = new Estado();
            PaisDao dao = new PaisDao();
            comboPaises = dao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {
            EstadoDao dao = new EstadoDao();
            dao.salvar(estado);
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
            //estado = new Estado();
            PaisDao daoPais = new PaisDao();
            comboPaises = daoPais.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new EstadoDao();
            dao.alterar(estado);

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

            EstadoDao dao = new EstadoDao();
            dao.excluir(estado);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }
}
