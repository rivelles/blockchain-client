import consumers.WebSocketClient;
import org.springframework.web.socket.TextMessage;
import producers.MessagePublisher;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Application {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        MessagePublisher publisher = new MessagePublisher();
        WebSocketClient webSocketClient = new WebSocketClient(publisher);
        webSocketClient.sendData("{\n" +
                "   \"action\": \"SubAdd\",\n" +
                "   \"subs\": [\"5~CCCAGG~BTC~USD\"]\n" +
                "}\n");

        Thread.sleep(60000);

        webSocketClient.close();
        publisher.close();
    }
}