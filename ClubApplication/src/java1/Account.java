package java1;

public class Account {
	private String number;
	private double balance;
	
	public Account(String number, double balance) {
		this.number = number;
		this.balance = balance;
	}
	
	public Account() {
		this ("1000", 1000);
	}
	
	public String getNumber() {
		return number;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void deposit(double amount) {
		if(amount > 0.0) {
			balance += amount;
		}
	}
}
