package projeto;


import static org.junit.Assert.assertTrue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightInformationsApplication {

private static final String CSV_FILE_NAME = "C:\\Java\\FlightInformations\\diretorio\\";

  public static void main(String[] args) throws IOException {
      Voo vooObjeto = new Voo();


//        List<Voo> voos = List.of(
//                new Voo("GRU-Brasil", "MEL-Austrália", "Gol Airlines", "13/04/2022 22:00:00 (-03:00)", "14/04/2022 08:00:00 (-11:00)", 3524.00),
//                new Voo("GRU-Brasil", "MEL-Austrália", "Lufthansa", "10/04/2022 14:00:00 (-07:00)", "11/04/2022 06:00:00 (+03:00)", 4848.00),
//                new Voo("GRU-Brasil", "MEL-Austrália", "United Airlines", "18/04/2022 16:00:00 (Z)", "19/04/2022 04:00:00 (+01:00)", 3928.00),
//                new Voo("GRU-Brasil", "FEN-Brasil", "AirCanada", "25/04/2022 17:00:00 (+07:00)", "25/04/2022 18:00:00 (-03:00)", 3793.00),
//                new Voo("KIN-Jamaica", "MEL-Austrália", "LATAM Airlines", "15/04/2022 19:00:00 (-01:00)", "16/04/2022 13:00:00 (-05:00)", 2044.00),
//                new Voo("GRU-Brasil","LIS-Portugal","Emirates","11/04/2022 16:00:00 (-02:00)","12/04/2022 12:00:00 (-02:00)",4539.00),
//                new Voo("GRU-Brasil","KIN-Jamaica","LATAM Airlines","09/04/2022 07:00:00 (-12:00)","09/04/2022 18:00:00 (-05:00)",2006.00)
//        );

        Stream<Voo> voos = lerCSV("C:\\Java\\FlightInformations\\diretorio\\flights.csv");


//        List<String> imprimeVoo = voos.sorted(Comparator.comparing(Voo::getOrigem))
//                .map(Voo::converteParaCSV).collect(Collectors.toList());

      List<String> imprimeVoo = voos.sorted(Comparator.comparing(Voo::getOrigem))
        .sorted(Comparator.comparing(Voo::getDestino))
              .map(Voo::converteParaCSV).collect(Collectors.toList());

      

//      assert false;
      gravaCSV("primeiroArquivo.csv", imprimeVoo);


//        List<Voo> voosOrigemDestino =
//                voos
//                        .filter(voo -> Objects.equals(voo.getOrigem(), "GRU-Brasil"))
//                        .filter(voo -> Objects.equals(voo.getDestino(), "MEL-Austrália"))
//                        .sorted(Comparator.comparing(Voo::getPreco))
//                                .collect(Collectors.toList());
//
//
//        voosOrigemDestino.forEach(System.out::println);
//
//
//
//        Optional<Voo> vooMaisRapido = voosOrigemDestino.stream()
//                        .min(Comparator.comparing(Voo::calculaDuracao));
//
//        imprimeVoo.add(vooMaisRapido.orElse(null).toString());
//
//        System.out.println("\nO voo Mais Rapido e:");
//        System.out.println(vooMaisRapido.orElse(null));
//
//      System.out.println("\nA duracao voo mais rapido");
//        System.out.println(vooMaisRapido.map(Voo::getDuracao).orElse(null));
//
//
//        Optional<Voo> vooMaisLongo = voosOrigemDestino.stream()
//                .max(Comparator.comparing(Voo::calculaDuracao));
//
//        imprimeVoo.add(vooMaisLongo.toString());
//
//
//      System.out.println("\nO Voo mais longo nesse percurso é:");
//        System.out.println(vooMaisLongo.orElse(null));
//
//      System.out.println("\nA duracao voo mais longo");
//      System.out.println(vooMaisLongo.map(Voo::getDuracao).orElse(null));
//
//      double duracaoMedia = voosOrigemDestino.stream()
//              .mapToLong(Voo::calculaDuracao).average().getAsDouble();
//
//      String mediaDaDuracao = ("Média da duração dos voos:" + duracaoMedia + " horas");
//      imprimeVoo.add(mediaDaDuracao);
//
//      System.out.printf("\nMédia da duração: %.2f horas", duracaoMedia);
//
//        OptionalDouble preco = voosOrigemDestino.stream()
//                .mapToDouble(Voo::getPreco).average();
//
//        double precoMedio = preco.getAsDouble();
//
//
//      String mediaDoPreco = ("Média da preco: " + precoMedio + " reais");
//      imprimeVoo.add(mediaDoPreco);
//
//        System.out.printf("\nMédia dos preços: R$%.2f", precoMedio);
//
//
//      gravaCSV("listaFinalDosVoos.csv", imprimeVoo);
    }



  public static void gravaCSV(String nomeArquivo, List<String> dataLines) throws IOException {
    File csvOutputFile = new File(CSV_FILE_NAME+nomeArquivo);
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
        dataLines.forEach(pw::println);
    }
     assertTrue(csvOutputFile.exists());
  }

    public static Stream<Voo> lerCSV(String csvFileName) throws IOException {
     Path caminho = Path.of(csvFileName);
      return Files.readAllLines(caminho)
                //Converter para lista
                .stream().skip(1)
                //Mapear as linhas  por ";"
                .map(linha->{ String[] elementos = linha.split(";");
                    return new Voo (
                            elementos [0],//origem
                            elementos [1],//destino
                            elementos [2],//companhia
                            elementos [3],//partida
                            elementos [4],//chegada
                            Double.parseDouble(elementos [5])
                    );
                });
    }
}