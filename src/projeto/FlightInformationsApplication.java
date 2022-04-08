package projeto;


import java.awt.*;
import java.time.ZonedDateTime;
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
      Voo vooObjeto = new Voo();

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


//        System.out.println(voosOrigemDestino);

        voosOrigemDestino.forEach(System.out::println);

//        System.out.println("\n\n------------- teste metodo calcula duracao---------------");
       voosOrigemDestino.forEach(Voo::calculaDuracao);

//        System.out.println("\n------------- teste metodo calcula duracao media---------------");

        //System.out.println("Voo mais rapido");

//        for(int i = 0; i < voosOrigemDestino.size(); i++){
//            System.out.println(voosOrigemDestino);
//        }


        //chamando voo mais rapido
//        String vooMaisRapido = voosOrigemDestino.stream()
//                        .min(Comparator.comparing(Voo::calculaDuracao))
//                                .toString();

        Optional<Voo> vooMaisRapido = voosOrigemDestino.stream()
                        .min(Comparator.comparing(Voo::calculaDuracao));


        System.out.println("\nPrintando vooMaisRapido:");
        System.out.println(vooMaisRapido.orElse(null));
//        System.out.println("\nPrintando vooTeste:");
//        System.out.println(vooTeste.toString());
      System.out.println("\nPrintando duracao voo mais rapido");
        System.out.println(vooMaisRapido.map(Voo::getDuracao).orElse(null));

//pra cima daqui é voo mais curto
        Optional<Voo> vooMaisLongo = voosOrigemDestino.stream()
                .max(Comparator.comparing(Voo::calculaDuracao));

        System.out.println("\nO Voo mais longo nesse percurso é:");
        System.out.println(vooMaisLongo);



      double duracaoMedia = voosOrigemDestino.stream()
              .mapToLong(Voo::calculaDuracao).average().getAsDouble();


        System.out.println("\nMédia da duração: " + duracaoMedia + " horas");

        OptionalDouble preco = voosOrigemDestino.stream()
                .mapToDouble(Voo::getPreco).average();

        double precoMedio = preco.getAsDouble();

        System.out.println("\nMédia do preco: " + precoMedio + " reais");
    }
  public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
    File csvOutputFile = new File(CSV_FILE_NAME);
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      dataLines.stream()
              .map(this::convertToCSV)
              .forEach(pw::println);
    }
    assertTrue(csvOutputFile.exists());
  }
}
