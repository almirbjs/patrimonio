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
    @NamedQuery(name = "Assistencia.listar", query = "SELECT p FROM Assistencia p")
    ,
    @NamedQuery(name = "Assistencia.buscarPorCodigo", query = "SELECT p FROM Assistencia p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "Assistencia")
@SuppressWarnings("serial")
public class Assistencia implements Serializable {

    @Id
    @Column(name = "idAssistencia")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dtchegada")
    private Date dataChegada;

    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "obs", length = 45)
    private String observacao;

    @JoinColumn(name = "fk_assistencia_idorcamento", referencedColumnName = "idorcamento", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Orcamento orcamento = new Orcamento();

    @JoinColumn(name = "fk_assistencia_iddocumento", referencedColumnName = "iddocumentofiscal", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private DocumentoFiscal documentoFiscal = new DocumentoFiscal();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public DocumentoFiscal getDocumentoFiscal() {
        return documentoFiscal;
    }

    public void setDocumentoFiscal(DocumentoFiscal documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
    }

}
