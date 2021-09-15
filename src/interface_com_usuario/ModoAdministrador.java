package interface_com_usuario;

import dados.*;

import java.util.*;

public class ModoAdministrador {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private int senhaAdm = 12345;
    // senha para entrar no modo administrador

    public ArrayList<Beneficiario> listaBeneficiariosCadastrados = new ArrayList<Beneficiario>();
    // inicia vazio pois no início do programa, ninguém está cadastrado


    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS */

    private ArrayList<CartaoBeneficio> lerDadosCartoes(){

        var cartoesBeneficiario = new ArrayList<CartaoBeneficio>();

        /* Senhas dos Cartões */
        Impressora.msgBasica("Deseja definir senhas aleatórias para os cartões, ou definir manualmente?:");
        Impressora.msgBasica("('a' - aleatório  |  'm' - manual)");
        char opcao = Leitor.lerOpcao(new char[]{'a', 'm'});
        Impressora.aumentarIndentacao();

        Map<TipoCartaoBeneficio, int[]> senhas = new HashMap<>();
        TipoCartaoBeneficio[] tiposDeCartao = TipoCartaoBeneficio.values();

        if(opcao == 'a'){



            for (var tipoDeCartao: tiposDeCartao) {
                var senhaGerada = CartaoBeneficio.gerarSenhaAleatoria();
                senhas.put(tipoDeCartao, senhaGerada);
                Impressora.msgSenha("Senha do " + tipoDeCartao.toString(), senhaGerada);
            }

        }
        else if(opcao == 'm'){

            for (var tipoDeCartao: tiposDeCartao) {
                var senhaGerada = Leitor.lerArrayDeInteiros(4);;
                senhas.put(tipoDeCartao, senhaGerada);
                Impressora.msgSenha("Senha do " + tipoDeCartao.toString(), senhaGerada);
            }

        }
        Impressora.diminuirIndentacao();

        /* Validade dos Cartões */
        Impressora.msgBasica("Deseja usar a validade padrão para os cartões (12 meses), ou defini-las manualmente?");
        Impressora.msgBasica("('p' - padrão  |  'm' - manual)");
        opcao = Leitor.lerOpcao(new char[]{'p', 'm'});
        Impressora.aumentarIndentacao();
        if(opcao == 'p'){
            Impressora.msgAtencao("Validade dos 3 cartões definida para daqui 12 meses");

            for (var tipoDeCartao: tiposDeCartao) {
                cartoesBeneficiario.add(tipoDeCartao.fabricar(senhas.get(tipoDeCartao)));
            }
            Impressora.diminuirIndentacao();
            return cartoesBeneficiario;
        }
        else if(opcao == 'm'){
            Impressora.msgBasica("Favor informar a validade, em meses, de cada cartão:");
            Impressora.msgBasica("   (Obs.: para cartões vencidos, digite uma quatidade negativa de meses)");


            for (var tipoDeCartao: tiposDeCartao) {
                Impressora.msgBasica(tipoDeCartao.toString());
                Date dateValidade = CartaoBeneficio.calcularDataValidade(Leitor.lerInteiro());
                cartoesBeneficiario.add(tipoDeCartao.fabricar(senhas.get(tipoDeCartao), dateValidade));
            }


            Impressora.diminuirIndentacao();
            return cartoesBeneficiario;
        }

        Impressora.diminuirIndentacao();
        return cartoesBeneficiario;

    }

    //todo
    /** Método que lê dados no novo beneficiário, e o adiciona à listaUsuariosCadastrados */
    private void cadastrarNovoBeneficiario(){

        Impressora.msgBasica("Nome do Beneficiário:");
        var nome = Leitor.lerString();
        Impressora.msgBasica("Senha do Beneficiário (6 dígitos):");
        var senha = Leitor.lerArrayDeInteiros(6);
        var cartoes = lerDadosCartoes();
        var novoBeneficiario = new Beneficiario(nome, senha, cartoes);
        this.listaBeneficiariosCadastrados.add(novoBeneficiario);
        Impressora.msgAtencao("Cadastro realizado com sucesso");

    }

    //todo
    /** Método que imprime a lista de beneficiários cadastrado em forma de texto */
    private void visualizarBeneficiarios(){

        Impressora.msgBasica("Falta implementar função");

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
                Impressora.msgAtencao("Senha incorreta");
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

        /* Título */
        Impressora.titulo("Modo Administrador");

        /* Menu */
        Impressora.linhaVazia();
        Impressora.subtitulo("Menu do Administrador");
        Impressora.msgOpcao('1', "Cadastrar novo beneficiário");
        Impressora.msgOpcao('2', "Ver lista de beneficiários cadastrados");
        Impressora.msgOpcao('3', "Editar dados dos cartões de um beneficiário");
        Impressora.msgOpcao('s', "Sair do Modo Administrador");
        Impressora.linhaSeparadora();

        /* Escolher e executar as opções */
        char opcao;
        do{

            /* Escolher a opção */

            Impressora.linhaVazia();
            Impressora.msgBasica("Digite a opção desejada:");
            opcao = Leitor.lerOpcao(new char[]{'1', '2', '3', 's'});

            /* Organização e indentação */
            if(opcao == 's'){
                continue;
            }else{
                Impressora.linhaVazia();
                Impressora.aumentarIndentacao();
            }

            /* Executar opção escolhida */
            switch(opcao){

                /* OPÇÃO 1: Cadastrar novo beneficiário */
                case '1':
                    Impressora.subtitulo("Cadastrar novo Beneficiário:");
                    cadastrarNovoBeneficiario();
                    break;

                /* OPÇÃO 2: Mostrar lista beneficiários cadastrados */
                case '2':
                    Impressora.subtitulo("Beneficiários Cadastrados:");
                    visualizarBeneficiarios();
                    break;

                /* OPÇÃO 3: Mostrar lista beneficiários cadastrados */
                case '3':
                    Impressora.subtitulo("Editar dados dos cartões de um beneficiário:");
                    Impressora.msgBasica("Função ainda não implementada");
                    break;
            }

            Impressora.msgRedirecionamento("Voltando ao menu do Modo Administrador");
            Impressora.linhaVazia();
            Impressora.linhaSeparadoraDupla();
            Impressora.diminuirIndentacao();

        }while(opcao != 's');

        /* Saindo do Modo Administrador */
        Impressora.linhaVazia();
        Impressora.msgRedirecionamento("Fechando o Modo Administrador");
        Impressora.linhaVazia();
        Impressora.linhaSeparadoraDupla();

    }

}
