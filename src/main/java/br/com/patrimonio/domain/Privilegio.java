package br.com.patrimonio.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name = "Privilegio.listar", query = "SELECT p FROM Privilegio p")
    ,
		@NamedQuery(name = "Privilegio.buscaPorCodigo", query = "SELECT p FROM Privilegio p WHERE p.codigo =:codigo")})
@Entity
public class Privilegio implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "idprivilegio")
    private int codigo;

    @Column(name = "privilegio", length = 45, nullable = false)
    private String privilegio;
    // Crei esta string para verificar se o privilegio esta ativo ou n�o.
    @Column(length = 11)
    private String ativo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Privilegio [codigo=" + codigo + ", privilegio=" + privilegio + ", ativo=" + ativo + "]";
    }

}
