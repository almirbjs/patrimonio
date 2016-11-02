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
    @NamedQuery(name = "Setor.listar", query = "SELECT s FROM Setor s")
    ,
	@NamedQuery(name = "Setor.buscarPorCodigo", query = "SELECT s FROM Setor s WHERE s.codigo = :codigo")})
@Entity
@Table(name = "setor")
public class Setor implements Serializable {

    @Id
    @Column(name = "idsetor")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    private String setor;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "Setor [codigo=" + codigo + ", setor=" + setor + "]";
    }

}
