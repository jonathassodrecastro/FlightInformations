package exercicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GerenciadorJavaNIO implements GerenciadorDeArquivo{

    @Override
    public void apagaArquivo(String caminhoArquivo) {
        Path caminho = Path.of(caminhoArquivo);
        try{
            Files.deleteIfExists(caminho);
        } catch (NoSuchFileException exception){
            System.out.println(exception);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagaDiretorio(String caminhoDiretorio) {
        Path caminho = Path.of(caminhoDiretorio);
        try{
            Files.delete(caminho);
        } catch (IOException exception){
            //System.out.println(exception);
        }
    }

    @Override
    public void criaArquivo(String caminhoArquivo) {
        Path caminho = Path.of(caminhoArquivo);
        try{
            Files.createFile(caminho);
        } catch (IOException exception){
            //System.out.println(exception);
        }
    }

    @Override
    public void criaDiretorio(String caminhoDiretorio) {
        Path caminho = Path.of(caminhoDiretorio);
        try{
            Files.createDirectories(caminho);
        } catch (IOException exception){
            //System.out.println(exception);
        }

    }

    @Override
    public List<String> leLinhas(String caminhoArquivo) {
        Path caminho = Paths.get(caminhoArquivo);
        try{
            Files.readAllLines(caminho);
        } catch (IOException exception){
            //System.out.println(exception);
        }

        return null;
    }

    @Override
    public void escreveLinhas(String caminhoArquivo, List<String> conteudoArquivo) {
        Path caminho = Paths.get(caminhoArquivo);
        try{
            Files.write(caminho, conteudoArquivo);
        } catch (IOException exception){
            //System.out.println(exception);
        }

    }
}
