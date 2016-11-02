package br.com.patrimonio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "Pais.listar", query = "SELECT p FROM Pais p")
    ,
	@NamedQuery(name = "Pais.buscarPorCodigo", query = "SELECT p FROM Pais p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "pais")
public class Pais {

    @Id
    @Column(name = "idpais")
    private int codigo;
    private String pais;
    @Column(length = 3)
    private String sigla;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Pais [codigo=" + codigo + ", pais=" + pais + ", sigla=" + sigla + "]";
    }

}
