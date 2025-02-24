package services;

import org.glassfish.tyrus.server.Server;
import java.util.HashMap;
import java.util.Map;

public class WebSocketServerApp {
    private Server server;

    public void start() {
        // Configurações vazias para o servidor WebSocket
        Map<String, Object> serverProperties = new HashMap<>();

        // Criando o servidor WebSocket
        server = new Server("localhost", ConfigManager.loadPort(), ConfigManager.loadEndpoint(), serverProperties, WebSocketServer.class);

        try {
            server.start();
            System.out.println("Servidor WebSocket iniciado em ws://localhost:" + ConfigManager.loadPort() + ConfigManager.loadEndpoint());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (server != null) {
            server.stop();
            System.out.println("Servidor WebSocket parado.");
        }
    }
}