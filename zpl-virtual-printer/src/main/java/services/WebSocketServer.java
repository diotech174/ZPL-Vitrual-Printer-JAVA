package services;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ServerEndpoint("/")
public class WebSocketServer {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Cliente conectado: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Recebido: " + message);
        session.getBasicRemote().sendText("Eco: " + message);
        
        Date dataHoraAtual = new Date();
        String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(dataHoraAtual);
        
        LabelaryRequest l = new LabelaryRequest();
        l.sendZpl(message, filename + ".png");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Cliente desconectado: " + session.getId());
    }
}