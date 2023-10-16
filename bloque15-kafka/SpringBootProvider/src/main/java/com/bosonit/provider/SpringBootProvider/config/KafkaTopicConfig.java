package com.bosonit.provider.SpringBootProvider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){

        Map<String,String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE);
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); //El número de ms que un mensaje se queda retenido
        configurations.put(TopicConfig.SEGMENT_MS_CONFIG, "1073741824"); //Se especifica el tamaño de segmentación en bytes
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "102323"); //Tamaño máximo de cada mensaje
        return TopicBuilder.name("formacionBINAIA-Topic")
                .partitions(2) //Se crearán dos subconjuntos para esteTopic
                .replicas(2) // Se crean dos copias en algún otro topic o kluster
                .configs(configurations)
                .build();
    }
}
