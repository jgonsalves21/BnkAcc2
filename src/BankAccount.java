
public abstract class BankAccount 
{
	// Fields
	private static int nextAccNum = 0;
	private String name;
	private int accNum;
	private double balance;
	
	/**
	 * BankAccount: Creates an object with params:
	 * 
	 * @param n		Name of BankAccount
	 * Balance is set to 0
	 * Increases accNum by 1 every time a new BankAccount is created
	 */
	public BankAccount(String n)
	{
		this.balance = 0;
		this.name = n;
		nextAccNum++;
		accNum= nextAccNum;
	}
	
	/**
	 * BankAccount:	Creates an object with params:
	 * 
	 * @param n		Name of BankAccount
	 * @param b		Balance of BankAccount
	 */
	public BankAccount(String n, double b)
	{
		this.balance = b;
		this.name = n;
		nextAccNum++;
		accNum= nextAccNum;
	}
	
	public void deposit(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException("You cannot deposit a negative amount.");
		}
		this.balance = balance + amt;
	}
	
	public void withdraw(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException("You cannot withdraw a negative amount.");
		}
		this.balance = balance - amt;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public int getAccNum()
	{
		return accNum;
	}
	
	public abstract void endOfMonthUpdate();
	
	public void transfer(BankAccount other, double amt)
	{
		this.balance = balance - amt;
		other.deposit(amt);
	}
	
	public String toString()
	{
		return accNum + "\t" + name + "\t $ " + balance; 
	}

}
