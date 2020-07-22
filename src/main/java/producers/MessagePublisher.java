package producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class MessagePublisher {
    private final KafkaProducer<String, String> producer;
    private static final String BOOTSTRAP_SERVER = "localhost:9092";
    private static final String TOPIC = "indexes";
    private final Logger logger = LoggerFactory.getLogger(MessagePublisher.class);

    public MessagePublisher() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<String, String>(properties);
    }

    public void sendData(String data) {
        producer.send(new ProducerRecord<String, String>(TOPIC, data), (recordMetadata, e) -> {
            if (e != null) logger.error("An error happened.", e);
        });

        //producer.flush();
    }

    public void close() {
        producer.close();
    }
}
