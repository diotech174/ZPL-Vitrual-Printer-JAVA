/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author diogenes
 */
public class ConfigManager {
    private static final String CONFIG_FILE = "config.properties";

    public static void saveConfig(int port, String endpoint) {
        Properties props = new Properties();
        props.setProperty("port", String.valueOf(port));
        props.setProperty("endpoint", endpoint);

        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE)) {
            props.store(output, "Configurações do servidor");
            System.out.println("Configurações salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar configurações: " + e.getMessage());
        }
    }
    
    // Método para carregar a porta salva
    public static int loadPort() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            props.load(input);
            return Integer.parseInt(props.getProperty("port", "9344")); // Valor padrão: 8080
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar a porta: " + e.getMessage());
            return 9344; // Retorna um valor padrão em caso de erro
        }
    }

    // Método para carregar o endpoint salvo
    public static String loadEndpoint() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            props.load(input);
            return props.getProperty("endpoint", "/"); // Valor padrão
        } catch (IOException e) {
            System.err.println("Erro ao carregar o endpoint: " + e.getMessage());
            return "/"; // Retorna um valor padrão em caso de erro
        }
    }
}
