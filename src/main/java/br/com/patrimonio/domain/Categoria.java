package br.com.patrimonio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "Categoria.listar", query = "SELECT c FROM Categoria c")
    ,
		@NamedQuery(name = "Categoria.buscarPorCodigo", query = "SELECT c FROM Categoria c WHERE c.codigo = :codigo")})
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @Column(name = "idcategoria")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    private String categoria;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Categoria [codigo=" + codigo + ", categoria=" + categoria + "]";
    }

}
