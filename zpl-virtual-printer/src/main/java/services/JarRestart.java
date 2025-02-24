/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author diogenes
 */
public class JarRestart {
    public static void restartApplication() throws IOException {
        // Obtém o caminho do JAR
        String jarPath = new File(JarRestart.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();
        
        // Cria o comando para reiniciar
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", jarPath);
        builder.start();

        // Encerra o processo atual
        System.exit(0);
    }

    public static void main(String[] args) {
        System.out.println("Executando aplicação...");
        try {
            Thread.sleep(5000);
            restartApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
