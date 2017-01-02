package br.com.patrimonio.domain;

import java.io.Serializable;
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
    @NamedQuery(name = "Insumo.listar", query = "SELECT p FROM Insumo p")
    ,
    @NamedQuery(name = "Insumo.buscarPorCodigo", query = "SELECT p FROM Insumo p WHERE p.codigo = :codigo")})
@Entity
@Table(name = "insumo")
@SuppressWarnings("serial")
public class Insumo implements Serializable {

    @Id
    @Column(name = "idinsumo", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer codigo;
    @Column(length = 45)
    String insumo;
    Integer qtdMinBaixaTemp;
    Integer qtdMaxBaixaTemp;
    Integer qtdMinAltaTemp;
    Integer qtdMaxAltaTemp;
    @Column(length = 45)
    String obs;
    @JoinColumn(name = "fk_itemmarca_idinsumo", referencedColumnName = "iditemmarca")
    @ManyToOne(fetch = FetchType.EAGER)
    ItemMarca itemMarca= new ItemMarca();

    @JoinColumn(name = "fk_grupo_idinsumo", referencedColumnName = "idgrupo")
    @ManyToOne(fetch = FetchType.EAGER)
    Grupo grupo = new Grupo();

    @JoinColumn(name = "fk_unidade_idinsumo", referencedColumnName = "idunidade")
    @ManyToOne(fetch = FetchType.EAGER)
    Unidade unidade = new Unidade();

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public Integer getQtdMinBaixaTemp() {
        return qtdMinBaixaTemp;
    }

    public void setQtdMinBaixaTemp(Integer qtdMinBaixaTemp) {
        this.qtdMinBaixaTemp = qtdMinBaixaTemp;
    }

    public Integer getQtdMaxBaixaTemp() {
        return qtdMaxBaixaTemp;
    }

    public void setQtdMaxBaixaTemp(Integer qtdMaxBaixaTemp) {
        this.qtdMaxBaixaTemp = qtdMaxBaixaTemp;
    }

    public Integer getQtdMinAltaTemp() {
        return qtdMinAltaTemp;
    }

    public void setQtdMinAltaTemp(Integer qtdMinAltaTemp) {
        this.qtdMinAltaTemp = qtdMinAltaTemp;
    }

    public Integer getQtdMaxAltaTemp() {
        return qtdMaxAltaTemp;
    }

    public void setQtdMaxAltaTemp(Integer qtdMaxAltaTemp) {
        this.qtdMaxAltaTemp = qtdMaxAltaTemp;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

      public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public ItemMarca getItemMarca() {
        return itemMarca;
    }

    public void setItemMarca(ItemMarca itemMarca) {
        this.itemMarca = itemMarca;
    }

}
