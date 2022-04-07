package projeto;


import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlightInformationsApplication {

    public static void main(String[] args) {
//        Voo voo1 = new Voo("KIN-Jamaica", "MEL-Austrália", "LATAM Airlines", "15/04/2022 19:00:00 (-01:00)", "16/04/2022 13:00:00 (-05:00)", 2044.00);
//        Voo voo2 = new Voo("GRU-Brasil", "MEL-Austrália", "Gol Airlines", "13/04/2022 22:00:00 (-03:00)", "14/04/2022 08:00:00 (-11:00)", 3524.00);
//        Voo voo3 = new Voo("GRU-Brasil", "FEN-Brasil", "AirCanada", "25/04/2022 17:00:00 (+07:00)", "25/04/2022 18:00:00 (-03:00)", 3793.00);
//        Voo voo4 = new Voo("GRU-Brasil", "MEL-Austrália", "Lufthansa", "10/04/2022 14:00:00 (-07:00)", "11/04/2022 06:00:00 (+03:00)", 4848.00);
//        Voo voo5 = new Voo("GRU-Brasil", "MEL-Austrália", "United Airlines", "18/04/2022 16:00:00 (Z)", "19/04/2022 04:00:00 (+01:00)", 3928.00);
//        Voo voo = new Voo();

        List<Voo> voos = List.of(
                new Voo("GRU-Brasil", "MEL-Austrália", "Gol Airlines", "13/04/2022 22:00:00 (-03:00)", "14/04/2022 08:00:00 (-11:00)", 3524.00),
                new Voo("GRU-Brasil", "MEL-Austrália", "Lufthansa", "10/04/2022 14:00:00 (-07:00)", "11/04/2022 06:00:00 (+03:00)", 4848.00),
                new Voo("GRU-Brasil", "MEL-Austrália", "United Airlines", "18/04/2022 16:00:00 (Z)", "19/04/2022 04:00:00 (+01:00)", 3928.00),
                new Voo("GRU-Brasil", "FEN-Brasil", "AirCanada", "25/04/2022 17:00:00 (+07:00)", "25/04/2022 18:00:00 (-03:00)", 3793.00),
                new Voo("KIN-Jamaica", "MEL-Austrália", "LATAM Airlines", "15/04/2022 19:00:00 (-01:00)", "16/04/2022 13:00:00 (-05:00)", 2044.00),
                new Voo("GRU-Brasil","LIS-Portugal","Emirates","11/04/2022 16:00:00 (-02:00)","12/04/2022 12:00:00 (-02:00)",4539.00),
                new Voo("GRU-Brasil","KIN-Jamaica","LATAM Airlines","09/04/2022 07:00:00 (-12:00)","09/04/2022 18:00:00 (-05:00)",2006.00)
        );


        List<Voo> voosOrigemDestino =
                voos.stream()
                        .filter(voo -> Objects.equals(voo.getOrigem(), "GRU-Brasil"))
                        .filter(voo -> Objects.equals(voo.getDestino(), "MEL-Austrália"))
                        .sorted(Comparator.comparing(Voo::getPreco))
                                .collect(Collectors.toList());


        System.out.println(voosOrigemDestino);




//        GerenciadorDeArquivo gerenciadorDeArquivo = new GerenciadorDeArquivoJavaIO();
//
//        List<String> voos = gerenciadorDeArquivo.leLinhas("C:\\Java\\FlightInformations\\flights.csv");
//
//        System.out.println(voos);





    }
}
