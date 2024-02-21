package com.project.ElectronicStore.Servicelayer;


import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ElectronicStore.Dto.CartDto;
import com.project.ElectronicStore.Dto.CartItemDto;
import com.project.ElectronicStore.Dto.ProductDto;
import com.project.ElectronicStore.Dto.UserDto;
import com.project.ElectronicStore.Entities.Cart;
import com.project.ElectronicStore.Entities.CartItem;
import com.project.ElectronicStore.Entities.Product;
import com.project.ElectronicStore.Entities.User;
import com.project.ElectronicStore.Exceptions.ResourceNotFoundException;
import com.project.ElectronicStore.Helper.AddItemToCartRequest;
import com.project.ElectronicStore.Repository.CartItemRepository;
import com.project.ElectronicStore.Repository.CartRepository;
import com.project.ElectronicStore.Repository.ProductRepository;
import com.project.ElectronicStore.Repository.UserRepository;


@Service
public class CartServiceImpel implements CartService {

	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	private Logger logger;
	
	
	
	@Override
	public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
		// TODO Auto-generated method stub
		
		
		  int quantity = request.getQuantity();
	        String productId = request.getProductId();

	        if (quantity <= 0) {
	            throw new ResourceNotFoundException("Quantiy is not valid increase it");
	        }

	        //fetch the product
	        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));
	        //fetch the user from db
	        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found in database!!"));

	        
	        
	        
	        
	        
	        
	        //checking if the user has cart if not create it.,
	        
	        
	        Cart cart = null;
	        try {
	            cart = cartRepository.findByUser(user).get();
	        } catch (NoSuchElementException e) {
	            cart = new Cart();
	            cart.setCartId(UUID.randomUUID().toString());
	            cart.setDate(new Date());
	        }
	        

	        //perform cart operations
	        //if cart items already present; then update
	        
	        
	        AtomicReference<Boolean> updated = new AtomicReference<>(false);
	        List<CartItem> items = cart.getCartItems();
	        items = items.stream().map(item -> {

	            if (item.getProduct().getProductId().equals(productId)) {
	                //item already present in cart
	                item.setQuantiy(quantity);
	                item.setTotalPrice(quantity * product.getDiscountedPrice());
	                updated.set(true);
	            }
	            return item;
	        }).collect(Collectors.toList());

//	        cart.setItems(updatedItems);
	        logger=LoggerFactory.getLogger(CartService.class);
        	
        	
        	

	        //create items
	        if (!updated.get()) {
//	            CartItem cartItem = CartItem.builder()
//	                    .quantity(quantity)
//	                    .totalPrice(quantity * product.getDiscountedPrice())
//	                    .cart(cart)
//	                    .product(product)
//	                    .build();
	        	
	        	CartItem cartItem = new CartItem();
	        	cartItem.setQuantiy(quantity);
	        	cartItem.setTotalPrice(quantity*product.getDiscountedPrice());
	        	cartItem.setCart(cart);
	        	cartItem.setProduct(product);
	        	logger.info("  "+product);
	        	
	        	System.out.println("++++++++++++++"+product+"++++++++++++++++");
	        	
	        	
	        	CartItemDto cartItemDto=new CartItemDto();
	        	cartItemDto.setProductDto(mapper.map(product, ProductDto.class));
	            cart.getCartItems().add(cartItem);
	        }


	        cart.setUser(user);
	        Cart updatedCart = cartRepository.save(cart);
	        
	        
	        //System.out.println("  ++++++++++++="+updatedCart);
	        
	        CartDto cartDto=mapper.map(updatedCart, CartDto.class);
	        cartDto.setUserDto(mapper.map(user, UserDto.class));
	        return cartDto;

		
	}

	@Override
	public void removeItemFromCart(String userId, int cartItemId) {
		// TODO Auto-generated method stub
		CartItem cart=cartItemRepository.findById(cartItemId).orElseThrow(()->new ResourceNotFoundException("Cart your researching doesnt exist"));
		cartItemRepository.delete(cart);
		
	}

	@Override
	public void clearCart(String userId) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
		Cart cart=cartRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("cart not found"));
		cart.getCartItems().clear();
		cartRepository.save(cart);
		
	}

	@Override
	public CartDto getCartByUser(String userId) {
		// TODO Auto-generated method stub
		
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
		Cart cart=cartRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("Cart Not found"));
		
		
		return mapper.map(cart, CartDto.class);
	}

}
