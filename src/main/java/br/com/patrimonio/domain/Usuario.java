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
import javax.persistence.TemporalType;

//Criando uma consulta Hql. 
//@NamedQueries : � o nome das queries 
//name = "Usuario.listar" : Apelido ou nome da query 
//query = "Select usuario from Usuario" : Seleciona o usuario do banco de dados que esta em minusculo e aonde Usuario � Maisculo no Java
@NamedQueries({
    @NamedQuery(name = "Usuario.listar", query = "SELECT u FROM Usuario u")
    ,
		@NamedQuery(name = "Usuario.buscarPorCodigo", query = "SELECT u FROM Usuario u WHERE u.codigo = :codigo")})

// Significa que � uma entidade de persistencia
@Entity
// diz que a minha unidade de persistencia esta ligada a minha tabela
// se o nome da minha tabela fosse diferente da classe java precisaria utilizar
// (name="usuario")
@Table(name = "usuario")
public class Usuario implements Serializable {
    // � usado para indentificar a chave primaria, se o nome da minha varialve
    // fosse diferente do banco deve se nomear: @Column(name = "usuario")

    // @Id indentifica que o codigo � uma coluna. (length =10) � o tamanho do
    // campo
    // @GeneratedValue(strategy = GenerationType.AUTO) gera um codigo automatico
    @Id
    @Column(name = "idusuario", length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Column(name = "usuario", length = 45)
    private String usuario;

    // nullable=false Coluna n�o pode ser nula.
    @Column(name = "senha", length = 45, nullable = false)
    private String senha;
    // unique=true � usado para n�o repetir o cpf porque o cpf � unico.
    @Column(name = "cpf", length = 20, nullable = false, unique = true)
    private String cpf;
    // unique=true � usado para n�o repetir o rg porque o rg � unico.
    @Column(name = "rg", length = 45, nullable = false, unique = true)
    private String rg;
    @Temporal(value = TemporalType.DATE)
    @Column(name = "dataNasc", length = 45, nullable = false)
    private Date dataNasc;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    // --Criando a chave estrangeira(FK)----
    // usa-se FetchType.EAGER quando eu carregar os usuarios juntos como os
    // privilegios,j� no FetchType.LAZY ele s� carrega os usuarios
    // Para indeteficar qual � a chave estrangeira dentro da tabela � utilezado
    // o @joinColunn
    // name informe qual � o nome da fk que ser� exibido na tabela
    // referencedColumnName="idprivilegio informa a pk da tabela pai, tem que
    // ser igual sen�o da erro
    // Muitos Usuarios tem um privelegio
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_usuario_idprivilegio", referencedColumnName = "idprivilegio", nullable = false)
    private Privilegio privilegio = new Privilegio();

    public Privilegio getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(Privilegio privilegio) {
        this.privilegio = privilegio;
    }

    // get pega a variavel
    public int getCodigo() {
        return codigo;
    }

    // seta a variavel
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Para criar o metodo toString(): Click com o bot�o direito do mouse e
    // selecione source e depois click em generate toString() e so click em
    // avancar.
    // O toString()� utilizado para ler uma lista
    @Override
    public String toString() {
        return "Usuario [codigo=" + codigo + ", usuario=" + usuario + ", senha=" + senha + ", cpf=" + cpf + ", rg=" + rg
                + ", dataNasc=" + dataNasc + ", email=" + email + ", privilegio=" + privilegio + "]";
    }

}
