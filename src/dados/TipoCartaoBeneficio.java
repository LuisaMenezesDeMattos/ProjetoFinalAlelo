package dados;

import java.time.LocalDate;
import java.util.Date;

public enum TipoCartaoBeneficio {

    VALE_ALIMENTACAO {
        @Override
        public CartaoBeneficio fabricar(char[] senha) {
            return new ValeAlimentacao(senha);
        }

        @Override
        public CartaoBeneficio fabricar(char[] senha, LocalDate validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Vale-Alimentação";
        }
    },
    VALE_REFEICAO {
        @Override
        public CartaoBeneficio fabricar(char[] senha) {
            return new ValeRefeicao(senha);
        }

        @Override
        public CartaoBeneficio fabricar(char[] senha, LocalDate validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Vale-Refeição";
        }
    },
    VALE_COMBUSTIVEL {
        @Override
        public CartaoBeneficio fabricar(char[] senha) {
            return new ValeCombustivel(senha);
        }

        @Override
        public CartaoBeneficio fabricar(char[] senha, LocalDate validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Vale-Combustível";
        }
    };

    public abstract CartaoBeneficio fabricar(char[] senha);
    public abstract CartaoBeneficio fabricar(char[] senha, LocalDate validade);

    public abstract String label();


}