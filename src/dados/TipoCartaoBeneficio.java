package dados;

public enum TipoCartaoBeneficio {

    VALE_ALIMENTACAO {
        @Override
        public ICartaoBeneficio fabricar() {
            return new ValeAlimentacao();
        }

        @Override
        public String label() {
            return "Vale-Alimentação";
        }
    },
    VALE_REFEICAO {
        @Override
        public ICartaoBeneficio fabricar() {
            return new ValeRefeicao();
        }

        @Override
        public String label() {
            return "Vale-Refeição";
        }
    },
    VALE_COMBUSTIVEL {
        @Override
        public ICartaoBeneficio fabricar() {
            return new ValeCombustivel();
        }

        @Override
        public String label() {
            return "Vale-Combustível";
        }
    };

    public abstract ICartaoBeneficio fabricar();


}