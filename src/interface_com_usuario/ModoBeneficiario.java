package interface_com_usuario;

import dados.Beneficiario;
import dados.Estabelecimento;
import dados.TipoCartaoBeneficio;

import java.util.ArrayList;

public class ModoBeneficiario {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private Beneficiario beneficiarioLogado;
    private ArrayList<Estabelecimento> listaEstabelecimentosCadastrados;


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR */

    //todo
    public ModoBeneficiario(){

        listaEstabelecimentosCadastrados = new ArrayList<>();
        // Colocar como hard-coded alguns estabelecimentos automáticos
        // não fazer mais de 9

    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS DE APOIO */


    /** Método que lê um nome e uma senha, e tenta achar o beneficiário correspondente */
    private Beneficiario lerDadosEProcurarBeneficiario(ArrayList<Beneficiario> beneficiariosCadastrados){
        Impressora.msgBasica("Nome: ");
        String nomeBeneficiario = Leitor.lerString();
        Impressora.msgBasica("Senha (6 dígitos): ");
        int[] senhaBeneficiario = Leitor.lerArrayDeInteiros(6);
        for(var beneficiario : beneficiariosCadastrados){
            if(beneficiario.checarDadosLogin(nomeBeneficiario, senhaBeneficiario)){
                return beneficiario;
            }
        }
        return null;
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS QUE EXECUTAM AS OPÇÕES DO USUÁRIO */

    /** Método que roda o 2º menu do Modo Beneficiário: Menu de escolha de cartão */
    private void rodarEscolhaCartao(){

        char opcao;
        do{

            /* Menu */
            Impressora.msgBasica("Escolha qual cartão deseja gerenciar");
            Impressora.msgOpcao('1', "Vale-alimentação");
            Impressora.msgOpcao('2', "Vale-refeição");
            Impressora.msgOpcao('3', "Vale-combustível");
            Impressora.msgOpcao('v', "Voltar");
            opcao = Leitor.lerOpcao(new char[]{'1', '2', '3', 'v'});

            /* Executar opção escolhida */
            switch(opcao){

                /* OPÇÃO #1: Vale-Alimentação */
                case '1':
                    Impressora.subtitulo("Vale-alimentação");
                    rodarGerenciamentoCartao(TipoCartaoBeneficio.VALE_ALIMENTACAO);
                    Impressora.msgRedirecionamento("Voltando ao menu de escolha do cartão");
                    break;

                /* OPÇÃO #2: Vale-Refeição */
                case '2':
                    Impressora.subtitulo("Vale-refeição");
                    rodarGerenciamentoCartao(TipoCartaoBeneficio.VALE_REFEICAO);
                    Impressora.msgRedirecionamento("Voltando ao menu de escolha do cartão");
                    break;

                /* OPÇÃO #3: Vale-Combustível */
                case '3':
                    Impressora.subtitulo("Vale-combustível");
                    rodarGerenciamentoCartao(TipoCartaoBeneficio.VALE_COMBUSTIVEL);
                    Impressora.msgRedirecionamento("Voltando ao menu de escolha do cartão");
                    break;
            }

        }while(opcao != 'v');

        /* Saindo do menu de escolha de cartões */
        Impressora.msgRedirecionamento("Voltando");

    }

    /** Método que roda o 3º menu do Modo Beneficiário: Menu de opções de um cartão */
    private void rodarGerenciamentoCartao(TipoCartaoBeneficio tipoCartao){

        char opcao;
        do{

            /* Menu */
            Impressora.msgBasica("O que deseja fazer com o cartão selecionado?");
            Impressora.msgOpcao('1', "Visualizar Extrato");
            Impressora.msgOpcao('2', "Passar Cartão");
            Impressora.msgOpcao('v', "Voltar");
            opcao = Leitor.lerOpcao(new char[]{'1', '2', 'v'});

            /* Executar opção escolhida */
            switch(opcao){

                /* OPÇÃO #1: Visualizar o extrato do cartão */
                case '1':
                    Impressora.subtitulo("Extrato do Cartão");
                    Impressora.msgBasica(beneficiarioLogado.extratoCartao(tipoCartao));
                    Impressora.msgRedirecionamento("Voltando ao menu de gerenciamento do cartão");
                    break;

                /* OPÇÃO #2: Fazer algum pagamento com o cartão */
                case '2':
                    Impressora.subtitulo("Passar Cartão");
                    rodarPassarCartao(tipoCartao);
                    Impressora.msgRedirecionamento("Voltando ao menu de gerenciamento do cartão");
                    break;
            }

        }while(opcao != 'v');

        /* Saindo do menu de gerenciamento de cartão */
        Impressora.msgRedirecionamento("Voltando");

    }

    /** Método que roda o tratamento do uso de um cartão */
    private void rodarPassarCartao(TipoCartaoBeneficio tipoCartao){

        /* Primeiro, pedir a senha do cartão */
        Impressora.msgBasica("Digite a senha do cartão para acessá-lo (4 dígitos):");
        int[] senha = Leitor.lerArrayDeInteiros(4);
        while(!beneficiarioLogado.checarSenhaCartao(senha, tipoCartao)){
            Impressora.msgAtencao("Senha incorreta");
            Impressora.msgBasica("Deseja tentar novamente? ('s' - sim | 'n' - não)");
            char tentarNovamente = Leitor.lerOpcao(new char[]{'s', 'n'});
            if(tentarNovamente == 's'){
                Impressora.msgBasica("Digite a senha do cartão para acessá-lo (4 dígitos):");
                senha = Leitor.lerArrayDeInteiros(4);
            }else{
                return;
            }
        }

        /* Uma vez que a senha está correta, executar uso do cartão */
        boolean usarCartao = true;
        do{

            /* Escolher estabelecimento */
            Impressora.msgBasica("Escolha o estabelecimento:");
            int flag = 0; // para fins didáticos, o código supõe que não há mais que 9 estabelecimentos cadastrados
            for(var estabelecimento : listaEstabelecimentosCadastrados){
                flag++;
                Impressora.msgOpcao(flag, estabelecimento.getNome());
            }
            int opcaoEstabelecimento = Leitor.lerOpcao(1, flag);
            Estabelecimento estabelecimentoEscolhido = listaEstabelecimentosCadastrados.get(opcaoEstabelecimento - 1);

            /* Informar o valor */
            Impressora.msgBasica("Digite o valor da compra:");
            double valor = Leitor.lerValorEmReais();

            /* Tentar efetuar o pagamento */
            boolean sucessoPagamento = beneficiarioLogado.tentarPassarCartao(tipoCartao, valor, estabelecimentoEscolhido);
            if(sucessoPagamento){
                Impressora.msgAtencao("Pagamento efetuado com sucesso");
                Impressora.msgBasica("Seu novo saldo neste cartão é: " + beneficiarioLogado.getSaldoCartao(tipoCartao));
            }

            /* Perguntar se deseja usar o cartão novamente */
            Impressora.msgBasica("Deseja passar o cartão de novo? ('s' - sim | 'n' - não)");
            char opcaoContinuar = Leitor.lerOpcao(new char[]{'s', 'n'});
            if(opcaoContinuar == 's'){
                usarCartao = true;
            }else{
                usarCartao = false;
            }

        }while(usarCartao);

        /* Saindo da opção de passar o cartão */
        Impressora.msgRedirecionamento("Voltando");

    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PÚBLICOS */

    /** Método que recebe a lista de beneficiários cadastrados, e tenta fazer login como um deles,
     * retornando true caso o login ocorra, ou false caso não */
    public boolean tentarLogin(ArrayList<Beneficiario> beneficiariosCadastrados){

        this.beneficiarioLogado = lerDadosEProcurarBeneficiario(beneficiariosCadastrados);
        boolean tentarNovamente = true;
        while (this.beneficiarioLogado == null && tentarNovamente) {
            Impressora.msgAtencao("Dados incorretos");
            Impressora.msgBasica("Deseja tentar novamente, ou voltar ao menu anterior?");
            Impressora.msgBasica("'t' - Tentar  |  'v' - Voltar");
            char flag = Leitor.lerOpcao(new char[]{'t', 'v'});
            if (flag == 't') {
                Impressora.msgBasica("\nNova tentativa:");
                this.beneficiarioLogado = lerDadosEProcurarBeneficiario(beneficiariosCadastrados);
            }
            else{
                tentarNovamente = false;
                Impressora.msgRedirecionamento("Voltando");
            }
        }
        if(this.beneficiarioLogado != null){
            Impressora.msgAtencao("Dados corretos");
            return true;
        }
        return false;

    }

    /** Método que roda o menu principal do Modo Beneficiário */
    public void rodar(){

        /* Título */
        Impressora.titulo("Beneficiário " + beneficiarioLogado.getNome());
        Impressora.linhaVazia();

        /* Escolher e executar as opções */
        char opcao;
        do{

            /* Menu */
            Impressora.msgBasica("Deseja visualizar os dados de seus cartões, gerenciar um deles, ou fazer logoff?");
            Impressora.msgOpcao('1', "Visualizar Cartões");
            Impressora.msgOpcao('2', "Gerenciar um Cartão");
            Impressora.msgOpcao('s', "Sair");
            opcao = Leitor.lerOpcao(new char[]{'1', '2', 's'});

            /* Executar opção escolhida */
            switch (opcao){

                /* OPÇÃO #1: Visualizar os dados dos cartões */
                case '1':
                    Impressora.subtitulo("Visualizar Cartões");
                    Impressora.msgBasica(beneficiarioLogado.dadosCartoes());
                    Impressora.msgRedirecionamento("Voltando ao menu principal de beneficiário");
                    break;

                /* OPÇÃO #2: Escolher um cartão para gerenciar */
                case '2':
                    Impressora.subtitulo("Gerenciar um Cartão");
                    rodarEscolhaCartao();
                    Impressora.msgRedirecionamento("Voltando ao menu principal de beneficiário");
                    break;

            }

        }while(opcao != 's');

        /* Saindo do Modo Beneficiário */
        Impressora.msgRedirecionamento("Logoff");

    }

}
