package interface_com_usuario;

import java.util.Locale;

public class Impressora {

    /** ------------------------------------------------------------- */
    /** CONSTRUTOR DE CLASSE ESTÁTICA */

    private Impressora(){}


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    /** O que aparece para que o usuário entenda que deve digitar algo */
    public static void inputFlag(String identacao){
        System.out.print(identacao + "> ");
    }
    public static void inputFlag(){
        inputFlag("");
    }

    /** Imprime uma longa linha na tela */
    public static void linhaSeparadora(){
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    /** Pula uma linha */
    public static void linhaVazia(){
        System.out.println("");
    }

    /** Imprime a logomarca da empresa
     * Autor: Rafael */
    public static void logomarca(){
        linhaVazia();
        linhaSeparadora();
        msgBasica("  █▀▀█ █░░ █░░   █▀▀█ █▀▀ █▀▀▄ █▀▀ █▀▀ ░▀░ █▀▀ ░▀░ █▀▀█ █▀▀");
        msgBasica("  █▄▄█ █░░ █░░   █▀▀▄ █▀▀ █░░█ █▀▀ █▀▀ ▀█▀ █░░ ▀█▀ █░░█ ▀▀█");
        msgBasica("  █░▒█ ▀▀▀ ▀▀▀   █▄▄█ ▀▀▀ ▀░░▀ ▀▀▀ ▀░░ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀▀ ▀▀▀");
        linhaSeparadora();
        linhaVazia();
    }

    /** Imprime um título */
    public static void titulo(String texto){
        linhaSeparadora();
        System.out.println("  ***  " + texto.toUpperCase(Locale.ROOT) + "  ***  ");
        linhaSeparadora();
    }

    /** Imprime um subtítulo */
    public static void subtitulo(String texto){
        linhaVazia();
        linhaSeparadora();
        msgBasica(texto.toUpperCase(Locale.ROOT), " ----- ");
        linhaVazia();
    }

    /** Imprime uma mensagem qualquer */
    public static void msgBasica(String texto, String identacao){
        System.out.println(identacao + texto);
    }
    public static void msgBasica(String texto){ msgBasica(texto, ""); }

    /** Imprime uma mensagem de aviso ou atenção */
    public static void msgAtencao(String texto, String identacao){
        msgBasica("* " + texto + "!", identacao);
    }
    public static void msgAtencao(String texto){ msgAtencao(texto, ""); }

    /** Imprime uma opção que o usuário pode escolher */
    public static void msgOpcao(char flag, String label, String identacao){
        msgBasica("'" + flag + "' - " + label, identacao);
    }
    public static void msgOpcao(char flag, String label){
        msgOpcao(flag, label, "");
    }
    public static void msgOpcao(int flag, String label){
        msgOpcao(Integer.toString(flag).charAt(0), label, "");
    }

    /** Imprime uma mensagem avisando ao usuário para onde ele vai agora */
    public static void msgRedirecionamento(String texto){
        msgBasica(texto + "...", "");
        linhaSeparadora();
        linhaVazia();
    }

}
