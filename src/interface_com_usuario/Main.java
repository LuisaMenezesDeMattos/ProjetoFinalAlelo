package interface_com_usuario;

public class Main {

    /** ------------------------------------------------------------- */
    /** MÉTODO MAIN */

    public static void main(String[] args) {

        /* Variáveis locais */
        var modoAdministrador = new ModoAdministrador();
        var modoBeneficiario = new ModoBeneficiario();
        var rodarPrograma = true;

        /* Início do Programa */
        Impressora.logomarca();
        Impressora.titulo("Bem-vindo ao programa de gerenciamento de cartões da All Benefícios!");

        /* Configurações iniciais */
        Impressora.linhaVazia();
        Impressora.msgBasica("Antes de iniciar, é necessário que sejam feitas as configurações do programa.");
        Impressora.linhaVazia();
        if(modoAdministrador.tentarEntrar()){
            modoAdministrador.rodar();
        }else{
            Impressora.msgAtencao("Como foi excedido o limite de tentativas, o programa será abortado");
            rodarPrograma = false;
        }

        /* Programa em si (pós configurações iniciais) */
        if(rodarPrograma){

            char opcaoModo;
            do{

                /* Menu para escolher qual modo do programa deseja acessar */
                Impressora.subtitulo("Escolha do Modo (Administrador | Beneficiário)");
                Impressora.msgBasica("Deseja voltar ao Modo Administrador para alterar configurações do programa,");
                Impressora.msgBasica("ou entrar no Modo Beneficiário para acessar o uso dos cartões?");
                Impressora.msgOpcao('1', "Beneficiário");
                Impressora.msgOpcao('2', "Administrador");
                Impressora.msgOpcao('s', "Sair");
                opcaoModo = Leitor.lerOpcao(new char[]{'1', '2', 's'});

                /* Executar opção escolhida */
                switch (opcaoModo){

                    /* MODO BENEFICIÁRIO */
                    case '1':

                        /* Título */
                        Impressora.titulo("Modo Beneficiário");

                        /* Tentar login como beneficiário */
                        if(modoBeneficiario.tentarLogin(modoAdministrador.listaBeneficiariosCadastrados)){
                            Impressora.msgRedirecionamento("Fazendo login");
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
            Impressora.msgRedirecionamento("Fechando o programa");
            Impressora.titulo("Obrigado por usar o programa de gerenciamento de cartões da All Benefícios!");

        }

    }

}
