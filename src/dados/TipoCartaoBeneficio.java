package dados;

import java.util.Date;

public enum TipoCartaoBeneficio {

    VALE_ALIMENTACAO {
        @Override
        public CartaoBeneficio fabricar(int[] senha) {
            return new ValeAlimentacao(senha);
        }

        @Override
        public CartaoBeneficio fabricar(int[] senha, Date validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Vale-Alimentação";
        }
    },
    VALE_REFEICAO {
        @Override
        public CartaoBeneficio fabricar(int[] senha) {
            return new ValeRefeicao(senha);
        }

        @Override
        public CartaoBeneficio fabricar(int[] senha, Date validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Vale-Refeição";
        }
    },
    VALE_COMBUSTIVEL {
        @Override
        public CartaoBeneficio fabricar(int[] senha) {
            return new ValeCombustivel(senha);
        }

        @Override
        public CartaoBeneficio fabricar(int[] senha, Date validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Vale-Combustível";
        }
    };

    public abstract CartaoBeneficio fabricar(int[] senha);
    public abstract CartaoBeneficio fabricar(int[] senha, Date validade);

    public abstract String label();


}