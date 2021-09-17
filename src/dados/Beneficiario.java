package dados;

import java.util.ArrayList;

public class Beneficiario {

    /** -------------------------------------------------------------
    /** ATRIBUTOS */

    private String nome;
    private char[] senha = new char[6];
    private ArrayList<CartaoBeneficio> listaCartoes;


    /** -------------------------------------------------------------
    /** CONSTRUTOR */

    public Beneficiario(String _nome, char[] _senha, ArrayList<CartaoBeneficio> _listaCartoes) {
        this.nome = _nome;
        this.senha = _senha;
        this.listaCartoes = _listaCartoes;
    }


    /** -------------------------------------------------------------
    /** MÉTODOS */

    /** Método que checa os dados deste beneficiário */
    public boolean checarDadosLogin(String nomeChecar, char[] senhaChecar) {
        boolean nomeCorreto = this.nome.equals(nomeChecar);
        boolean senhaCorreta = true;
        if(this.senha.length != senhaChecar.length){
            senhaCorreta  = false;
        }else{
            for(int i=0; i<senhaChecar.length; i++){
                if(this.senha[i] != senhaChecar[i]){
                    senhaCorreta = false;
                    break;
                }
            }
        }

        if (nomeCorreto == false || senhaCorreta == false) {
            return false;
        } else {
            return true;
        }
    }

    /** Método que retorna os dados deste beneficiário como String */
    @Override
    public String toString() {
        return "Nome:" + nome + "\n" +
                "Lista de Cartões:\n" + listaCartoes;
    }

    /** Método que tenta usar o cartão, e retorna true caso dê certo, ou false caso não */
    public boolean tentarPassarCartao(TipoCartaoBeneficio tipo, Double valor, Estabelecimento estabelecimento) {

        for(var cartao : listaCartoes) {
            boolean b = cartao.getTipo() == tipo;

            if(b) {
                return cartao.tentarPagamento(estabelecimento,valor);
            }
        }

        return false;

    }

    /** Método que recebe um tipo de cartão, e retorna os dados do mesmo */
    public String extratoCartao(TipoCartaoBeneficio tipo) {

        for(var cartao : listaCartoes){
            boolean b = cartao.getTipo() == tipo;

            if(b) {
                return cartao.extrato();
            }

        }

        return "";
    }

    /** Método que retorna o nome do beneficiário */
    public String getNome() {
        return nome;
    }

    /** Método que retorna o saldo de um dado cartão */
    public String getSaldoCartao (TipoCartaoBeneficio tipo){
        for(var cartao : listaCartoes) {
            boolean b = cartao.getTipo() == tipo;

            if(b) {
                return cartao.getSaldo();
            }
        }

        return "";

    }

    /** Método que retorna se uma dada senha corresponde ao um dado cartão */
    public boolean checarSenhaCartao (char[] senhaChecar, TipoCartaoBeneficio tipo){

        for(var cartao : listaCartoes) {
            boolean b = cartao.getTipo() == tipo;

            if(b) {

                boolean senhaCorreta = true;
                if(cartao.senha.length != senhaChecar.length){
                    senhaCorreta  = false;
                }else{
                    for(int i=0; i<senhaChecar.length; i++){
                        if(cartao.senha[i] != senhaChecar[i]){
                            senhaCorreta = false;
                            break;
                        }
                    }
                }
                return senhaCorreta;
            }

        }

        return false;

    }

    /** Método que retorna os dados de cada cartão */
    public String dadosCartoes () {

        String retorno = "";

        for(var cartao : listaCartoes){
            retorno = retorno + cartao.toString()+"\n";
        }


        return retorno;
    }
}
