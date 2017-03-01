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
    @NamedQuery(name = "ItemMarca.listar", query = "SELECT p FROM ItemMarca p")
    ,
    @NamedQuery(name = "ItemMarca.buscarPorCodigoInsumo", query = "  SELECT i FROM ItemMarca i WHERE i.insumo= :codigo")})
@Entity
@Table(name = "itemmarca")
@SuppressWarnings("serial")
public class ItemMarca implements Serializable {

    @Id
    @Column(name = "iditemmarca", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer codigo;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Marca marca = new Marca();

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Insumo insumo = new Insumo();

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

}
