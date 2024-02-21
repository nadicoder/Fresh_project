package com.project.ElectronicStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ElectronicStore.Dto.ApiResponseMessage;
import com.project.ElectronicStore.Dto.CartDto;
import com.project.ElectronicStore.Entities.Cart;
import com.project.ElectronicStore.Helper.AddItemToCartRequest;
import com.project.ElectronicStore.Servicelayer.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	//post
	//delete
	//clear
	//getting by user
	@Autowired
	private CartService cartService;
	@PostMapping("/{userId}")
	public ResponseEntity<CartDto> create(@RequestBody AddItemToCartRequest addItem,@PathVariable String userId)
	{
		CartDto cartDto=cartService.addItemToCart(userId, addItem);
		//System.out.println(addItem+"  "+userId);
		return new ResponseEntity<CartDto>(cartDto,HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping("/{userId}/delete/{cartId}")
	public ResponseEntity<ApiResponseMessage> delete(@PathVariable String userId,@PathVariable Integer cartId)
	{
		cartService.removeItemFromCart(userId, cartId);
		ApiResponseMessage message=new ApiResponseMessage();
		message.setMessage("cartitem got deleted successfull");
		message.setStatus(HttpStatus.OK);
		message.setSuccess(true);
		return new ResponseEntity<ApiResponseMessage>(message,HttpStatus.OK);
	}
	
	@DeleteMapping("/clear/{userId}")
	public ResponseEntity<ApiResponseMessage> clearCart(@PathVariable String userId)
	{
		cartService.clearCart(userId);
		ApiResponseMessage message=new ApiResponseMessage();
		message.setMessage("Cart got cleared");
		message.setStatus(HttpStatus.OK);
		message.setSuccess(true);
		return new ResponseEntity<ApiResponseMessage>(message,HttpStatus.OK);
	}
	
	

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId) {
        CartDto cartDto = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
	
	

}
