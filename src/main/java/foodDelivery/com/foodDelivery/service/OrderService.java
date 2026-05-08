package foodDelivery.com.foodDelivery.service;

import org.springframework.stereotype.Service;

import foodDelivery.com.foodDelivery.dto.OrderEvent;
import foodDelivery.com.foodDelivery.entity.Order;
import foodDelivery.com.foodDelivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
 
	private final OrderRepository repository;
	
	private final OrderProducerService orderProducerService;
	
	public Order createOrder(Order order) {
		order.setStatus("CREATED");
		
		Order saveOrder =repository.save(order);
		
		OrderEvent event = OrderEvent.builder()
				.orderId(saveOrder.getId())
				.userId(saveOrder.getUserId())
				.restaurantId(saveOrder.getRestaurantId())
				.amount(saveOrder.getTotalAmount())
				.status(saveOrder.getStatus())
				.build();
		
		orderProducerService.publish(event);
		
		return saveOrder;
	}
	
	
}
