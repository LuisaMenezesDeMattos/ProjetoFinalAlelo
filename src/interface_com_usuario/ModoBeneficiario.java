package interface_com_usuario;

import dados.Beneficiario;
import dados.Estabelecimento;
import dados.TipoCartaoBeneficio;

import java.util.ArrayList;
import java.util.Arrays;

public class ModoBeneficiario {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private static Beneficiario beneficiarioLogado;
    private static ArrayList<Estabelecimento> listaEstabelecimentosCadastrados = new ArrayList<>();


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR DE CLASSE ESTÁTICA */

    private ModoBeneficiario(){
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS ESTÁTICOS */

    /** Método que lê um nome e uma senha, e tenta achar o beneficiário correspondente */
    private static Beneficiario lerDadosEProcurarBeneficiario(){
        Impressora.linhaVazia();
        Impressora.subtitulo("Login do Beneficiário");
        Impressora.msgBasica("Nome: ");
        String nomeBeneficiario = Leitor.lerString();
        Impressora.msgBasica("Senha (6 dígitos): ");
        char[] senhaBeneficiario = Leitor.lerArrayDeDigitos(6);
        return ModoAdministrador.checarDadosLoginBeneficiario(nomeBeneficiario, senhaBeneficiario);
    }

    /** Método que recebe um código e retorna o estabelecimento relativo, caso haja */
    public static Estabelecimento buscaEstabelecimento(char[] codigo){
        for(var estabelecimento : listaEstabelecimentosCadastrados){
            if(Arrays.equals(estabelecimento.getCodigo(), codigo)){
                return estabelecimento;
            }
        }
        return null;
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS QUE EXECUTAM AS OPÇÕES DO USUÁRIO */

    /** Método que roda o 2º menu do Modo Beneficiário: Menu de escolha de cartão */
    private static void rodarEscolhaCartao(){

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
    private static void rodarGerenciamentoCartao(TipoCartaoBeneficio tipoCartao){

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
    private static void rodarPassarCartao(TipoCartaoBeneficio tipoCartao){

        /* Primeiro, pedir a senha do cartão */
        Impressora.msgBasica("Digite a senha do cartão para acessá-lo (4 dígitos):");
        char[] senha = Leitor.lerArrayDeDigitos(4);
        while(!beneficiarioLogado.checarSenhaCartao(senha, tipoCartao)){
            Impressora.msgAtencao("Senha incorreta");
            Impressora.msgBasica("Deseja tentar novamente? ('s' - sim | 'n' - não)");
            char tentarNovamente = Leitor.lerOpcao(new char[]{'s', 'n'});
            if(tentarNovamente == 's'){
                Impressora.msgBasica("Digite a senha do cartão para acessá-lo (4 dígitos):");
                senha = Leitor.lerArrayDeDigitos(4);
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

    /** Método que r tenta fazer login como um dos beneficiários cadastrados em ModoAdministrador,
     * retornando true caso o login ocorra, ou false caso não */
    public static boolean tentarLogin(){

        beneficiarioLogado = lerDadosEProcurarBeneficiario();
        boolean tentarNovamente = true;
        while (beneficiarioLogado == null && tentarNovamente) {
            Impressora.msgAtencao("Dados incorretos");
            Impressora.msgBasica("Deseja tentar novamente, ou voltar ao menu anterior?");
            Impressora.msgBasica("'t' - Tentar  |  'v' - Voltar");
            char flag = Leitor.lerOpcao(new char[]{'t', 'v'});
            if (flag == 't') {
                Impressora.msgBasica("\nNova tentativa:");
                beneficiarioLogado = lerDadosEProcurarBeneficiario();
            }
            else{
                tentarNovamente = false;
                Impressora.msgRedirecionamento("Voltando");
            }
        }
        if(beneficiarioLogado != null){
            Impressora.msgAtencao("Dados corretos");
            return true;
        }
        return false;

    }

    /** Método que roda o menu principal do Modo Beneficiário */
    public static void rodar(){

        /* Título */
        Impressora.subtitulo("Beneficiário: " + beneficiarioLogado.getNome());
        Impressora.linhaVazia();

        /* Escolher e executar as opções */
        char opcao;
        do{

            /* Menu */
            Impressora.subtitulo("Menu");
            Impressora.msgBasica("Deseja visualizar os dados de seus cartões, gerenciar um deles, ou fazer logoff?");
            Impressora.msgOpcao('1', "Visualizar Cartões");
            Impressora.msgOpcao('2', "Gerenciar um Cartão");
            Impressora.msgOpcao('s', "Sair");
            opcao = Leitor.lerOpcao(new char[]{'1', '2', 's'});

            /* Executar opção escolhida */
            Impressora.linhaVazia();
            Impressora.aumentarIndentacao();
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
