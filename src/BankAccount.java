/**
* @author Joshua Gonsalves
* class BankAccount
*
*/
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
	
    /**
    * Method deposit: deposits param amt into account
    *
    *
    * @param amt   Amount of money to be deposited
    */
	public void deposit(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException("You cannot deposit a negative amount.");
		}
		this.balance = balance + amt;
	}
	
    /**
    * Method withdraw: Withdraws param amt into account
    *
    *
    * @param amt   Amount of money to be withdrawn
    */
	public void withdraw(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException("You cannot withdraw a negative amount.");
		}
		this.balance = balance - amt;
	}
	
    /**
    * Method getName
    * 
    * Returns the owner of the BankAccount
    * 
    */
	public String getName()
	{
		return name;
	}
	
    /**
    * Method getBalance
    * 
    * Returns the balance of the BankAccount
    * 
    */
	public double getBalance()
	{
		return balance;
	}
	
    /**
    * Method getAccNum
    * 
    * Returns the account number
    * 
    */
	public int getAccNum()
	{
		return accNum;
	}
	
    /**
    * Creates an abstract method for the subclasses
    *
    */
	public abstract void endOfMonthUpdate();
	
    /**
    * Method transfer: Takes params other and amt to withdraw from one acc and deposit in another
    * 
    * @param other   Account from which money is deposited
    * @param amt     Account of money to be transferred
    */
	public void transfer(BankAccount other, double amt)
	{
		this.balance = balance - amt;
		other.deposit(amt);
	}
	
    /**
    * Method toString
    * Converts information of account to a string
    */
	public String toString()
	{
		return accNum + "\t" + name + "\t $ " + balance; 
	}

}
