package br.com.patrimonio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

// Significa que � uma entidade de persistencia
@Entity
// diz que a minha unidade de persistencia esta ligada a minha tabela
// se o nome da minha tabela fosse diferente da classe java precisaria utilizar
// (name="usuario")
@Table(name = "notaFiscal")
public class NotaFiscal {
    // � usado para indentificar a chave primaria, se o nome da minha varialve
    // fosse diferente do banco deve se nomear: @Column(name = "usuario")

    // @Id indentifica que o codigo � uma coluna. (length =10) � o tamanho do
    // campo
    // @GeneratedValue(strategy = GenerationType.AUTO) gera um codigo automatico
    @Id
    @Column(name = "idNotaFiscal", length = 10)
    private int codigo;

    @Column(name = "descricaoNotaFiscal", length = 45)
    private String descricaoNotalFiscal;

    @Lob
    @Column(name = "notaFiscalImagem", length = 45, nullable = false, columnDefinition = "mediumblob")
    private byte[] notaFiscalImagem;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoNotalFiscal() {
        return descricaoNotalFiscal;
    }

    public void setDescricaoNotalFiscal(String descricaoNotalFiscal) {
        this.descricaoNotalFiscal = descricaoNotalFiscal;
    }

    public byte[] getNotaFiscalImagem() {
        return notaFiscalImagem;
    }

    public void setNotaFiscalImagem(byte[] notaFiscalImagem) {
        this.notaFiscalImagem = notaFiscalImagem;
    }

}
