package com.project.ElectronicStore.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Cart {
	
	@Id
	private String cartId;
	private Date date;
	@OneToOne
	private User user;
	@OneToMany(mappedBy="cart",fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems=new ArrayList<>();
	
	public Cart(String cartId, Date date, User user, List<CartItem> cartItems) {
		super();
		this.cartId = cartId;
		this.date = date;
		this.user = user;
		this.cartItems = cartItems;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", date=" + date + ", user=" + user + ", cartItems=" + cartItems + "]";
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
}
