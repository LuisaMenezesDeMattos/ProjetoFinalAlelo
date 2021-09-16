package dados;

/** CLASSE VALE_REFEIÇÃO (do tipo CARTÃO_BENEFÍCIO) */

public class ValeRefeicao extends CartaoBeneficio{

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private static int validadeDefaultEmMeses = 12;
    private static Double saldoDefault = 200.0;


    /** ------------------------------------------------------------- */
    /** CONSTRUTORES */

    public ValeRefeicao(char[] _senha) {
        super(_senha);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    //todo
    /** Sobrescrita do método tentarPagamento da classe-pai */
    @Override
    public boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra) {

        // não deve realizar a compra se:
        //   > o cartão está vencido
        //   > o teste anti-fraude falhar
        //   > não houver saldo suficiente

        // caso passe todos os testes, fazer:
        //   > adicionar a compra às transações do cartão
        //   > mudar o saldo (levando em conta o cashback de 3%)

        return false;
    }

    /** Sobrescrita do método que retorna o tipo do cartão */
    @Override
    public TipoCartaoBeneficio getTipo() {
        return TipoCartaoBeneficio.VALE_REFEICAO;
    }

}
