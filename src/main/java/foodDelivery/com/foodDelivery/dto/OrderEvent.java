package foodDelivery.com.foodDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent {
	
  private Long orderId;
  
  private Long userId;
  
  private Long restaurantId;
  
  private Double amount;
  
  private String status;
}
