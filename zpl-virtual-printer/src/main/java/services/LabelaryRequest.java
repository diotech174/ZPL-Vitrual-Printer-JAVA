/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author diogenes
 */
public class LabelaryRequest {
    public void sendZpl(String zplData, String fileName) {

        // Cria o cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Cria a requisição HTTP POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.labelary.com/v1/printers/8dpmm/labels/4x6/0/"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(zplData, StandardCharsets.UTF_8))
                .build();

        try {
            // Envia a requisição e recebe a resposta
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            // Verifica se a resposta foi bem-sucedida
            if (response.statusCode() == 200) {
                // Salva a imagem retornada em um arquivo (ou processa de outra forma)
                byte[] imageData = response.body();
                // Aqui você pode salvar o byte array em um arquivo PNG, por exemplo
                java.nio.file.Files.write(java.nio.file.Path.of("labels/"+fileName), imageData);
                // Salva o conteúdo ZPL em um arquivo de texto
                saveZplToFile(zplData, fileName.replace(".png", ""));
                
                System.out.println("Label saved as " + fileName);
            } else {
                System.out.println("Failed to get label. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void saveZplToFile(String zplData, String fileName) {
        try {
            // Cria o diretório "labels" se ele não existir
            Path labelsDir = Paths.get("labels");
            if (!Files.exists(labelsDir)) {
                Files.createDirectories(labelsDir);
            }

            // Salva o conteúdo ZPL em um arquivo de texto
            Path filePath = labelsDir.resolve(fileName + ".txt");
            Files.write(filePath, zplData.getBytes(StandardCharsets.UTF_8));

            System.out.println("ZPL data saved as " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
