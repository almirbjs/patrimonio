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

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Insumo insumo = new Insumo();

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Requisicao requisicao = new Requisicao();

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

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

}
