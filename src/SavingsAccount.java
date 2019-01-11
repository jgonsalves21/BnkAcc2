
public class SavingsAccount extends BankAccount 
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	public void withdraw(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException("You cannot withdraw a negative amount.");
		}
		super.withdraw(amt);
		if (super.getBalance() < 0)
		{
			super.deposit(amt);
			throw new IllegalArgumentException("Your balance will go negative. This transaction cannot occur.");
		}
		if (super.getBalance() < MIN_BAL)
		{
			super.withdraw(MIN_BAL_FEE);
		}	
	}
	
	public void transfer(BankAccount other, double amt)
	{
		if (other.getName().equals(super.getName()))
		{
			super.withdraw(amt);
			if (super.getBalance() > 0)
			{
				other.deposit(amt);
			}
			else
			{
				super.deposit(amt);
				throw new IllegalArgumentException("The account balance becomes negative after transferring. Please enter a valid amount.");
			}
		}
		else
		{
			throw new IllegalArgumentException("The owner of the bank accounts does not match. Please provide a correct account.");
		}
	}
	
	public void addInterest()
	{
		this.deposit(this.getBalance()*intRate);
	}
	
	public void endOfMonthUpdate()
	{
		addInterest();
	}
}
