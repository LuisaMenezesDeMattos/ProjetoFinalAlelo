package dados;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/** ----------------------------------------------------------------- */
/** CLASSE ABSTRATA CARTÃO-BENEFÍCIO
 * Função: Declara as características e comportamentos em comum para todos
 *          os cartões do sistema (vale-alimentação, vale-refeição, e vale-combustível)
 * */

public abstract class CartaoBeneficio {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    protected char[] senha = new char[4];
    // a senha deve ser, obrigatoriamente, 4 dígitos numéricos

    protected Double saldo;
    // lembrando que não pode ser negativo

    protected LocalDate dataValidade;
    // lembrando que não pode efetuar a compra com um cartão vencido

    protected ArrayList<Transacao> listaTransacoes = new ArrayList<Transacao>();
    // guarda o histórico de todas as transações deste cartão
    // começa vazio

    protected static int validadeDefaultEmMeses;
    // meses para o cálculo da validade de um cartão quando ele for criado

    protected static Double saldoDefault;
    // valor automático pro saldo de um novo cartão quando for criado


    /** ------------------------------------------------------------- */
    /** CONSTRUTORES */

    public CartaoBeneficio(char[] _senha){
        this.senha = _senha;
        this.saldo = saldoDefault;
        this.dataValidade = calcularDataValidade(validadeDefaultEmMeses);
    }
    public CartaoBeneficio(char[] _senha, LocalDate _dataValidade){
        this(_senha);
        this.dataValidade = _dataValidade;
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS ESTÁTICOS */

    /** Método estático que gera uma senha de cartão aleatória */
    public static char[] gerarSenhaAleatoria(){
        char[] senha = new char[4];
        var gerador = new Random();
        for(int i=0; i<4; i++){
            senha[i] = (gerador.nextInt(10) + "").charAt(0);
        }
        return senha;
    }

    /** Método estático que calcula a data de validade */
    public static LocalDate calcularDataValidade(int meses){
        return LocalDate.now().plusMonths(meses);
    }

    /** Método estático que formata um valor em reais */
    public static String formataReais(double valor){
        return new DecimalFormat("'R$'.00").format(valor);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PROTEGIDOS */

    /** Método que checa se o cartão está vencido */
    protected boolean seVencido(){
        if(this.dataValidade.isBefore(LocalDate.now())){
            return true;
        }
        return false;
    }

    /** Método que checa a hora para fazer o teste anti-fraude, retornando true se passar e false se falhar */
    protected boolean tentarPassarNoAntiFraude(double valorCompra, Estabelecimento estabelecimento){

        // Se não há transações cadastradas ainda, não precisa fazer nenhum teste
        int qtdTransacoes = listaTransacoes.size();
        if(qtdTransacoes == 0){
            return true;
        }

        // Comparar essa compra com a transação mais recente
        // (Não devemos passar duas compras do mesmo valor no mesmo estabelecimento em um período de 30 segundos)
        var ultimaTransacao = listaTransacoes.get(qtdTransacoes-1);
        if(ultimaTransacao.isValor(valorCompra) && ultimaTransacao.isEstabelecimento(estabelecimento)){
            if(ultimaTransacao.segundosDesdeTransacao() <= 30){
                return false;
            }
        }

        // Comparar a hora de agora com a hora da penúltima transação
        // (Não devemos passar três compras no mesmo cartão dentro do mesmo minuto.)
        if(qtdTransacoes > 1){
            var penultimaTransacao = listaTransacoes.get(qtdTransacoes-2);
            if(penultimaTransacao.segundosDesdeTransacao() <= 60){
                return false;
            }
        }

        // Passou nos testes
        return true;

    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PÚBLICOS */

    /** Método que retorna os dados deste cartão em forma de texto */
    @Override
    public String toString() {
        return  "Saldo: " + saldo +
                "\nData de Validade: " + dataValidade +
                ' ';
    }

    /** Método que retorna o saldo do cartão */
    public String getSaldo(){
        return formataReais(this.saldo);
    }

    /** Método que retorna o extrato deste cartão em forma de texto */
    public String extrato(){

        if(listaTransacoes.size() == 0){
            return "Não há histórico de transações neste cartão ainda";
        }

        String retorno = "";
        retorno += "Saldo: " + getSaldo();
        retorno += "\nTransações:";
        for(var transacao : listaTransacoes){
            retorno += "\n" + transacao;
        }
        return retorno;

    }

    /** Método que checa se uma dada senha é a correta */
    public boolean checarSenha(char[] senhaChecar){
        return (Arrays.equals(senhaChecar, this.senha));
    }

    /** Método que tenta realizar um pagamento neste cartão */
    public abstract boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra);

    /** Método que retorna o tipo do cartão */
    public abstract TipoCartaoBeneficio getTipo();




}
