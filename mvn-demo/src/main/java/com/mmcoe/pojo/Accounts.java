
package com.mmcoe.pojo;

public class Accounts {
	private double balance;
	
	public Accounts(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double deposit(double amount) {
		if(amount < 0) {
			throw new NumberFormatException("Negative amount");
		}
		balance += amount;
		return balance;
		
	}
	
	public double withdraw(double amount) throws BalanceException{ 
		if(amount < 0) 
			throw new NumberFormatException("Negative amount");
			
		if (amount > balance) 
			throw new BalanceException("Insufficient Funds");
		
			
			balance -= amount;
			return balance;
	
		}
		
}

