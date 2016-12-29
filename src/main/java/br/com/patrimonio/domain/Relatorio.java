package br.com.patrimonio.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;


@Entity
// diz que a minha unidade de persistencia esta ligada a minha tabela
// se o nome da minha tabela fosse diferente da classe java precisaria utilizar
// (name="usuario")
public class Relatorio {

    @Id
   
     Long ID;
    //   usado para indentificar a chave primaria, se o nome da minha varialve
    // fosse diferente do banco deve se nomear: @Column(name = "usuario")

    // @Id indentifica que o codigo   uma coluna. (length =10)   o tamanho do
    // campo
    // @GeneratedValue(strategy = GenerationType.AUTO) gera um codigo automatico
   
    @Transient
     String tipo;

    @Transient
    @Temporal(javax.persistence.TemporalType.DATE)
     Date data;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    
}