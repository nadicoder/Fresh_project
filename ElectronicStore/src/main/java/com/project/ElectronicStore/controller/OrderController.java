package com.project.ElectronicStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ElectronicStore.Dto.ApiResponseMessage;
import com.project.ElectronicStore.Dto.CreateOrderRequest;
import com.project.ElectronicStore.Dto.OrderDto;
import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Servicelayer.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	 @Autowired
	    private OrderService orderService;

	    //create
	    @PostMapping
	    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request) {
	        OrderDto order = orderService.createOrder(request);
	        return new ResponseEntity<>(order, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/{orderId}")
	    public ResponseEntity<ApiResponseMessage> removeOrder(@PathVariable String orderId) {
	        orderService.removeOrder(orderId);
//	        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
//	                .status(HttpStatus.OK)
//	                .message("order is removed !!")
//	                .success(true)
//	                .build();
	        ApiResponseMessage message=new ApiResponseMessage();
	        message.setMessage("order is removed");
	        message.setStatus(HttpStatus.OK);
	        message.setSuccess(true);
	        
	        
	        
	        
	        return new ResponseEntity<>(message, HttpStatus.OK);

	    }

	    //get orders of the user

	    @GetMapping("/users/{userId}")
	    public ResponseEntity<List<OrderDto>> getOrdersOfUser(@PathVariable String userId) {
	        List<OrderDto> ordersOfUser = orderService.getOrdersOfUser(userId);
	        return new ResponseEntity<>(ordersOfUser, HttpStatus.OK);
	    }

	    @GetMapping
	    public ResponseEntity<PageableResponse<OrderDto>> getOrders(
	            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
	            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
	            @RequestParam(value = "sortBy", defaultValue = "orderedDate", required = false) String sortBy,
	            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
	    ) {
	        PageableResponse<OrderDto> orders = orderService.getOrders(pageNumber, pageSize, sortBy, sortDir);
	        return new ResponseEntity<>(orders, HttpStatus.OK);
	    }



}
