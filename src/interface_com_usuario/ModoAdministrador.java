package interface_com_usuario;

import dados.*;

import java.util.*;

public class ModoAdministrador {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private static int senhaAdm = 12345;
    // senha para entrar no modo administrador

    private static ArrayList<Beneficiario> listaBeneficiariosCadastrados = new ArrayList<Beneficiario>();
    // inicia vazio pois no início do programa, ninguém está cadastrado


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR DE CLASSE ESTÁTICA */

    private ModoAdministrador(){}


    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS DE APOIO */

    //todo: APAGAR ESSE MÉTODO!!!
    private static void hardCodeBeneficiarios(){
        var senhaB = new char[]{'1', '2', '3', '4', '5', '6'};
        var senhaC = new char[]{'1', '2', '3', '4'};
        var cartoes = new ArrayList<CartaoBeneficio>();
        cartoes.add(new ValeAlimentacao(senhaC));
        cartoes.add(new ValeRefeicao(senhaC));
        cartoes.add(new ValeCombustivel(senhaC));
        var luisa = new Beneficiario("Luísa", senhaB, cartoes);
        listaBeneficiariosCadastrados.add(luisa);
    }

    /** Método que lê dados dos cartões de um novo beneficiário */
    private static ArrayList<CartaoBeneficio> lerDadosCartoes(){

        /* Variáveis locais */
        var cartoesBeneficiario = new ArrayList<CartaoBeneficio>();
        Map<TipoCartaoBeneficio, char[]> senhas = new HashMap<>();
        TipoCartaoBeneficio[] tiposDeCartao = TipoCartaoBeneficio.values();

        /* Senhas dos Cartões */
        Impressora.msgBasica("Deseja definir senhas aleatórias para os cartões, ou definir manualmente?:");
        Impressora.msgBasica("('a' - aleatório  |  'm' - manual)");
        char opcao = Leitor.lerOpcao(new char[]{'a', 'm'});
        Impressora.aumentarIndentacao();

        if(opcao == 'a'){
            for (var tipoDeCartao: tiposDeCartao) {
                var senhaGerada = CartaoBeneficio.gerarSenhaAleatoria();
                senhas.put(tipoDeCartao, senhaGerada);
                Impressora.msgSenha("Senha do " + tipoDeCartao.label(), senhaGerada);
            }
        }
        else{
            for (var tipoDeCartao: tiposDeCartao) {
                Impressora.msgBasica("Senha do " + tipoDeCartao.label() + " (4 dígitos):");
                var senhaDada = Leitor.lerArrayDeDigitos(4);;
                senhas.put(tipoDeCartao, senhaDada);
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
        }
        else{
            Impressora.msgBasica("Favor informar a validade, em meses, de cada cartão:");
            Impressora.msgBasica("   (Obs.: para cartões vencidos, digite uma quatidade negativa de meses)");
            for (var tipoDeCartao: tiposDeCartao) {
                Impressora.msgBasica(tipoDeCartao.label());
                Date dateValidade = CartaoBeneficio.calcularDataValidade(Leitor.lerInteiro());
                cartoesBeneficiario.add(tipoDeCartao.fabricar(senhas.get(tipoDeCartao), dateValidade));
            }
        }

        /* Retorno */
        Impressora.diminuirIndentacao();
        return cartoesBeneficiario;
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PÚBLICOS DE APOIO */

    public static Beneficiario getBeneficiario(String nome){
        for(var beneficiario : listaBeneficiariosCadastrados){
            if(beneficiario.getNome().equals(nome)){
                return beneficiario;
            }
        }
        return null;
    }

    public static Beneficiario checarDadosLoginBeneficiario(String nome, char[] senha){
        for(var beneficiario : listaBeneficiariosCadastrados){
            if(beneficiario.checarDadosLogin(nome, senha)){
                return beneficiario;
            }
        }
        return null;
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS QUE EXECUTAM AS OPÇÕES DO USUÁRIO */

    /** Método que lê dados no novo beneficiário, e o adiciona à listaUsuariosCadastrados */
    private static void cadastrarNovoBeneficiario(){

        Impressora.msgBasica("Nome do Beneficiário:");
        var nome = Leitor.lerString();
        Impressora.msgBasica("Senha do Beneficiário (6 dígitos):");
        var senha = Leitor.lerArrayDeDigitos(6);
        var cartoes = lerDadosCartoes();
        var novoBeneficiario = new Beneficiario(nome, senha, cartoes);
        listaBeneficiariosCadastrados.add(novoBeneficiario);
        Impressora.msgAtencao("Cadastro realizado com sucesso");

    }

    /** Método que imprime a lista de beneficiários cadastrado em forma de texto */
    private static void visualizarBeneficiarios(){
        if(listaBeneficiariosCadastrados.size() == 0){
            Impressora.msgAtencao("Não há nenhum beneficiário cadastrado ainda");
        }
        int posicao = 1;
        Impressora.linhaSeparadora();
        for(var beneficiario : listaBeneficiariosCadastrados){
            Impressora.msgBasica(posicao++ + ". " + beneficiario.toString());
            Impressora.linhaSeparadora();
        }
    }

    /** Método que dá a chance de editar saldo e validade de um dado cartão de um dado usuário */
    private static void editarCartao(){
        Impressora.msgBasica("Nome do beneficiário:");
        String nome = Leitor.lerString();
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PÚBLICOS */

    /** Método que lê uma dada senha no máximo 3 vezes, e retorna se está certa
     * Autores: Rafael & Luísa */
    public static boolean tentarEntrar(){

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
    public static void rodar(){

        //todo: tirar!!!
        hardCodeBeneficiarios();

        /* Título */
        Impressora.titulo("Modo Administrador");

        /* Escolher e executar as opções */
        char opcao;
        do{

            /* Menu */
            Impressora.linhaVazia();
            Impressora.subtitulo("Menu do Administrador");
            Impressora.msgOpcao('1', "Cadastrar novo beneficiário");
            Impressora.msgOpcao('2', "Ver lista de beneficiários cadastrados");
            Impressora.msgOpcao('3', "Editar dados dos cartões de um beneficiário");
            Impressora.msgOpcao('s', "Sair do Modo Administrador");
            Impressora.linhaSeparadora();

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

                /* OPÇÃO 3: Editar dados de um dos cartões de um beneficiário */
                case '3':
                    Impressora.subtitulo("Editar dados dos cartões de um beneficiário:");
                    editarCartao();
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
