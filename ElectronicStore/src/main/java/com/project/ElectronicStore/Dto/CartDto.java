package com.project.ElectronicStore.Dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDto {
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartDto(String cartId, Date date, List<CartItemDto> cartItems, UserDto userDto) {
		super();
		this.cartId = cartId;
		this.date = date;
		this.cartItems = cartItems;
		this.userDto = userDto;
	}
	public Date getDate() {
		return date;
	}
	@Override
	public String toString() {
		return "CartDto [cartId=" + cartId + ", date=" + date + ", cartItems=" + cartItems + ", userDto=" + userDto
				+ "]";
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<CartItemDto> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	private String cartId;
	private Date date;
	private List<CartItemDto> cartItems=new ArrayList<CartItemDto>();
	private UserDto userDto;
}
