package br.com.patrimonio.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "Unidade.listar", query = "SELECT c FROM Unidade c")
    ,
		@NamedQuery(name = "Unidade.buscarPorCodigo", query = "SELECT c FROM Unidade c WHERE c.codigo = :codigo")})
@Entity
@Table(name = "unidade")
public class Unidade implements Serializable {

    @Id
    @Column(name = "idunidade")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer codigo;

    String unidade;
    String sigla;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
