package exercicios;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exercicio {

    public static void main(String[] args) {
        List<Integer> numeros = IntStream.rangeClosed(-10, 10).boxed().collect(Collectors.toList());
        System.out.println(numeros);

        String numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
        System.out.println(numerosPares);

        String numerosImpares = numeros.stream()
                .filter(n -> n % 2 != 0)
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
        System.out.println(numerosImpares);

        Map<String, List<Integer>> numerosPorParOuImpar =
                numeros.stream()
                        .filter(n -> n>=0)
                        .collect(Collectors.groupingBy(n ->
                                                        (n%2 == 0)?"Pares":"Ímpares"));

        System.out.println(numerosPorParOuImpar);

    }

}
