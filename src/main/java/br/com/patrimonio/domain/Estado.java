package br.com.patrimonio.domain;

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
    @NamedQuery(name = "Estado.listar", query = "SELECT e FROM Estado e")
    ,
		@NamedQuery(name = "Estado.buscarPorCodigoPais", query = "SELECT e FROM Estado e WHERE e.pais = :codigo")})
@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @Column(name = "idestado", length = 11)
    private int codigo;
    @Column(length = 2, unique = false, nullable = false)
    private String siglaEstado;

    @Column(length = 45)
    private String estado;
    // Muitos estados tem um pais
    // FetchType.LAZY carrega apenas os estados
    // @ManyToOne(fetch=FetchType.EAGER)carrega os estado e pais porque � uma
    // depedencia. Estava dando um erro aonde nao consegui atulizar o combox ex.
    // mudar pais . percibi que era por causa que estava usando apenas o FetchType.LAZY aonde deveria ser utilizado o fetchType.Eager.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estado_idpais", referencedColumnName = "idpais", nullable = false)
    private Pais pais = new Pais();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    @Override
    public String toString() {
        return "Estado [codigo=" + codigo + ", estado=" + estado + ", siglaEstado=" + siglaEstado + ", pais=" + pais
                + ", getCodigo()=" + getCodigo() + ", getEstado()=" + getEstado() + ", getPais()=" + getPais()
                + ", getSiglaEstado()=" + getSiglaEstado() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

}
