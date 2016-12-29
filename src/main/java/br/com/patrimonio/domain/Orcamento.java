package br.com.patrimonio.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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

@NamedQueries({
    @NamedQuery(name = "Orcamento.listar", query = "SELECT p FROM Orcamento p")
    ,
    @NamedQuery(name = "Orcamento.buscarPorCodigo", query = "SELECT p FROM Orcamento p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "Orcamento")
@SuppressWarnings("serial")
public class Orcamento implements Serializable {

    @Id
    @Column(name = "idOrcamento")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int codigo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dtOrcamento")
    Date dataOrcamento;

    @Column(precision = 7, scale = 2, nullable = false)
    BigDecimal valor;

    @Column(name = "previsao", length = 45)
    String previsaoDeEntrega;

    @Column(name = "formapagamento", length = 45)
    String FormaDePagamento;

    @Column(name = "prazopagamento", length = 45)
    String prazoParaPagamento;

    @Column(name = "tipoorcamento", length = 45)
    String tipoDeOrcamento;

    @Column(name = "obs", length = 45)
    String observacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_orcamento_idfornecedor", referencedColumnName = "idfornecedor", nullable = false)
    Fornecedor fornecedor = new Fornecedor();

    @JoinColumn(name = "fk_orcamento_idmanutencao", referencedColumnName = "idmanutencao", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Manutencao manutencao = new Manutencao();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataOrcamento() {
        return dataOrcamento;
    }

    public void setDataOrcamento(Date dataOrcamento) {
        this.dataOrcamento = dataOrcamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getPrevisaoDeEntrega() {
        return previsaoDeEntrega;
    }

    public void setPrevisaoDeEntrega(String previsaoDeEntrega) {
        this.previsaoDeEntrega = previsaoDeEntrega;
    }

    public String getFormaDePagamento() {
        return FormaDePagamento;
    }

    public void setFormaDePagamento(String FormaDePagamento) {
        this.FormaDePagamento = FormaDePagamento;
    }

    public String getPrazoParaPagamento() {
        return prazoParaPagamento;
    }

    public void setPrazoParaPagamento(String prazoParaPagamento) {
        this.prazoParaPagamento = prazoParaPagamento;
    }

    public String getTipoDeOrcamento() {
        return tipoDeOrcamento;
    }

    public void setTipoDeOrcamento(String tipoDeOrcamento) {
        this.tipoDeOrcamento = tipoDeOrcamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

}
