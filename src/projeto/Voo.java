package projeto;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Voo {
    protected String origem;
    protected String destino;
    protected String companhia;
    protected ZonedDateTime partida;
    protected ZonedDateTime chegada;
    protected double preco;
    protected long duracao;

    public String getOrigem() {
        return origem;
    }
    public String getDestino() {
        return destino;
    }
    public ZonedDateTime getPartida() {
        return partida;
    }
    public ZonedDateTime getChegada() {
        return chegada;
    }
    public double getPreco() {
        return preco;
    }
    public String getCompanhia() {
        return companhia;
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
    public void setPartida(ZonedDateTime partida) {
        this.partida = partida;
    }
    public void setChegada(ZonedDateTime chegada) {
        this.chegada = chegada;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }
    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    public Voo(String origem, String destino, String companhia, String partida, String chegada, double preco) {
        this.origem = origem;
        this.destino = destino;
        this.companhia = companhia;
        this.partida = ZonedDateTime.parse(partida, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss '('zzz')'"));
        this.chegada = ZonedDateTime.parse(chegada, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss '('zzz')'"));
        this.preco = preco;
        //this.duracao = this.partida.until(this.chegada, ChronoUnit.HOURS);
    }

    public Voo(){}

//    public long calculaDuracao(ZonedDateTime partida, ZonedDateTime chegada){
//        long duracao = 0L;
//        duracao = partida.until(chegada, ChronoUnit.HOURS);
//        return duracao;
//    }

    @Override
    public String toString() {
        return "Voo{" +
                "origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", companhia='" + companhia + '\'' +
                ", partida=" + partida +
                ", chegada=" + chegada +
                ", preco=" + preco +
                '}';
    }

    public static long calculaDuracao(ZonedDateTime partida, ZonedDateTime chegada){
        long duracao;
        duracao = partida.until(chegada, ChronoUnit.HOURS);
        return duracao;
    }

    public void calculaDuracao() {
        long duracao;
        long duracaoTeste;
        ZoneOffset zoneOffset = ZoneOffset.of("-03:00");
        //OffsetDateTime  = OffsetDateTime.now(zoneOffset);
//        OffsetDateTime partidaFuso = partida.withFixedOffsetZone().toOffsetDateTime().with(zoneOffset);
//        OffsetDateTime chegadaFuso = chegada.withFixedOffsetZone().toOffsetDateTime().with(zoneOffset);
        ZonedDateTime partidaFuso = partida.withZoneSameLocal(ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime chegadaFuso = chegada.withZoneSameLocal(ZoneId.of("America/Sao_Paulo"));

        duracaoTeste = partidaFuso.until(chegadaFuso, ChronoUnit.HOURS);
        duracao = partida.until(chegada, ChronoUnit.HOURS);
        System.out.println(partidaFuso+", "+ chegadaFuso+ " - "+duracaoTeste+" horas");
        System.out.println(this.partida+", "+ this.chegada+ " - "+duracao+" horas\n");
    }
}
