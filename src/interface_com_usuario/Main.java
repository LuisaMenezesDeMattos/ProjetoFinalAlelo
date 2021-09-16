package interface_com_usuario;

import dados.Estabelecimento;
import dados.TipoEstabelecimento;

public class Main {

    /** ------------------------------------------------------------- */
    /** MÉTODO MAIN */

    public static void main(String[] args) {

        var estabelecimentoTeste = new Estabelecimento(new char[]{}, "", TipoEstabelecimento.MERCADO, "");

        /* Variáveis locais */
        var modoAdministrador = new ModoAdministrador();
        var modoBeneficiario = new ModoBeneficiario();
        var rodarPrograma = true;

        /* Início do Programa */
        Impressora.logomarca();
        Impressora.linhaVazia();
        Impressora.titulo("Bem-vindo ao programa de gerenciamento de cartões da All Benefícios!");


        /* Configurações iniciais */
        Impressora.msgBasica("Antes de iniciar, é necessário que sejam feitas as configurações do programa.");
        if(modoAdministrador.tentarEntrar()){

            /* Rodar configurações iniciais */
            Impressora.linhaVazia();
            Impressora.aumentarIndentacao();
            modoAdministrador.rodar();

            /* Sair das configurações iniciais */
            Impressora.diminuirIndentacao();
            Impressora.linhaVazia();
            Impressora.msgRedirecionamento("Saindo das configurações iniciais e abrindo o programa");
            Impressora.linhaVazia();
            Impressora.linhaSeparadoraDupla();

        }else{
            Impressora.msgAtencao("Como foi excedido o limite de tentativas, o programa será abortado");
            rodarPrograma = false;
        }

        /* Programa em si (pós configurações iniciais) */
        if(rodarPrograma){

            char opcaoModo;
            do{

                /* Menu para escolher qual modo do programa deseja acessar */
                Impressora.linhaVazia();
                Impressora.titulo("Escolha do Modo (Administrador | Beneficiário)");
                Impressora.linhaVazia();
                Impressora.msgBasica("Deseja voltar ao Modo Administrador para alterar configurações do programa,");
                Impressora.msgBasica("ou entrar no Modo Beneficiário para acessar o uso dos cartões?");
                Impressora.linhaVazia();
                Impressora.subtitulo("Menu de Escolha de Modo");
                Impressora.msgOpcao('1', "Beneficiário");
                Impressora.msgOpcao('2', "Administrador");
                Impressora.msgOpcao('s', "Sair");
                Impressora.linhaSeparadora();
                Impressora.linhaVazia();
                Impressora.msgBasica("Digite a opção desejada:");
                opcaoModo = Leitor.lerOpcao(new char[]{'1', '2', 's'});

                /* Organização e indentação */
                if(opcaoModo == 's'){
                    continue;
                }else{
                    Impressora.linhaVazia();
                    Impressora.aumentarIndentacao();
                    //Impressora.aumentarIndentacao();
                }

                /* Executar opção escolhida */
                switch (opcaoModo){

                    /* MODO BENEFICIÁRIO */
                    case '1':

                        /* Título */
                        Impressora.titulo("Modo Beneficiário");

                        /* Tentar login como beneficiário */
                        if(modoBeneficiario.tentarLogin(modoAdministrador.listaBeneficiariosCadastrados)){
                            Impressora.msgRedirecionamento("Fazendo login");
                            Impressora.linhaVazia();
                            Impressora.linhaSeparadoraDupla();
                            Impressora.linhaVazia();
                            modoBeneficiario.rodar();
                        }

                        /* Fim do Modo Beneficiário */
                        Impressora.msgRedirecionamento("Saindo do Modo Beneficiário");
                        break;

                    /* MODO ADMINISTRADOR */
                    case '2':

                        /* Título */
                        Impressora.titulo("Modo Administrador");

                        /* Tentar entrar no Modo Administrador novamente */
                        if(modoAdministrador.tentarEntrar()){
                            modoAdministrador.rodar();
                        }else{
                            Impressora.msgAtencao("Como foi excedido o limite de tentativas, o programa sera abortado");
                            rodarPrograma = false;
                        }

                        /* Fim do Modo Administrador */
                        Impressora.msgRedirecionamento("Saindo do Modo Administrador");
                        break;

                }

            }while(opcaoModo != 's');

            /* Final do programa */
            Impressora.linhaVazia();
            Impressora.msgRedirecionamento("Fechando o programa");
            Impressora.linhaSeparadoraDupla();
            Impressora.diminuirIndentacao();
            Impressora.linhaVazia();
            Impressora.titulo("Obrigado por usar o programa de gerenciamento de cartões da All Benefícios!");
            Impressora.logomarca();

        }

    }

}
