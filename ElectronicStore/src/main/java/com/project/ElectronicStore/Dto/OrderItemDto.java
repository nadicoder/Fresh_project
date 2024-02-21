package com.project.ElectronicStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public class OrderItemDto {


	    private int orderItemId;

	    private int quantity;

	    private int totalPrice;

	    private ProductDto product;


	}



