package dados;

/** CLASSE VALE_ALIMENTAÇÃO (do tipo CARTÃO_BENEFÍCIO) */

public class ValeAlimentacao extends CartaoBeneficio {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private static int validadeDefaultEmMeses = 12;
    private static Double saldoDefault = 600.0;


    /** ------------------------------------------------------------- */
    /** CONSTRUTORES */

    public ValeAlimentacao(char[] _senha) {
        super(_senha);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    //todo
    /** Sobrescrita do método tentarPagamento da classe-pai */
    @Override
    public boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra) {

        // não deve realizar a compra se:
        //   > o estabelecimento é do tipo POSTO_COMBUSTIVEL
        //   > o cartão está vencido
        //   > o teste anti-fraude falhar
        //   > não houver saldo suficiente

        // caso passe todos os testes, fazer:
        //   > adicionar a compra às transações do cartão
        //   > mudar o saldo (levando em conta o cashback de 1.5%)

        return false;
    }

    /** Sobrescrita do método que retorna o tipo do cartão */
    public TipoCartaoBeneficio getTipo() {
        return TipoCartaoBeneficio.VALE_ALIMENTACAO;
    }
}
