package foodDelivery.com.foodDelivery.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import foodDelivery.com.foodDelivery.dto.OrderEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProducerService {

	private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	public void publish(OrderEvent event) {
		kafkaTemplate.send("order-created-topic", event);
	}
}
