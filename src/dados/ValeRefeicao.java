package dados;

public class ValeRefeicao extends CartaoBeneficio{

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
}
