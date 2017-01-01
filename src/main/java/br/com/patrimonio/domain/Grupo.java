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
    @NamedQuery(name = "Grupo.listar", query = "SELECT c FROM Grupo c")
    ,
		@NamedQuery(name = "Grupo.buscarPorCodigo", query = "SELECT c FROM Grupo c WHERE c.codigo = :codigo")})
@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idgrupo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer codigo;
    String grupo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

}
