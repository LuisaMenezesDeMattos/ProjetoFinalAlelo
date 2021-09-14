package dados;

public class ValeCombustivel extends CartaoBeneficio {

    @Override
    public boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra) {

        // não deve realizar a compra se:
        //   > o estabelecimento NÃO é do tipo POSTO_COMBUSTIVEL
        //   > o cartão está vencido
        //   > o teste anti-fraude falhar
        //   > o último uso tiver sido menos de 2 minutos atrás
        //   > não houver saldo suficiente

        // caso passe todos os testes, fazer:
        //   > adicionar a compra às transações do cartão
        //   > mudar o saldo (levando em conta o a taxa de R$1,00)

        return false;
    }
}
