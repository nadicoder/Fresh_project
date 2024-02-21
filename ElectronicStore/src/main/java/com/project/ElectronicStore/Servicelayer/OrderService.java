package com.project.ElectronicStore.Servicelayer;

import java.util.List;

import com.project.ElectronicStore.Dto.CreateOrderRequest;
import com.project.ElectronicStore.Dto.OrderDto;
import com.project.ElectronicStore.Dto.PageableResponse;

public interface OrderService {
	
	
	
	//create 
	//get orders by user
	//get order
	//remove orders
	OrderDto createOrder(CreateOrderRequest orderDto);

    //remove order
    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);

    //order methods(logic) related to order


}
