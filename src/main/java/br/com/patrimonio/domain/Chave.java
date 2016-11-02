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
    @NamedQuery(name = "Chave.listar", query = "SELECT c FROM Chave c")
    ,
    @NamedQuery(name = "Chave.buscarPorCodigo", query = "SELECT c FROM Chave c WHERE c.codigo = :codigo")})
@Entity
@Table(name = "chave")
public class Chave implements Serializable {

    @Id
    @Column(name = "idchave")
    private int codigo;

    private String chave;

    private int quantidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_chave_idsetor", referencedColumnName = "idsetor", nullable = false)
    private Setor setor = new Setor();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "Chave [codigo=" + getCodigo() + ", chave=" + getChave() + ",quantidade=" + getQuantidade() + "]";
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
