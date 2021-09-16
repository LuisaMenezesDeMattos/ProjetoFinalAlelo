package dados;


/** ----------------------------------------------------------------- */

import java.util.Locale;

/** CLASSE ESTABELECIMENTO
 * Autor: Sandro
 * Função: Guarda os dados de um determinado estabelecimento cadastrado no sistema
 * */

public class Estabelecimento {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */
    private TipoEstabelecimento tipo;
    private char[] codigo;
    private  String  nome;
    private  String endereco;


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR */

    public Estabelecimento(char[] codigo, String nome, TipoEstabelecimento tipo, String endereco){
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.endereco = endereco;
    }
    public Estabelecimento(String codigo, String nome, TipoEstabelecimento tipo, String endereco){
        this(codigo.substring(0,3).toCharArray(), nome, tipo, endereco);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    public char[] getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public TipoEstabelecimento getTipo(){ return this.tipo; }

    /*public void setCodigo(char[] codigo){
        char[] newCodigo = new char[0];
        this.codigo = newCodigo;
    }

    public void setNome(String newNome){
        this.endereco = newNome;
    }

    public void setTipoEstabelecimento(Object newTipoEstabelecimento){
        this.TipoEstabelecimento = newTipoEstabelecimento;
    }
    public void setEndereco(String newEndereco){
        this.endereco = newEndereco;
    }*/

}