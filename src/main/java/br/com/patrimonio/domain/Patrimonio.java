package br.com.patrimonio.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
    @NamedQuery(name = "Patrimonio.listar", query = "SELECT p FROM Patrimonio p")
    ,
    @NamedQuery(name = "Patrimonio.buscarPorCodigo", query = "SELECT p FROM Patrimonio p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "patrimonio")
public class Patrimonio implements Serializable {

    @Id
    @Column(name = "idpatrimonio")
    private int codigo;
    // @Temporal : � utilizado para marcar a data
    // (TemporalType.TIMESTAMP) � utilizado para marcar dia, mes, ano,
    // horas,minutos e segundos.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dtcadastro")
    private Date dataCadastro;
    // Usa-se para valor o BigDecinal e as anota��es precision=7 quer dizer que
    // tera uma precis�o de 7 numero antes da virgula e scale=2 quer dizer que
    // tera dois numero apos a virgula.
    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "numserie")
    private String numeroSerie;

    @Column(name = "anofabricacao")
    private String anoFabricacao;

    @Column(name = "notafiscal")
    private int numeroNotaFiscal;

    @Column(name = "dataemissao")
    private String dataEmissao;

    @Column(name = "obs", length = 45)
    private String observacao;

    @JoinColumn(name = "fk_patrimonio_idsetor", referencedColumnName = "idsetor", nullable = false)
    //Muitos setores tem um patrimonio
    @ManyToOne(fetch = FetchType.EAGER)
    private Setor setor = new Setor();

    @JoinColumn(name = "fk_patrimonio_idproduto", referencedColumnName = "idproduto", nullable = false)
    // Muitos produtos tem um patrimonio.
    @ManyToOne(fetch = FetchType.EAGER)
    private Produto produto = new Produto();

    @JoinColumn(name = "fk_patrimonio_idDocumentoFiscal", referencedColumnName = "idDocumentoFiscal")

    @ManyToOne(fetch = FetchType.EAGER)
    private DocumentoFiscal documentoFiscal = new DocumentoFiscal();

    @Override
    public String toString() {
        return "Patrimonio [codigo=" + getCodigo() + ", dataCadastro=" + getDataCadastro() + ", valor=" + getValor() + ", numeroSerie="
                + getNumeroSerie() + ", anoFabricacao=" + getAnoFabricacao() + ", numeroNotaFiscal=" + getNumeroNotaFiscal()
                + ", dataEmissao=" + getDataEmissao() + ", observacao=" + getObservacao()
                + ", setor=" + getSetor() + ", produto=" + getProduto() + "]";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public void setNumeroNotaFiscal(int numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the documentoFiscal
     */
    public DocumentoFiscal getDocumentoFiscal() {
        return documentoFiscal;
    }

    /**
     * @param documentoFiscal the documentoFiscal to set
     */
    public void setDocumentoFiscal(DocumentoFiscal documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
    }

}
