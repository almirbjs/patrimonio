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

@NamedQueries({
    @NamedQuery(name = "ListaDeCompra.listar",query = "SELECT p FROM ListaDeCompra p")})

@Entity
@Table(name = "listaDeCompra")
public class ListaDeCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @Column(name = "idListaDeCompra", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer codigo;

    int quantMinAltaTemp;
    int quantMaxAltaTemp;
    int quantMinBaixaTemp;
    int quantMaxBaixaTemp;

    String descricao;
    String obs;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    ItemInsumo itemInsumo = new ItemInsumo();

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    ItemFornecedor itemFornecedor = new ItemFornecedor();

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Setor setor = new Setor();

    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getQuantMinAltaTemp() {
        return quantMinAltaTemp;
    }

    public void setQuantMinAltaTemp(int quantMinAltaTemp) {
        this.quantMinAltaTemp = quantMinAltaTemp;
    }

    public int getQuantMaxAltaTemp() {
        return quantMaxAltaTemp;
    }

    public void setQuantMaxAltaTemp(int quantMaxAltaTemp) {
        this.quantMaxAltaTemp = quantMaxAltaTemp;
    }

    public int getQuantMinBaixaTemp() {
        return quantMinBaixaTemp;
    }

    public void setQuantMinBaixaTemp(int quantMinBaixaTemp) {
        this.quantMinBaixaTemp = quantMinBaixaTemp;
    }

    public int getQuantMaxBaixaTemp() {
        return quantMaxBaixaTemp;
    }

    public void setQuantMaxBaixaTemp(int quantMaxBaixaTemp) {
        this.quantMaxBaixaTemp = quantMaxBaixaTemp;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public ItemInsumo getItemInsumo() {
        return itemInsumo;
    }

    public void setItemInsumo(ItemInsumo itemInsumo) {
        this.itemInsumo = itemInsumo;
    }

    public ItemFornecedor getItemFornecedor() {
        return itemFornecedor;
    }

    public void setItemFornecedor(ItemFornecedor itemFornecedor) {
        this.itemFornecedor = itemFornecedor;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

}
