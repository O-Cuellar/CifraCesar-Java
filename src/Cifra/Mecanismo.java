package Cifra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mecanismo {

    private ArrayList<String> primaryBuffer;
    private ArrayList<String> secundaryBuffer;

    String arquivoTexto = "src/Texto/entradaTexto.txt";
    String arquivoCripto = "src/Texto/saidaCripto.txt";
    String saidaCripto = "src/Texto/saidaDeCripto.txt";

    public void LoadPrimaryBuffer() {

        primaryBuffer = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoTexto))) {
            String line;
            while ((line = reader.readLine()) != null) {
                primaryBuffer.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void SaveBufferInArchive(ArrayList<String> secundaryBuffer, String arquivo){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))){
            for (String line : secundaryBuffer) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Arquivo salvo em /src/Texto/saidaCripto");
        } catch (IOException e){
            System.out.println("Erro ao salvar o arquivo " + e.getMessage());
        }
    }

    public void PrintSecundaryBuffer(){
        System.out.println("##### Conteudo do Arquivo #####");
        System.out.println("-------------------------------");
        for (String texto : this.secundaryBuffer) {
            System.out.println(texto);            
        }
        System.out.println("-------------------------------");
    }

    public void executarCripto() {
        
        Scanner scanner = new Scanner(System.in);
        secundaryBuffer = new ArrayList<>();

        System.out.println("Digite a chave de criptografia:");
        int chave = scanner.nextInt();

        CifraCesar cifra = new CifraCesar(chave);

        for (String line : primaryBuffer) {
            String criptografado = cifra.criptografar(line);
            secundaryBuffer.add(criptografado);
        }

        SaveBufferInArchive(secundaryBuffer, arquivoCripto);
        PrintSecundaryBuffer();
    }

    public void executarDecripto() {

        Scanner scanner = new Scanner(System.in);
        secundaryBuffer = new ArrayList<>();

        System.out.println("Digite a chave de desciptografia");
        int chave = scanner.nextInt();

        CifraCesar cifra = new CifraCesar(chave);

        for (String line : primaryBuffer) {
            String descripto = cifra.descriptografar(line);
            secundaryBuffer.add(line);
        }

        SaveBufferInArchive(secundaryBuffer, saidaCripto);
        PrintSecundaryBuffer();
;    }
}