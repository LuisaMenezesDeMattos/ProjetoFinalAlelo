package dados;

import interface_com_usuario.Impressora;

/** CLASSE VALE_COMBUSTÍVEL (do tipo CARTÃO_BENEFÍCIO) */

public class ValeCombustivel extends CartaoBeneficio {


    /** ------------------------------------------------------------- */
    /** CONSTRUTORES */

    public ValeCombustivel(char[] _senha) {
        super(_senha);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    //todo
    /** Sobrescrita do método tentarPagamento da classe-pai */
    @Override
    public boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra) {

        if(this.seVencido()){
            Impressora.msgAtencao("Cartão vencido!");
            return false;
        }
        if(this.tentarPassarNoAntiFraude(valorCompra, estabelecimento) == false){
            return false;
        }

        // não deve realizar a compra se:
        //   > o estabelecimento NÃO é do tipo POSTO_COMBUSTIVEL
        //   > o cartão está vencido
        //   > o teste anti-fraude falhar
        //   > o último uso tiver sido menos de 2 minutos atrás
        //   > não houver saldo suficiente

        // caso passe todos os testes, fazer:
        //   > adicionar a compra às transações do cartão
        //   > mudar o saldo (levando em conta o a taxa de R$1,00)

        valorCompra = valorCompra + 1;
        var novaTransacao = new Transacao(valorCompra, estabelecimento);
        listaTransacoes.add(novaTransacao);
        this.saldo -= valorCompra;
        return true;
    }

    @Override
    public void imprimeDados(){
        Impressora.msgBasica(TipoCartaoBeneficio.VALE_COMBUSTIVEL.label()+ ":");
        Impressora.aumentarIndentacao();
        Impressora.msgBasica("Saldo: " + getSaldo());
        Impressora.msgBasica("Data de validade: " + dataValidade);
        Impressora.diminuirIndentacao();
    }

    @Override
    public TipoCartaoBeneficio getTipo(){
        return TipoCartaoBeneficio.VALE_COMBUSTIVEL;
    }

}
