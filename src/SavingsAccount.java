/**
* @author Joshua Gonsalves
* class SavingsAccount
*
*/
public class SavingsAccount extends BankAccount 
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	/**
	 * Constructor SavingsAccount: Creates an account with params:
	 * 
	 * @param n		Name of account
	 * @param b		Balance of account
	 * @param r		Interest rate of account
	 * @param mb	Minimum balance of account
	 * @param mbf	Minimum balance fee of account
	 */
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * Constructor SavingsAccount: Creates an account with bal 0 and params:
	 * 
	 * @param n		Name of account
	 * @param r		Interest rate of account
	 * @param mb	Minimum balance of account
	 * @param mbf	Minimum balance fee of account
	 */
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
    * Method withdraw: Withdraws param amt from account
    * Overrides superclass and takes a min bal into account
    *
    * @param amt   Amount of money to be withdrawn
    */
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
	
	/**
	 * Method transfer: Transfers money between two accounts using params:
	 * 
	 * @param other		Second bank account
	 * @param amt		Amount of money to be transferred
	 */
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
	
	/**
	 * Method addInterest: Deposits money calculated using intRate into the account
	 * 
	 */
	public void addInterest()
	{
		this.deposit(this.getBalance()*intRate);
	}
	
	/**
	 * Method endOfMonthUpdate: Adds interest to the account
	 * 
	 */
	public void endOfMonthUpdate()
	{
		addInterest();
	}
}
