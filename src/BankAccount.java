
public class BankAccount 
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
	
	public void Deposit(double amt)
	{
		this.balance = balance + amt;
	}
	
	public void Withdraw(double amt)
	{
		this.balance = balance - amt;
	}
	
	public String GetName()
	{
		return name;
	}
}
