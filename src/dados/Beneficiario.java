package dados;

import java.util.ArrayList;
import java.util.Objects;

/** CLASSE BENEFICIÁRIO
 * Autor: Rafael */

public class Beneficiario {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    /*private final String nome;
    // o programa presume que não há dois beneficiários com o mesmo nome

    private int[] senha = new int[6];
    // a senha deve ser, obrigatoriamente, 6 dígitos numéricos

    private ArrayList<ICartaoBeneficio> listaCartoes;
    // quando o beneficiário for criado (no construtor), devem ser criados
    // juntamente com ele os seus cartões*/

    public String nome;
    private int[] senha = new int[6];
    private String nomeChecar;
    private int[] senhaChecar;
    private ArrayList<ICartaoBeneficio> listaCartoes;

    // quando o beneficiário for criado (no construtor), devem ser criados
    // juntamente com ele os seus cartões


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR */

    /*public Beneficiario(String _nome){
        this.nome = _nome; // obrigatório pois o nome é do tipo final
        // falta incluir a senha e os cartões no construtor
    }*/

    public Beneficiario(String nome, int[] senha){
        this.nome = nome;
        this.senha = senha;
        this.listaCartoes = listaCartoes;
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    /** Método que checa os dados deste beneficiário */
    public boolean checarDadosLogin(String nomeChecar, int[] senhaChecar){

        /*
        // recebe um nome e uma senha, e retorna true caso sejam iguais aos nome e senha
        // deste beneficiário, ou false caso não sejam

        return true; // retorno fictício
        */

        Objects.equals(this.nomeChecar, nome);
        Objects.equals(this.senhaChecar, senha);

        return true;
    }

    /** Método que retorna os dados deste beneficiário como String */
    @Override
    public String toString(){

        /*
        // retorna os dados do beneficiário:
        //   > nome
        //   > saldos dos cartões
        // de forma amigável para leitura

        // (NÃO retorna as senhas)

        return ""; // retorno fictício
         */

        return "Beneficiario{" +
                "nome='" + nome + '\'' +
                ", listaCartoes=" + listaCartoes +
                '}';

    }

    /** Método que tenta usar o cartão, e retorna true caso dê certo, ou false caso não */
    public boolean tentarPassarCartao(TipoCartaoBeneficio tipoCartao, Double valor, Estabelecimento estabelecimento){

        // recebe o tipo do cartão, o valor a ser gasto, e o estabelecimento.
        // então busca o cartão do tipo passado na listaCartoes,
        // e retorna o seu método tentarPagamento(estabelecimento, valor);

        return true; /* retorno fictício */

    }

    /** Método que recebe um tipo de cartão, e retorna os dados do mesmo */
    public String extratoCartao(TipoCartaoBeneficio tipo){

        // recebe o tipo do cartão, busca o cartão do tipo passado em listaCartoes,
        // e retorna o seu método extrato();

        return ""; /* retorno fictício */

    }

    /** Método que retorna o nome do beneficiário */
    public String getNome(){
        return ""; /* retorno fictício */
    }

    /** Método que retorna o saldo de um dado cartão */
    public String getSaldoCartao(TipoCartaoBeneficio tipoCartao){
        return "R$00,00"; /* retorno fictício */
    }




    /** Método que retorna se uma dada senha corresponde ao um dado cartão */
    public boolean checarSenhaCartao(int[] senha, TipoCartaoBeneficio tipoCartao){

        // recebe o tipo do cartão, busca o cartão do tipo passado em listaCartoes,
        // e testa se a senha passada é a deste cartão

        return true; /* retorno fictício */
    }





    /** Método que retorna os dados de cada cartão */
    public String dadosCartoes(){

        return ""; /* retorno fictício */
    }


}
