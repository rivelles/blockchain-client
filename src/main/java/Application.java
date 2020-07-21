import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Application {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Client webSocketClient = new Client();
        webSocketClient.clientSession.sendMessage(new TextMessage("{\"op\":\"ping\"}"));
        webSocketClient.clientSession.sendMessage(new TextMessage("{\"op\":\"unconfirmed_sub\"}"));
        Thread.sleep(20000);
        webSocketClient.clientSession.close();
    }
}