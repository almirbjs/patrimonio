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
    @NamedQuery(name = "ListaDeCompra.listar", query = "SELECT p FROM ListaDeCompra p")})

@Entity
@Table(name = "listaDeCompra")
public class ListaDeCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idListaDeCompra", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer codigo;

    String descricao;
    String obs;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Setor setor = new Setor();

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

}
