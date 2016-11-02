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
    @NamedQuery(name = "Fornecedor.listar", query = "SELECT f FROM Fornecedor f")
    ,
		@NamedQuery(name = "Fornecedor.buscarPorCodigo", query = "SELECT f FROM Fornecedor f WHERE f.codigo = :codigo")})
@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idfornecedor", length = 11)
    private int codigo;
    @Column(name = "cnpjcpf", length = 20, nullable = false, unique = false)
    private String cnpjCpf;
    @Column(name = "pessoa", nullable = false)
    private String pessoa;
    @Column(name = "inscricaoestadualrg", length = 15, nullable = false, unique = false)
    private String inscricaoEstadual;
    @Column(name = "nomefantasia", length = 45)
    private String nomeFantasia;
    @Column(name = "razaosocialnome", length = 45, nullable = false)
    private String razaoSocialNome;
    @Column(length = 15)
    private String telefone;
    @Column(length = 15)
    private String celular;
    @Column(length = 15)
    private String contato;
    @Column(length = 15)
    private String cep;
    @Column(length = 15)
    private String bairro;
    @Column(length = 45)
    private String rua;
    @Column(length = 15)
    private String numero;
    @Column(length = 45)
    private String complemento;
    @Column(length = 45)
    private String email;
    @Column(length = 45)
    private String site;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fornecedor_idcidade", referencedColumnName = "idcidade", nullable = false)
    private Cidade cidade = new Cidade();

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocialNome() {
        return razaoSocialNome;
    }

    public void setRazaoSocialNome(String razaoSocialNome) {
        this.razaoSocialNome = razaoSocialNome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Fornecedor [codigo=" + codigo + ", cnpjCpf=" + cnpjCpf + ", pessoa=" + pessoa + ", inscricaoEstadual="
                + inscricaoEstadual + ", nomeFantasia=" + nomeFantasia + ", razaoSocialNome=" + razaoSocialNome
                + ", telefone=" + telefone + ", celular=" + celular + ", contato=" + contato + ", cep=" + cep
                + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento
                + ", email=" + email + ", site=" + site + ", cidade=" + cidade + ", getCidade()=" + getCidade()
                + ", getCodigo()=" + getCodigo() + ", getBairro()=" + getBairro() + ", getCnpjCpf()=" + getCnpjCpf()
                + ", getInscricaoEstadual()=" + getInscricaoEstadual() + ", getNomeFantasia()=" + getNomeFantasia()
                + ", getRazaoSocialNome()=" + getRazaoSocialNome() + ", getTelefone()=" + getTelefone()
                + ", getCelular()=" + getCelular() + ", getContato()=" + getContato() + ", getEmail()=" + getEmail()
                + ", getSite()=" + getSite() + ", getCep()=" + getCep() + ", getRua()=" + getRua() + ", getNumero()="
                + getNumero() + ", getComplemento()=" + getComplemento() + ", getPessoa()=" + getPessoa()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }

}
