package dados;

import interface_com_usuario.Impressora;

/** CLASSE VALE_ALIMENTAÇÃO (do tipo CARTÃO_BENEFÍCIO) */

public class ValeAlimentacao extends CartaoBeneficio {


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

        if(this.seVencido()){
            Impressora.msgAtencao("Cartão vencido!");
            return false;
        }
        if(this.tentarPassarNoAntiFraude(valorCompra, estabelecimento) == false){
            return false;
        }

        // não deve realizar a compra se:
        //   > o estabelecimento é do tipo POSTO_COMBUSTIVEL
        //   > o cartão está vencido
        //   > o teste anti-fraude falhar
        //   > não houver saldo suficiente

        // caso passe todos os testes, fazer:
        //   > adicionar a compra às transações do cartão
        //   > mudar o saldo (levando em conta o cashback de 1.5%)

        valorCompra -= valorCompra * 0.015;
        var novaTransacao = new Transacao(valorCompra, estabelecimento);
        listaTransacoes.add(novaTransacao);
        this.saldo -= valorCompra;
        return true;
    }


    @Override
    public void imprimeDados(){
        Impressora.msgBasica(TipoCartaoBeneficio.VALE_ALIMENTACAO.label()+ ":");
        Impressora.aumentarIndentacao();
        Impressora.msgBasica("Saldo: " + getSaldo());
        Impressora.msgBasica("Data de validade: " + dataValidade);
        Impressora.diminuirIndentacao();
    }

    @Override
    public TipoCartaoBeneficio getTipo(){
        return TipoCartaoBeneficio.VALE_ALIMENTACAO;
    }
}
