/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.File;

/**
 *
 * @author diogenes
 */
public class FileDeletion {
    public static void main(String[] args) {
        String filePath = "caminho/do/seu/arquivo.txt"; // Substitua pelo caminho do arquivo que deseja excluir
        boolean isDeleted = deleteFile(filePath);

        if (isDeleted) {
            System.out.println("Arquivo excluído com sucesso.");
        } else {
            System.out.println("Falha ao excluir o arquivo ou o arquivo não existe.");
        }
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);

        // Verifica se o arquivo existe
        if (file.exists()) {
            // Tenta excluir o arquivo
            return file.delete();
        } else {
            System.out.println("O arquivo não existe.");
            return false;
        }
    }
}
