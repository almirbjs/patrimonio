package br.com.patrimonio.bean;

import br.com.patrimonio.dao.FornecedorDao;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.ManutencaoDao;
import br.com.patrimonio.dao.OrcamentoDao;
import br.com.patrimonio.dao.PatrimonioDao;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.domain.Manutencao;
import br.com.patrimonio.domain.Orcamento;
import br.com.patrimonio.domain.Patrimonio;
import br.com.patrimonio.util.JSFUtil;
import org.primefaces.event.SelectEvent;

//ele vai estar por tras de uma interface grafica
// tipos de escopo request:(mais leve) a cada click ele e gerado (instanciado); 
//viewscope(� recomendado pelo prime faces):so existe enquanto a tela estiver aberta ex.: tela do fabricante aberta.
//SessionScope : Ele � criando quando o servidor for iniciado e finalizado quando o servidor e desligado.
@ManagedBean(name = "MBManutencao") // Nome do meu managedBean ele � usado para
// procurar o xhtml.
// Se der esse erro no console :java.lang.IllegalStateException: Cannot create a
// session after the response has been committed baixe o cdi-api-1.0.jar
@ViewScoped
public class ManutencaoBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    ArrayList<Manutencao> itens;
    ArrayList<Manutencao> itensFiltrados;// Vai armazenar os itens filtrados
    ArrayList<Patrimonio> itensPatrimonio; // Carrega o combox com o nome dos
    ArrayList<Fornecedor> itensFornecedor;
    ArrayList<Fornecedor> itensOrcamento;
    ManutencaoDao dao = new ManutencaoDao();
    Manutencao manutencao = new Manutencao();
    Patrimonio patrimonio = new Patrimonio();
    Orcamento orcamento = new Orcamento();
    Fornecedor fornecedor = new Fornecedor();

    @PostConstruct
    public void prepararPesquisa() {
        try {
            ManutencaoDao dao = new ManutencaoDao();
            setItens(dao.listar());

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }

    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {
            setManutencao(new Manutencao());
            PatrimonioDao dao = new PatrimonioDao();
            setItensPatrimonio(dao.listar());
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }
    
     

    public void salvar() {

        try {
            getPatrimonio().getCodigo();
            ManutencaoDao dao = new ManutencaoDao();
            getManutencao().setStatus("Pendente");
            dao.salvar(getManutencao());
            setPatrimonio(getManutencao().getPatrimonio());
            PatrimonioDao patrimonioDao = new PatrimonioDao();
            getPatrimonio().setFuncionando("N");

            patrimonioDao.alterar(getPatrimonio());

            setItens(dao.listar());
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
            //manutencao = new Manutencao();
            PatrimonioDao daoPatrimonio = new PatrimonioDao();
            setItensPatrimonio(daoPatrimonio.listar());
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }

    }

    public void editar() {

        try {

            setDao(new ManutencaoDao());
            getDao().alterar(getManutencao());

            setItens(getDao().listar());
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

            ManutencaoDao dao = new ManutencaoDao();
            dao.excluir(getManutencao());
            setItens(dao.listar());

            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");

            setPatrimonio(getManutencao().getPatrimonio());
            PatrimonioDao patrimonioDao = new PatrimonioDao();
            getPatrimonio().setFuncionando("S");

            patrimonioDao.alterar(getPatrimonio());

        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }

    }

    public void patrimonioSelecionado(SelectEvent event) {
        Patrimonio p = (Patrimonio) event.getObject();
        getManutencao().setPatrimonio(p);
        PatrimonioDao patrimonioDao = new PatrimonioDao();
        setItensPatrimonio(patrimonioDao.listar());

    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public ArrayList<Patrimonio> listaFuncionando() {
        setItensPatrimonio(null);

        {
            if (getItensPatrimonio() == null) {

                PatrimonioDao patrimonioDao = new PatrimonioDao();
                setItensPatrimonio(patrimonioDao.listarFuncionando());

            }

            return getItensPatrimonio();
        }
    }

    public ArrayList<Manutencao> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Manutencao> itens) {
        this.itens = itens;
    }

    public ArrayList<Manutencao> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Manutencao> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public ArrayList<Patrimonio> getItensPatrimonio() {
        return itensPatrimonio;
    }

    public void setItensPatrimonio(ArrayList<Patrimonio> itensPatrimonio) {
        this.itensPatrimonio = itensPatrimonio;
    }

    public ManutencaoDao getDao() {
        return dao;
    }

    public void setDao(ManutencaoDao dao) {
        this.dao = dao;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public ArrayList<Fornecedor> getItensFornecedor() {
        return itensFornecedor;
    }

    public void setItensFornecedor(ArrayList<Fornecedor> itensFornecedor) {
        this.itensFornecedor = itensFornecedor;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public ArrayList<Fornecedor> getItensOrcamento() {
        return itensOrcamento;
    }

    public void setItensOrcamento(ArrayList<Fornecedor> itensOrcamento) {
        this.itensOrcamento = itensOrcamento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
