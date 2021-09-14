package dados;

public enum TipoCartaoBeneficio {

    VALE_ALIMENTACAO {
        @Override
        public ICartaoBeneficio fabricar() {
            return new ValeAlimentacao();
        }
    },
    VALE_REFEICAO {
        @Override
        public ICartaoBeneficio fabricar() {
            return new ValeRefeicao();
        }
    },
    VALE_COMBUSTIVEL {
        @Override
        public ICartaoBeneficio fabricar() {
            return new ValeCombustivel();
        }
    };

    public abstract ICartaoBeneficio fabricar();


}