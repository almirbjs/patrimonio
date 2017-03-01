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
    @NamedQuery(name = "Manutencao.listar", query = "SELECT p FROM Manutencao p")
    ,
    @NamedQuery(name = "Manutencao.buscarPorCodigo", query = "SELECT p FROM Manutencao p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "manutencao")
@SuppressWarnings("serial")
public class Manutencao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idmanutencao")
    private int codigo;
    // @Temporal : � utilizado para marcar a data
    // (TemporalType.TIMESTAMP) � utilizado para marcar dia, mes, ano,
    // horas,minutos e segundos.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dtcadastro")
    private Date dataCadastro;

    @Column(name = "Status", length = 45)
    private String status;

    @Column(name = "observacao", length = 45)
    private String obs;

    @JoinColumn(name = "fk_manutencao_idpatrimonio", referencedColumnName = "idpatrimonio", nullable = false)
    //Muitos setores tem um patrimonio
    @ManyToOne(fetch = FetchType.EAGER)
    private Patrimonio patrimonio = new Patrimonio();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

}
