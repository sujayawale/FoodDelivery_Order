package foodDelivery.com.foodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import foodDelivery.com.foodDelivery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
