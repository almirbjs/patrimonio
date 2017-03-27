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
    @NamedQuery(name = "ItemInsumo.listar", query = "SELECT p FROM ItemInsumo p")
    ,
    @NamedQuery(name = "ItemInsumo.buscarPorCodigo", query = "SELECT p FROM ItemInsumo p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "itemInsumo")
@SuppressWarnings("serial")
public class ItemInsumo implements Serializable {

    @Id
    @Column(name = "iditeminsumo", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer codigo;
        
    int quantMinAltaTemp;
    int quantMaxAltaTemp;
    int quantMinBaixaTemp;
    int quantMaxBaixaTemp;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Insumo insumo = new Insumo();
    
     @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    ListaDeCompra listaDeCompra = new  ListaDeCompra();

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

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public ListaDeCompra getListaDeCompra() {
        return listaDeCompra;
    }

    public void setListaDeCompra(ListaDeCompra listaDeCompra) {
        this.listaDeCompra = listaDeCompra;
    }

  

}
