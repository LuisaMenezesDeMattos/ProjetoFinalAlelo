package dados;

/** ------------------------------------------------------------- */

import java.util.Date;

/** CLASSE VALE_ALIMENTAÇÃO (do tipo CARTÃO_BENEFÍCIO) */

public class ValeAlimentacao extends CartaoBeneficio {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private static int validadeDefaultEmMeses = 12;
    // no início do programa, é 12 meses

    private static Double saldoDefault = 600.0;
    // no início do programa, é R$600.00


    /** ------------------------------------------------------------- */
    /** CONSTRUTORES */

    public ValeAlimentacao(int[] _senha) {
        super(_senha);
    }

    public ValeAlimentacao(int[] _senha, Date _dataValidade) {
        super(_senha, _dataValidade);
    }

    /** ------------------------------------------------------------- */
    /** MÉTODOS */

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
}
