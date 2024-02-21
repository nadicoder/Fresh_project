package com.project.ElectronicStore.Dto;

import com.project.ElectronicStore.Entities.Product;

public class CartItemDto {
	
	private int cartItemId;
	private ProductDto productDto;
	private int quantity;
	@Override
	public String toString() {
		return "CartItemDto [cartItemId=" + cartItemId + ", productDto=" + productDto + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + "]";
	}
	public CartItemDto(int cartItemId, ProductDto productDto, int quantity, int totalPrice) {
		super();
		this.cartItemId = cartItemId;
		this.productDto = productDto;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public int getCartItemId() {
		return cartItemId;
	}
	public CartItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public ProductDto getProductDto() {
		return productDto;
	}
	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	private int totalPrice;
	

}
