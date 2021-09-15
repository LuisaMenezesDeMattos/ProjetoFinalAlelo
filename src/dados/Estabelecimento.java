package dados;


/** ----------------------------------------------------------------- */
/** CLASSE ESTABELECIMENTO
 * Autor: Sandro
 * Função: Guarda os dados de um determinado estabelecimento cadastrado no sistema
 * */

public class Estabelecimento {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private  Object TipoEstabelecimento;
    private char[] codigo;
    private  String  nome;
    private  String endereco;


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR */

    public Estabelecimento(char[] codigo, String nome,Object TipoEstabelecimento , String endereco){
        this.codigo = codigo;
        this.nome = nome;
        this.TipoEstabelecimento = TipoEstabelecimento;
        this.endereco = endereco;
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    public char[] getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Object getTipoEstabelecimento() {
        return TipoEstabelecimento;
    }

    public String getEndereco() {
        return endereco;
    }

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