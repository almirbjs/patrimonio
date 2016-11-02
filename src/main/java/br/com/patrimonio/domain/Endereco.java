package br.com.patrimonio.domain;

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

@NamedQueries({
    @NamedQuery(name = "Endereco.listar", query = "SELECT e FROM Endereco e")
    ,
	@NamedQuery(name = "Endereco.buscarPorCodigo", query = "SELECT e FROM Endereco e WHERE e.codigo = :codigo")})
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @Column(name = "idendereco", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    @Column(length = 45)
    private String rua;
    @Column(name = "ceprua")
    private String cepRua;
    @Column(length = 6)
    private int numero;
    @Column(length = 45, nullable = false)
    private String bairro;
    @Column(length = 45)
    private String complemento;

    @JoinColumn(name = "pk_endereco_idcidade", referencedColumnName = "idcidade", nullable = false)
    //Muitos endereco tem uma cidade
    @ManyToOne(fetch = FetchType.LAZY)
    private Cidade cidade;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCepRua() {
        return cepRua;
    }

    public void setCepRua(String cepRua) {
        this.cepRua = cepRua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco [codigo=" + codigo + ", rua=" + rua + ", cepRua=" + cepRua + ", numero=" + numero + ", bairro="
                + bairro + ", complemento=" + complemento + ", cidade=" + cidade + "]";
    }

}
