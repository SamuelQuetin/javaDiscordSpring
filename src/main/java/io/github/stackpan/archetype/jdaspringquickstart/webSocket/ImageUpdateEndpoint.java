package io.github.stackpan.archetype.jdaspringquickstart.webSocket;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("localhost:8080/imageUpdates")
public class ImageUpdateEndpoint {
    private static Set<Session> connectedClients = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        connectedClients.add(session);
        System.out.println("Client connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String imageUrl, Session session) throws IOException {
        for (Session client : connectedClients) {
            client.getBasicRemote().sendText(imageUrl);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason.CloseCode closeCode) {
        connectedClients.remove(session);
        System.out.println("Client disconnected: " + session.getId() + " (" + closeCode + ")");
    }
}
