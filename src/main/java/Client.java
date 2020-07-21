import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.concurrent.ExecutionException;

public class Client extends TextWebSocketHandler {

    public final WebSocketSession clientSession;
    private final String uri = "wss://ws.blockchain.info/btc/inv";

    public Client() throws ExecutionException, InterruptedException {
        this.clientSession = new StandardWebSocketClient()
                .doHandshake(this, new WebSocketHttpHeaders(), URI.create(uri)).get();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        System.out.println(payload);
    }
}