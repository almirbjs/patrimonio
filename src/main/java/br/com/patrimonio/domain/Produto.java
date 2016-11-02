package br.com.patrimonio.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@NamedQueries({
    @NamedQuery(name = "Produto.listar", query = "SELECT p FROM Produto p")
    ,
    @NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT p FROM Produto p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @Column(name = "idproduto", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    @Column(length = 45)
    private String produto;

    private String ExisteImagem;

    @Column(length = 45)
    private String modelo;
    @Column(length = 45)
    private String obs;
    @Column(length = 45)
    private String dataFabricacao;
    @Transient
    private String caminhoTemporarioProduto;

    @JoinColumn(name = "fk_produto_idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne(fetch = FetchType.EAGER)
    private Fornecedor fornecedor = new Fornecedor();
    @JoinColumn(name = "fk_produto_idcategoria", referencedColumnName = "idcategoria")
    //Muitas categoria tem um patrimonio
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria = new Categoria();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getCaminhoTemporarioProduto() {
        return caminhoTemporarioProduto;
    }

    public void setCaminhoTemporarioProduto(String caminhoTemporarioProduto) {
        this.caminhoTemporarioProduto = caminhoTemporarioProduto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getExisteImagem() {
        return ExisteImagem;
    }

    public void setExisteImagem(String ExisteImagem) {
        this.ExisteImagem = ExisteImagem;
    }

}
