package br.com.patrimonio.bean;

import br.com.patrimonio.dao.FornecedorDao;
import br.com.patrimonio.dao.ManutencaoDao;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.patrimonio.dao.OrcamentoDao;
import br.com.patrimonio.domain.DocumentoFiscal;
import br.com.patrimonio.domain.Fornecedor;
import br.com.patrimonio.domain.Manutencao;
import br.com.patrimonio.domain.Orcamento;
import br.com.patrimonio.util.JSFUtil;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "MBOrcamento") // Nome do meu managedBean

@ViewScoped
public class OrcamentoBean {
    // variavel que guarda o resultado de consulta (variavel de tela)

    ArrayList<Orcamento> itens;
    ArrayList<Orcamento> itensFiltrados;// Vai armazenar os itens
    ArrayList<Fornecedor> itensFornecedores;// filtrados 
    ArrayList<Fornecedor> itensManutencao;
    OrcamentoDao dao = new OrcamentoDao();
    Orcamento orcamento = new Orcamento();

    // @PostConstruct :esse metodo vai ser desenhado antes da pagina ser
    // desenhada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            OrcamentoDao dao = new OrcamentoDao();
            itens = dao.listar();
            
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionaMensagemErro(e.getMessage());
        }
    }
    
    public ArrayList<Orcamento> getItens() {
        return itens;
    }
    
    public void prepararSalvar() {
        // metodo criado para resolver o problema do objeto= null
        try {

             FornecedorDao fornecedorDao = new FornecedorDao();
         itensFornecedores = fornecedorDao.listar();
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionaMensagemErro(ex.getMessage());
        }
        
    }
    
    public void salvar() {
        
        FornecedorDao fornecedorDao = new FornecedorDao();
        try {
            
            OrcamentoDao dao = new OrcamentoDao();
            Manutencao manutencao = new Manutencao();
            ManutencaoDao manutencaoDao = new ManutencaoDao();
            orcamento.setManutencao(manutencao);
            
            dao.salvar(orcamento);
            
            JSFUtil.adicionaMensagemSucesso("Salvo com sucesso!");
            
            manutencao.setStatus("Aguardando Orcamento");
            manutencaoDao.alterar(manutencao);
            itens = dao.listar();
            itensFornecedores = fornecedorDao.listar();

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
            
            dao = new OrcamentoDao();
            dao.alterar(orcamento);
            
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
            
            OrcamentoDao dao = new OrcamentoDao();
            dao.excluir(orcamento);
            itens = dao.listar();
            
            JSFUtil.adicionaMensagemSucesso("Excluido com Sucesso.");
            
        } catch (Exception e) {
            JSFUtil.adicionaMensagemErro(e.getMessage());
            JSFUtil.adicionaMensagemSucesso("Erro ao tentar excluir ");
        }
        
    }
    
    public void fornecedorSelecionado(SelectEvent event) {
        Fornecedor fornecedor = (Fornecedor) event.getObject();
        orcamento.setFornecedor(fornecedor);
        
        FornecedorDao fornecedorDao = new FornecedorDao();
        itensFornecedores = fornecedorDao.listar();
        
    }
    
    public void setItens(ArrayList<Orcamento> itens) {
        this.itens = itens;
    }
    
    public ArrayList<Orcamento> getItensFiltrados() {
        return itensFiltrados;
    }
    
    public void setItensFiltrados(ArrayList<Orcamento> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }
    
    public ArrayList<Fornecedor> getItensFornecedores() {
        return itensFornecedores;
    }
    
    public void setItensFornecedores(ArrayList<Fornecedor> itensFornecedores) {
        this.itensFornecedores = itensFornecedores;
    }
    
    public OrcamentoDao getDao() {
        return dao;
    }
    
    public void setDao(OrcamentoDao dao) {
        this.dao = dao;
    }
    
    public Orcamento getOrcamento() {
        return orcamento;
    }
    
    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public ArrayList<Fornecedor> getItensManutencao() {
        return itensManutencao;
    }

    public void setItensManutencao(ArrayList<Fornecedor> itensManutencao) {
        this.itensManutencao = itensManutencao;
    }
    
    
}
