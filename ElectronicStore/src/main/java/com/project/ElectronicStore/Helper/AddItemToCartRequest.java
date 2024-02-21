package com.project.ElectronicStore.Helper;

import com.project.ElectronicStore.Dto.ProductDto;

public class AddItemToCartRequest {
	private String  productId;
	private int quantity;
	public String getProductId() {
		return productId;
	}
	public AddItemToCartRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddItemToCartRequest(String productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "AddItemToCartRequest [productId=" + productId + ", quantity=" + quantity + "]";
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
