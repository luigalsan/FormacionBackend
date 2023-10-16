package com.bosonit.provider.SpringBootProvider.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProviderConfig {


    @Value("${spring.kafka.bootstrapServers}") //Accedemos a las propiedades de nuestro properties
    private String bootstrapServers;

    public Map<String, Object> producerConfig(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); //Indicar nuestro servidor de Kafka
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //El objeto que va a serializar el mensaje de nuestra KEY en formato String a bytes
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //El objeto que va a serializar el mensaje de nuestro OBJECT en formato String a bytes

        return properties;

    }

    @Bean
    public ProducerFactory<String, String> providerFactory(){ //El patrón de diseño de tipo Factory
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, String> KafkaTemplate(ProducerFactory<String,String> producerFactory){
        return new KafkaTemplate<>(producerFactory); //Inyecta el ProducerFactory debido a que lo pone como parámetro en este mismo método
    }
}
