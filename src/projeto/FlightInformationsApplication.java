package projeto;


import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Map.*;
import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.groupingBy;
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

        Stream<Voo> voosSemOrdenacao = lerCSV("C:\\Java\\FlightInformations\\diretorio\\flights.csv");


//ordenando os campos por origem/destino e aí pelos outros campos
      List<String> imprimeVoo = voosSemOrdenacao.sorted(comparing(Voo::getOrigem)
                      .thenComparing(Voo::getDestino)
                      .thenComparing(Voo::getDuracao)
                      .thenComparing(Voo::getPreco)
                      .thenComparing(Voo::getCompanhia))
              .map(Voo::converteParaCSV).collect(Collectors.toList());

      imprimeVoo.add(0, "origin;destination;airline;departure;arrival;price;time");

      gravaCSV("primeiroArquivo.csv", imprimeVoo);
//leitura para o segundo arquivo
      Stream<Voo> voosOrdenados = lerCSV("C:\\Java\\FlightInformations\\diretorio\\flights.csv");
//ordenacao igual ao primeiro arquivo
     List<Voo> voos = voosOrdenados.sorted(comparing(Voo::getOrigem)
             .thenComparing(Voo::getDestino)
             .thenComparing(Voo::getDuracao)
             .thenComparing(Voo::getPreco)
             .thenComparing(Voo::getCompanhia)).collect(Collectors.toList());

//     Map<String, SumarizacaoVoos> voosSumarizados = voos.stream()
//             .sorted(Comparator.comparing(Voo::getOrigem))
//             .collect(groupingBy(Voo::getOrigem))
//             .entrySet()
//             .stream()
//             .map(entry -> mapToSumarizador(entry))
//             .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
//            ;
//      List<String> voosSumarizaArquivo = voosSumarizados.values().stream().map(Objects::toString).collect(Collectors.toList());
//Os elementos continuam na ordem que são inseridos
      Set<String> voosSumarizaArquivo = new LinkedHashSet<>();
//adiciona cabeçalho
      voosSumarizaArquivo.add("origin;destination;shortest_flight(h);longest_fight(h);cheapest_flight;" +
              "most_expensive_flight;average_time;average_price");

      assert false;
      for(Voo voo: voos.stream()
             .sorted(comparing(Voo::getOrigem)
                     .thenComparing(Voo::getDestino))
             .collect(Collectors.toList()))
     {
         voosSumarizaArquivo.add(
                 voo.getOrigem()+";"+voo.getDestino()+";"
                 //voo mais curto
                 +voos.parallelStream()
                         .filter(origem -> origem.getOrigem().equals(voo.getOrigem()))
                         .filter(destino -> destino.getDestino().equals(voo.getDestino()))
                         .map(Voo::getDuracao)
                         .min(naturalOrder()).get() + ";"
                 //voo mais longo
                         +voos.parallelStream()
                         .filter(origem -> origem.getOrigem().equals(voo.getOrigem()))
                         .filter(destino -> destino.getDestino().equals(voo.getDestino()))
                         .map(Voo::getDuracao)
                         .max(naturalOrder()).get() + ";"
                 //voo mais barato
                         +voos.parallelStream()
                         .filter(origem -> origem.getOrigem().equals(voo.getOrigem()))
                         .filter(destino -> destino.getDestino().equals(voo.getDestino()))
                         .map(Voo::getPreco)
                         .min(naturalOrder()).get() + ";"
                 //tempo medio
                         +voos.parallelStream()
                         .filter(origem -> origem.getOrigem().equals(voo.getOrigem()))
                         .filter(destino -> destino.getDestino().equals(voo.getDestino()))
                         .mapToDouble(Voo::getDuracao)
                         .average().orElse(0)+ ";"
                 //preco medio
                         +voos.parallelStream()
                         .filter(origem -> origem.getOrigem().equals(voo.getOrigem()))
                         .filter(destino -> destino.getDestino().equals(voo.getDestino()))
                         .mapToDouble(Voo::getPreco)
                         .average().orElse(0)+ ";"

         );

         List<String> listaFinal = new ArrayList<>(voosSumarizaArquivo);
         gravaCSV("segundoArquivo.csv", listaFinal);

     }

   //  List<String> voosSumarizaArquivo = voosSumarizados.values().stream().map(Objects::toString).collect(Collectors.toList());




 //     gravaCSV("segundoArquivo.csv", imprimeVoosOrdenados);

//      Optional<Voo> vooMaisRapido = voosOrdenados.min(comparing(Voo::getDuracao));
//      imprimeVoosOrdenados.add(vooMaisRapido.orElse(null).toString());
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

public static Map.Entry<String, SumarizacaoVoos> mapToSumarizador(Map.Entry<String, List<Voo>> entry){
      String origem = entry.getKey();
      List<Voo> voos = entry.getValue();

    Optional<Voo> calcVooMaisRapido = voos.stream().min(Comparator.comparing(Voo::getDuracao));
    String vooMaisRapido = calcVooMaisRapido.toString();

    Optional<Voo> calcVooMaisLongo = voos.stream().max(Comparator.comparing(Voo::getDuracao));
    String vooMaisLongo = calcVooMaisLongo.toString();

    Optional<Voo> calcVooMaisBarato = voos.stream().min(Comparator.comparing(Voo::getPreco));
    String vooMaisBarato = calcVooMaisBarato.toString();

    OptionalDouble calcDuracaoMedia = voos.stream().mapToLong(Voo::getDuracao).average();
    double duracaoMedia = calcDuracaoMedia.getAsDouble();

    OptionalDouble calcPrecoMedio = voos.stream().mapToDouble(Voo::getPreco).average();
    double precoMedio = calcPrecoMedio.getAsDouble();

    return Map.entry(origem, new SumarizacaoVoos(vooMaisRapido, vooMaisLongo, vooMaisBarato, duracaoMedia, precoMedio));
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