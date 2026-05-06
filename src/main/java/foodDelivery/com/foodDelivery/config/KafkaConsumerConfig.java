package foodDelivery.com.foodDelivery.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import foodDelivery.com.foodDelivery.dto.OrderEvent;

@Configuration
public class KafkaConsumerConfig {
	
	@Bean
    public ConsumerFactory<String, OrderEvent> consumerFactory(){
    	
		JsonDeserializer<OrderEvent> deserializer=
				new JsonDeserializer<>(OrderEvent.class);
		
		deserializer.addTrustedPackages("*");
		
		Map<String, Object> config= new HashMap<String, Object>();
		
		config.put(
			ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
			"localhost:9092"
        );
		
		config.put(
			ConsumerConfig.GROUP_ID_CONFIG,
			"order-group"
        );
		
		config.put(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class
	        );
		
		config.put(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				JsonDeserializer.class
	        );
		
		return new DefaultKafkaConsumerFactory<>(
				  config,
				  new StringDeserializer(),
				  deserializer
				);
    }
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, OrderEvent>
	kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory= new ConcurrentKafkaListenerContainerFactory<String, OrderEvent>();
		
		factory.setConsumerFactory(consumerFactory());
		
		return factory;
	}
	
}
