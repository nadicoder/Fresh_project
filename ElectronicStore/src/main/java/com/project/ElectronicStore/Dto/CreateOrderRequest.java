package com.project.ElectronicStore.Dto;


	
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;

import jakarta.validation.constraints.NotBlank;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@ToString
	public class CreateOrderRequest {

	    @NotBlank(message = "Cart id is required !!")
	    private String cartId;

	    @NotBlank(message = "Cart id is required !!")
	    private String userId;


	    private String orderStatus = "PENDING";
	    private String paymentStatus = "NOTPAID";
	    @NotBlank(message = "Address is required !!")
	    private String billingAddress;
	    public CreateOrderRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		public CreateOrderRequest(@NotBlank(message = "Cart id is required !!") String cartId,
				@NotBlank(message = "Cart id is required !!") String userId, String orderStatus, String paymentStatus,
				@NotBlank(message = "Address is required !!") String billingAddress,
				@NotBlank(message = "Phone number is required !!") String billingPhone,
				@NotBlank(message = "Billing name  is required !!") String billingName) {
			super();
			this.cartId = cartId;
			this.userId = userId;
			this.orderStatus = orderStatus;
			this.paymentStatus = paymentStatus;
			this.billingAddress = billingAddress;
			this.billingPhone = billingPhone;
			this.billingName = billingName;
		}
		@Override
		public String toString() {
			return "CreateOrderRequest [cartId=" + cartId + ", userId=" + userId + ", orderStatus=" + orderStatus
					+ ", paymentStatus=" + paymentStatus + ", billingAddress=" + billingAddress + ", billingPhone="
					+ billingPhone + ", billingName=" + billingName + "]";
		}
		public String getCartId() {
			return cartId;
		}
		public void setCartId(String cartId) {
			this.cartId = cartId;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}
		public String getPaymentStatus() {
			return paymentStatus;
		}
		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}
		public String getBillingAddress() {
			return billingAddress;
		}
		public void setBillingAddress(String billingAddress) {
			this.billingAddress = billingAddress;
		}
		public String getBillingPhone() {
			return billingPhone;
		}
		public void setBillingPhone(String billingPhone) {
			this.billingPhone = billingPhone;
		}
		public String getBillingName() {
			return billingName;
		}
		public void setBillingName(String billingName) {
			this.billingName = billingName;
		}
		@NotBlank(message = "Phone number is required !!")
	    private String billingPhone;
	    @NotBlank(message = "Billing name  is required !!")
	    private String billingName;


	}



