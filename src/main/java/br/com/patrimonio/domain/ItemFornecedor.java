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
    @NamedQuery(name = "ItemFornecedor.listar", query = "SELECT p FROM ItemFornecedor p")
    ,
    @NamedQuery(name = "ItemFornecedor.buscarPorCodigo", query = "SELECT p FROM ItemFornecedor p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "ItemFornecedor")
public class ItemFornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "iditeminsumo", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer codigo;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Fornecedor fornecedor = new Fornecedor();
    
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    ListaDeCompra listaDeCompra = new  ListaDeCompra();
    

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ListaDeCompra getListaDeCompra() {
        return listaDeCompra;
    }

    public void setListaDeCompra(ListaDeCompra listaDeCompra) {
        this.listaDeCompra = listaDeCompra;
    }

    
    
}
