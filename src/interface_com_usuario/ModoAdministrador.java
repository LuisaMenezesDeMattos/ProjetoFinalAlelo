package interface_com_usuario;

import dados.Beneficiario;

import java.util.ArrayList;

public class ModoAdministrador {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private int senhaAdm = 12345;
    // senha para entrar no modo administrador

    public ArrayList<Beneficiario> listaBeneficiariosCadastrados = new ArrayList<Beneficiario>();
    // inicia vazio pois no início do programa, ninguém está cadastrado


    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS */

    /** Método que lê dados no novo beneficiário, e o adiciona à listaUsuariosCadastrados */
    private void cadastrarNovoBeneficiario(){

        // lê nome e senha do novo beneficiário, e lê as senhas desejadas para
        // cada cartão, e então cria um novo usuário do tipo beneficiário
        // com esse nome, essa senha, e os cartões gerados. O saldo e a data de
        // validade dos cartões será gerada automaticamente de acordo com o
        // definido pelo administrador

    }

    /** Método que imprime a lista de beneficiários cadastrado em forma de texto */
    private void visualizarBeneficiarios(){

        // imprime na tela o nome de todos os beneficiários em listaBeneficiariosCadastrados,
        // e seus saldos de cada cartão, de forma amigável para leitura

        // NÃO imprime as senhas, pois somente os beneficiários em si devem poder ver as próprias senhas

        // utiliza o método toString() da classe Beneficiario

    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PÚBLICOS */

    /** Método que lê uma dada senha no máximo 3 vezes, e retorna se está certa
     * Autores: Rafael & Luísa */
    public boolean tentarEntrar(){

        /* Variáveis locais */
        boolean acertou = false;
        int tentativas = 3;
        int chute = 0;

        /* Interação com o usuário */
        while (tentativas > 0 && acertou == false) {
            Impressora.msgBasica("Favor informar a senha do administrador:");
            chute = Leitor.lerInteiro();

            if (chute == senhaAdm) {
                Impressora.msgAtencao("Senha correta");
                acertou = true;
            }
            else {
                --tentativas;
                Impressora.msgAtencao("Senha incorreta! ");
                Impressora.msgBasica(tentativas + " tentativas restantes.");
            }
            if (tentativas == 0) {
                Impressora.msgAtencao("Tentativas esgotadas");
                acertou = false;
            }
        }

        /* Retorno */
        return acertou;

    }

    /** Método que roda as opções e a execução das opções de administrador
     * Autores: Rafael & Luísa */
    public void rodar(){

        /* Variáveis locais */
        int operacao = 0;
        boolean sair = false;
        /*double saldoAlimentacao = 1000;
        double saldoRefeicao = 1000;
        double saldoCombustivel = 1000;
        double valorRemovido = 0;
        double valorAdcionado = 0;*/

        /* Escolher e executar as opções */
        while(operacao != 's'){

            /* Imprimir opções do menu */
            Impressora.titulo("Modo Administrador");
            Impressora.linhaVazia();
            Impressora.msgOpcao('1', "Acrescentar Saldo"); // não-essencial, fazer se der tempo
            Impressora.msgOpcao('2', "Retirar Saldo"); // não-essencial, fazer se der tempo
            Impressora.msgOpcao('3', "Cadastrar novo beneficiário");
            Impressora.msgOpcao('4', "Mostrar lista de beneficiários cadastrados");
            Impressora.msgOpcao('s', "Sair do Modo Administrador");

            /* Pedir entrada da opção desejada */
            operacao = Leitor.lerOpcao(new char[]{'1', '2', '3', '4', 's'});

            /* OPÇÃO 1: Acrescentar saldo */
            if (operacao == '1'){
                    /*msgBasica("Digite os valores que deseja adicionar ao saldo dos cartões:\n");

                    msgBasica("Digite o saldo a acrescentar do vale alimentação:");
                    valorAdcionado = leitor.lerDouble();
                    saldoAlimentacao += valorAdcionado;

                    msgBasica("Digite o saldo a acrescentar do vale combustivel:");
                    valorAdcionado = leitor.lerDouble();
                    saldoCombustivel += valorAdcionado;

                    msgBasica("Digite o saldo a acrescentar do vale refeição:");
                    valorAdcionado = leitor.lerDouble();
                    saldoRefeicao += valorAdcionado;

                    msgBasica("\n Saldo adicionado com sucesso");

                    linhaSeparadora();
                    msgBasica("Saldo atual:");
                    msgBasica("alimentação " + saldoAlimentacao);
                    msgBasica("combustivel " + saldoCombustivel);
                    msgBasica("refeição " + saldoRefeicao);*/

                Impressora.msgBasica("Falta implementar função");

            }

            /* OPÇÃO 2: Retirar Saldo */
            else if (operacao == '2'){
                    /*msgBasica("Digite os valores que deseja remover do saldo dos cartões:\n");

                    msgBasica("Digite o saldo a remover do vale alimentação:");
                    valorRemovido = leitor.lerDouble();
                    saldoAlimentacao -= valorRemovido;

                    msgBasica("Digite o saldo a remover do vale combustivel:");
                    valorRemovido = leitor.lerDouble();
                    saldoCombustivel -= valorRemovido;

                    msgBasica("Digite o saldo a remover do vale refeição:");
                    valorRemovido = leitor.lerDouble();
                    saldoRefeicao -= valorRemovido;

                    msgBasica("\n Saldo removido com sucesso");

                    linhaSeparadora();
                    msgBasica("saldo atual:");
                    msgBasica("alimentação " + saldoAlimentacao);
                    msgBasica("combustivel " + saldoCombustivel);
                    msgBasica("refeição " + saldoRefeicao);*/

                Impressora.msgBasica("Falta implementar função");

            }

            /* OPÇÃO 3: Cadastrar novo beneficiário */
            else if(operacao == '3'){
                Impressora.msgBasica("\nCadastrar novo Beneficiário:");
                cadastrarNovoBeneficiario();
            }

            /* OPÇÃO 4: Mostrar lista beneficiários cadastrados */
            else if(operacao == '4'){
                Impressora.msgBasica("\nBeneficiários Cadastrados:");
                visualizarBeneficiarios();
            }

            /* OPÇÃO 5: Sair do Modo Administrador */
            else if(operacao == 's'){
                sair = true;
            }

            /* Avisar o usuário para onde ele vai agora */
            if(sair == true){
                Impressora.msgBasica("\nFechando o Modo Administrador...");
            }
            else{
                Impressora.msgBasica("\nVoltando ao menu do Modo Administrador...");
            }

        }

    }

}
