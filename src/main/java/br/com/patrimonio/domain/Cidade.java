package br.com.patrimonio.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "Cidade.listar", query = "SELECT c FROM Cidade c")
    ,
		@NamedQuery(name = "Cidade.buscarPorCodigo", query = "SELECT c FROM Cidade c WHERE c.codigo = :codigo")
    ,
		@NamedQuery(name = "Cidade.buscarPorCodigoDoEstado", query = "SELECT c FROM Cidade c WHERE c.estado = :codigo")

})
@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

    @Id
    @Column(name = "idcidade", length = 11)
    private int codigo;
    @Column(length = 45, nullable = false)
    private String cidade;
    // Muitas cidades tem um estado
    // fetchType.EAGER busca estado e cidades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_idcidade_estado", referencedColumnName = "idestado", nullable = false)
    private Estado estado = new Estado();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cidade [codigo=" + codigo + ", cidade=" + cidade + ", estado=" + estado + ", getCodigo()=" + getCodigo()
                + ", getCidade()=" + getCidade() + ", getEstado()=" + getEstado() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
