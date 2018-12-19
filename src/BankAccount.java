
public abstract class BankAccount 
{
	private static int nextAccNum;
	private String name;
	private int accNum = 0;
	private double balance;
	
	public BankAccount(String n)
	{
		this.balance = 0;
		this.name = n;
		nextAccNum = accNum + 1;
		accNum = nextAccNum;
	}
	
	public BankAccount(String n, double b)
	{
		this.balance = b;
		this.name = n;
		nextAccNum = accNum + 1;
		accNum = nextAccNum;
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
	
	public String GetName()
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
