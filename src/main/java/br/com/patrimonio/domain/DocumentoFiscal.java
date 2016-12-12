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
import javax.persistence.Transient;

//Criando uma consulta Hql. 
//@NamedQueries :   o nome das queries 
//name = "Usuario.listar" : Apelido ou nome da query 
//query = "Select usuario from Usuario" : Seleciona o usuario do banco de dados que esta em minusculo e aonde Usuario   Maisculo no Java
@NamedQueries({
    @NamedQuery(name = "DocumentoFiscal.listar", query = "SELECT d FROM DocumentoFiscal d")
    ,
    @NamedQuery(name = "DocumentoFiscal.buscarPorCodigo", query = "SELECT u FROM DocumentoFiscal u WHERE u.codigo = :codigo")})

// Significa que   uma entidade de persistencia
@Entity
// diz que a minha unidade de persistencia esta ligada a minha tabela
// se o nome da minha tabela fosse diferente da classe java precisaria utilizar
// (name="usuario")
@Table(name = "DocumentoFiscal")
public class DocumentoFiscal implements Serializable {
    //   usado para indentificar a chave primaria, se o nome da minha varialve
    // fosse diferente do banco deve se nomear: @Column(name = "usuario")

    // @Id indentifica que o codigo   uma coluna. (length =10)   o tamanho do
    // campo
    // @GeneratedValue(strategy = GenerationType.AUTO) gera um codigo automatico
    @Id
    @Column(name = "idDocumentoFiscal")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Column(name = "NumeroDocumentoFiscal", nullable = true)
    private long NumeroDocumentoFiscal;

    @Transient
    private String caminhoTemporario;

    @Column(name = "dataEmissao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmissao;

    @Column(name = "chaveDeAcesso")
    private long chaveDeAcesso;

    @Column(name = "tipoDocFiscal")
    private String tipoDocFiscal;
   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_documentoFiscal_idfornecedor", referencedColumnName = "idfornecedor", nullable = false)
    private Fornecedor fornecedor = new Fornecedor();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public long getNumeroDocumentoFiscal() {
        return NumeroDocumentoFiscal;
    }

    public void setNumeroDocumentoFiscal(long NumeroDocumentoFiscal) {
        this.NumeroDocumentoFiscal = NumeroDocumentoFiscal;
    }

    public String getCaminhoTemporario() {
        return caminhoTemporario;
    }

    public void setCaminhoTemporario(String caminhoTemporario) {
        this.caminhoTemporario = caminhoTemporario;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public long getChaveDeAcesso() {
        return chaveDeAcesso;
    }

    public void setChaveDeAcesso(long chaveDeAcesso) {
        this.chaveDeAcesso = chaveDeAcesso;
    }

    public String getTipoDocFiscal() {
        return tipoDocFiscal;
    }

    public void setTipoDocFiscal(String tipoDocFiscal) {
        this.tipoDocFiscal = tipoDocFiscal;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    
}
