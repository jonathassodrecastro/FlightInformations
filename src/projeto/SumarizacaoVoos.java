package projeto;

public class SumarizacaoVoos {
        private String origem;
        private String destino;
        private double preco;
        private long duracao;

    public String getOrigem() {
        return origem;
    }
    public String getDestino() {
        return destino;
    }
    public double getPreco() {
        return preco;
    }
    public long getDuracao() {
        return duracao;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "SumarizacaoVoos{" +
                "origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", preco=" + preco +
                ", duracao=" + duracao +
                '}';
    }
}
