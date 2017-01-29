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

@NamedQueries({
    @NamedQuery(name = "Requisicao.listar", query = "SELECT c FROM Requisicao c")
    ,
		@NamedQuery(name = "Requisicao.buscarPorCodigo", query = "SELECT c FROM Requisicao c WHERE c.codigo = :codigo")})
@Entity
@Table(name = "requisicao")
@SuppressWarnings({"serial", "ValidAttributes"})
public class Requisicao implements Serializable {

    @Id
    @Column(name = "idrequisicao")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long codigo;
    String requisicao;
    String situacao;
    Date dataEmissao;
    Date dataComfirmacao;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Insumo insumo = new Insumo();

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Usuario usuario = new Usuario();

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(String requisicao) {
        this.requisicao = requisicao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataComfirmacao() {
        return dataComfirmacao;
    }

    public void setDataComfirmacao(Date dataComfirmacao) {
        this.dataComfirmacao = dataComfirmacao;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
