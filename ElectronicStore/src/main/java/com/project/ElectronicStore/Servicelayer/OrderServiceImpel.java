package com.project.ElectronicStore.Servicelayer;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ElectronicStore.Dto.CreateOrderRequest;
import com.project.ElectronicStore.Dto.OrderDto;
import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Entities.Cart;
import com.project.ElectronicStore.Entities.CartItem;
import com.project.ElectronicStore.Entities.Order;
import com.project.ElectronicStore.Entities.OrderItem;
import com.project.ElectronicStore.Entities.User;
import com.project.ElectronicStore.Exceptions.BadApiRequestException;
import com.project.ElectronicStore.Exceptions.ResourceNotFoundException;
import com.project.ElectronicStore.Helper.Helper;
import com.project.ElectronicStore.Repository.CartRepository;
import com.project.ElectronicStore.Repository.OrderRepository;
import com.project.ElectronicStore.Repository.UserRepository;



@Service
public class OrderServiceImpel implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OrderDto createOrder(CreateOrderRequest orderDto) {
		// TODO Auto-generated method stub
		String userId = orderDto.getUserId();
        String cartId = orderDto.getCartId();
        //fetch user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));

        //fetch cart
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart with given id not found on server !!"));

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems.size() <= 0) {
            throw new BadApiRequestException("Invalid number of items in cart !!");

        }
        
        
        /* Enter Understanding of create logic is:
         * for input considering individual units as one unit
         * shifting order repo to dto and next converting cartitems to order items
         * 
         * */
        
        

        //other checks

//        Order order = Order.builder()
//                .billingName(orderDto.getBillingName())
//                .billingPhone(orderDto.getBillingPhone())
//                .billingAddress(orderDto.getBillingAddress())
//                .orderedDate(new Date())
//                .deliveredDate(null)
//                .paymentStatus(orderDto.getPaymentStatus())
//                .orderStatus(orderDto.getOrderStatus())
//                .orderId(UUID.randomUUID().toString())
//                .user(user)
//                .build();

//        orderItems,amount
        Order order=new Order();
        order.setBillingName(orderDto.getBillingName());
        order.setBillingPhone(orderDto.getBillingPhone());
        order.setBillingAddress(orderDto.getBillingAddress());
        order.setOrderedDate(new Date());
        order.setPaymentStatus(orderDto.getPaymentStatus());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderId(UUID.randomUUID().toString());
        order.setUser(user);
        
        
        

        AtomicReference<Integer> orderAmount = new AtomicReference<>(0);
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
//            CartItem->OrderItem
//            OrderItem orderItem = OrderItem.builder()
//                    .quantity(cartItem.getQuantiy())
//                    .product(cartItem.getProduct())
//                    .totalPrice(cartItem.getQuantiy() * cartItem.getProduct().getDiscountedPrice())
//                    .order(order)
//                    .build();
        	OrderItem orderItem=new OrderItem();
        	orderItem.setQuantity(cartItem.getQuantiy());
        	orderItem.setProduct(cartItem.getProduct());
        	orderItem.setTotalPrice(cartItem.getQuantiy() * cartItem.getProduct().getDiscountedPrice());
        	orderItem.setOrder(order);

            orderAmount.set(orderAmount.get() + orderItem.getTotalPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setOrderAmount(orderAmount.get());

        //
        cart.getCartItems().clear();
        cartRepository.save(cart);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }
		

	@Override
	public void removeOrder(String orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order is not found !!"));
        orderRepository.delete(order);

		
		
		
	}

	@Override
	public List<OrderDto> getOrdersOfUser(String userId) {
		// TODO Auto-generated method stub
		 User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found !!"));
	        List<Order> orders = orderRepository.findByUser(user);
	        List<OrderDto> orderDtos = orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
	        return orderDtos;
		
	}

	@Override
	public PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		
		 Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
	        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	        Page<Order> page = orderRepository.findAll(pageable);
	        return Helper.getPageableResponse(page, OrderDto.class);

	}

}
