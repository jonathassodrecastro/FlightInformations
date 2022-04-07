package exercicios;

import java.util.List;

public class MainJavaNIO {
    public static void main(String[] args) {
       GerenciadorDeArquivo gerenciadorDeArquivo = new GerenciadorJavaNIO();

//        gerenciadorDeArquivo.criaDiretorio("diretorio-nio2");
//        gerenciadorDeArquivo.criaArquivo("diretorio-nio2\\teste-nio2.txt");

        List<String> linhas = gerenciadorDeArquivo.leLinhas("diretorio\\flights.csv");
        //gerenciadorDeArquivo.escreveLinhas("diretorio-nio2\\teste-nio2.txt", linhas);

        System.out.println(linhas);
    }
}
