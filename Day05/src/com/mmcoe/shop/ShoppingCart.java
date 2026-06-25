package com.mmcoe.shop;

public class ShoppingCart {
	private LinkedList<Product> items;
	private double total;
    private int CAPACITY = 5;

	
	public ShoppingCart() {
		items = new LinkedList<>();
	}
	
	public void addToCart(Product P) {
		if(items.size() == CAPACITY) {
			System.out.println("Cart is full");
			return;
		}
		items.add(P);
		total += P.getPrice();
		System.out.println(P +"Added to cart");
		
	}
	
    public void checkout() {
    	System.out.println("Items in Cart:");
        items.print();
        System.out.println("Total Amount = " + total);
    }
}
