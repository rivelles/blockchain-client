package consumers;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import producers.MessagePublisher;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

public class WebSocketClient extends TextWebSocketHandler {

    private final WebSocketSession clientSession;
    private static final String URI_WEBSOCKET = "wss://streamer.cryptocompare.com/v2?api_key=<YOUR-API-KEY>>";
    private MessagePublisher publisher;

    public WebSocketClient(MessagePublisher publisher) throws ExecutionException, InterruptedException {
        this.clientSession = new StandardWebSocketClient()
                .doHandshake(this, new WebSocketHttpHeaders(), URI.create(URI_WEBSOCKET)).get();

        this.publisher = publisher;
    }

    public void sendData(String data) throws IOException {
        clientSession.sendMessage(new TextMessage(data));
    }

    public void close() throws IOException {
        clientSession.close();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        publisher.sendData(payload);
    }
}