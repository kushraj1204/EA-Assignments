package kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender<T> {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

   /* public void send(String topic, T message){
        kafkaTemplate.send(topic, message);
    }*/

    public void send(String topic, T message){
        try {
            kafkaTemplate.send(topic, new ObjectMapper().writeValueAsString(message));
        }catch (JsonProcessingException e) {
            System.out.println("Could not parse request ");
        }
    }

}
