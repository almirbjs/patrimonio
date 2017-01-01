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
    @NamedQuery(name = "Marca.listar", query = "SELECT c FROM Marca c")
    ,
		@NamedQuery(name = "Marca.buscarPorCodigo", query = "SELECT c FROM Marca c WHERE c.codigo = :codigo")})
@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @Column(name = "idmarca")
    @GeneratedValue(strategy = GenerationType.AUTO)
    
     int codigo;
    String marca;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

   

}
