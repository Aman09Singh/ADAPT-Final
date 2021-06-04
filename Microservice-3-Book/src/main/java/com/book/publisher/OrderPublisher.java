package com.book.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.book.config.MessagingConfig;
import com.book.dto.Order;
import com.book.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

	 	@Autowired
	    private RabbitTemplate template;

	    @PostMapping("/{ticket}")
	    public String bookOrder(@RequestBody Order order, @PathVariable String ticket) {
	        order.setOrderId(UUID.randomUUID().toString());
	        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "ticket booked successfully in " + ticket);
	        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
	        return "Success !!";
	    }
}
