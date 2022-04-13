package projeto;

public class SumarizacaoVoos {

        protected String vooMaisRapido;
        protected String vooMaisLongo;
        protected String vooMaisBarato;
        protected double duracaoMedia;
        protected double precoMedio;

    public SumarizacaoVoos(String vooMaisRapido, String vooMaisLongo, String vooMaisBarato, double duracaoMedia, double precoMedio) {
        this.vooMaisRapido = vooMaisRapido;
        this.vooMaisLongo = vooMaisLongo;
        this.vooMaisBarato = vooMaisBarato;
        this.duracaoMedia = duracaoMedia;
        this.precoMedio = precoMedio;
    }

    @Override
    public String toString() {
        return "SumarizacaoVoos{" +
                "vooMaisRapido='" + vooMaisRapido + '\'' +
                ", vooMaisLongo='" + vooMaisLongo + '\'' +
                ", vooMaisBarato='" + vooMaisBarato + '\'' +
                ", duracaoMedia=" + duracaoMedia +
                ", precoMedio=" + precoMedio +
                '}';
    }
}
