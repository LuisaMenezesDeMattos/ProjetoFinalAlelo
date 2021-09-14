package dados;

import java.util.ArrayList;
import java.util.Date;

public abstract class CartaoBeneficio implements ICartaoBeneficio {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    protected final int[] codigo = new int[16];
    //código do tipo 0000-0000-0000-0000

    protected int[] senha = new int[4];
    // a senha deve ser, obrigatoriamente, 4 dígitos numéricos

    protected Double saldo;
    // lembrando que não pode ser negativo

    protected Date dataValidade;
    // lembrando que não pode efetuar a compra com um cartão vencido

    protected ArrayList<Transacao> listaTransacoes = new ArrayList<Transacao>();
    // guarda o histórico de todas as transações deste cartão
    // começa vazio

    protected static int validadeDefaultEmMeses;
    // meses para o cálculo da validade de um cartão quando ele for criado
    // somente no modo administrador é possível configurar esse valor

    protected static Double saldoDefault;
    // valor automático pro saldo de um novo cartão quando for criado
    // somente no modo administrador é possível configurar esse valor


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    /** Método que retorna os dados deste cartão em forma de texto */
    @Override
    public String toString(){
        return ""; /* retorno fictício */
    }

    /** Método que retorna o extrato deste cartão em forma de texto */
    public String extrato(){

        // retorna os dados de:
        //   > listaTransacoes
        //   > saldo
        // de forma amigável para leitura

        return ""; /* retorno fictício */

    }

    /** Método que checa se uma dada senha é a correta */
    public boolean checarSenha(int[] senhaChecar){

        // recebe uma senha, e retorna true caso seja igual à senha deste cartão,
        // mas false caso não seja

        return true; /* retorno fictício */

    }

    /** Método que checa se o cartão está vencido */
    public boolean seVencido(){

        // checa a data de hoje com a data de validade,
        // e retorna true caso o cartão esteja vencido,
        // ou false caso não

        return true; /* retorno fictício */

    }

    /** Método que tenta realizar um pagamento neste cartão */
    public abstract boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra);
    // recebe o estabelecimento e o valor, checa se a compra pode ser realizada, e realiza caso possa;
    // se realizou, retorna true; se o pagamento foi rejeitado, mostra a msgErro correspondente ao motivo
    // da rejeição, e então retorna false


}
