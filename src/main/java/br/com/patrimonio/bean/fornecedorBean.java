package br.com.patrimonio.bean;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.CidadeDao;
import br.com.patrimonio.dao.EstadoDao;
import br.com.patrimonio.dao.FornecedorDao;
import br.com.patrimonio.dao.PaisDao;
import br.com.patrimonio.domain.Cidade;
import br.com.patrimonio.domain.Estado;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.domain.Pais;
import br.com.patrimonio.util.JSFUtil;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBFornecedor") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class fornecedorBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    private ArrayList<Fornecedor> itens;
    private ArrayList<Pais> comboPais;
    private ArrayList<Estado> comboEstado;
    private Estado estado = new Estado();
    private Pais pais = new Pais();
    private ArrayList<Fornecedor> itensFiltrados;// Vai armazenar os itens
    // filtrados
    private ArrayList<Cidade> comboCidades; // Carrega o combox com o nome das
    // Cidades
    // Para armazenar o combox Pais e cidade � necessario criar uma variavel
    // temporaria para salvar a cidade no combox

    FornecedorDao dao = new FornecedorDao();
    EstadoDao estadoDao = new EstadoDao();
    private Fornecedor fornecedor = new Fornecedor();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            FornecedorDao dao = new FornecedorDao();
            itens = dao.listar();

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        fornecedor = new Fornecedor();
        try {

            PaisDao daoPais = new PaisDao();
            comboPais = daoPais.listar();

        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void salvar() {

        try {

            PaisDao daoPais = new PaisDao();
            comboPais = daoPais.listar();

            FornecedorDao dao = new FornecedorDao();
            dao.salvar(fornecedor);
            itens = dao.listar();
            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");

            // Quando salvar um novo objeto ele vai atualizar a minha tabela
            // automaticamente
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemErro("Erro ao salvar!");
        }

    }

    public void prepararEditar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            // fornecedor = new Fornecedor();
            CidadeDao daoCidade = new CidadeDao();
            comboCidades = daoCidade.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            dao = new FornecedorDao();
            dao.alterar(fornecedor);

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

            FornecedorDao dao = new FornecedorDao();
            dao.excluir(fornecedor);
            itens = dao.listar();

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    public void populaEstado() {

        comboEstado = estadoDao.listar();
        try {

            if (pais != null) {

                comboEstado = estadoDao.BuscaPorCodigoDoPais(pais.getCodigo());
                System.out.println(comboEstado);
            }
        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemErro("País nulo ");
        }

    }

    public void populaCidade() {
        CidadeDao cidadeDao = new CidadeDao();
        comboCidades = cidadeDao.listar();
        try {

            if (estado != null) {

                comboCidades = cidadeDao.BuscaPorCodigoDoEstado(estado.getCodigo());
                System.out.println(comboCidades);
            }
        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemErro("Cidade nula ");
        }

    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public ArrayList<Estado> getComboEstado() {
        return comboEstado;
    }

    public void setComboEstado(ArrayList<Estado> comboEstado) {
        this.comboEstado = comboEstado;
    }

    public ArrayList<Fornecedor> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Fornecedor> itens) {
        this.itens = itens;
    }

    public ArrayList<Pais> getComboPais() {
        return comboPais;
    }

    public void setComboPais(ArrayList<Pais> comboPais) {
        this.comboPais = comboPais;
    }

    public ArrayList<Fornecedor> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Fornecedor> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Cidade> getComboCidades() {
        return comboCidades;
    }

    public void setComboCidades(ArrayList<Cidade> comboCidades) {
        this.comboCidades = comboCidades;
    }

    public FornecedorDao getDao() {
        return dao;
    }

    public void setDao(FornecedorDao dao) {
        this.dao = dao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "fornecedorBean [itens=" + itens + ", comboPais=" + comboPais + ", comboEstado=" + comboEstado
                + ", pais=" + pais + ", itensFiltrados=" + itensFiltrados + ", comboCidades=" + comboCidades + ", dao="
                + dao + ", estadoDao=" + estadoDao + ", fornecedor=" + fornecedor + ", getPais()=" + getPais()
                + ", getComboEstado()=" + getComboEstado() + ", getItens()=" + getItens() + ", getComboPais()="
                + getComboPais() + ", getItensFiltrados()=" + getItensFiltrados() + ", getComboCidades()="
                + getComboCidades() + ", getDao()=" + getDao() + ", getFornecedor()=" + getFornecedor()
                + ", getEstadoDao()=" + getEstadoDao() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

}
