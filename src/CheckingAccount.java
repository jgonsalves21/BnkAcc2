/**
* @author Joshua Gonsalves
* class CheckingAccount
*
*/
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;
	
	/**
    * Constructor Checking Account
    * Creates a checking account with params:
    *  
	* @param n: Name of account owner
	* @param b: Balance of account
    * @param odf: Overdraft fee of the account
	* @param tf: Transaction fee of account
	* @param freeTrans: Number of free transactions 
        */
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
    * Constructor Checking Account
    * Creates a checking account with balance of 0 and params:
    *  
	* @param n: Name of account owner
    * @param odf: Overdraft fee of the account
	* @param tf: Transaction fee of account
	* @param freeTrans: Number of free transactions 
	*/
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
     * Method deposit: deposits param amt into account
     * Overrides superclass and takes a transaction fee into account
     *
     * @param amt   Amount of money to be deposited
     */
	public void deposit(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException("You cannot deposit a negative amount.");
		}
		super.deposit(amt);
		numTransactions ++;
		if  (numTransactions > FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		}
	}
	
	/**
    * Method withdraw: Withdraws param amt from account
    * Overrides superclass and takes a transaction fee into account
    *
    * @param amt   Amount of money to be withdrawn
    */
	public void withdraw(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException("You cannot withdraw a negative amount.");
		}
		if (super.getBalance() > 0)
		{
			super.withdraw(amt);
			numTransactions ++;
			if (numTransactions > FREE_TRANS)
			{
				super.withdraw(TRANSACTION_FEE);
			}
			if (super.getBalance() < 0)
			{
				super.withdraw(OVER_DRAFT_FEE);
			}
		}
		else
		{
			throw new IllegalArgumentException("Your balance is negative. You may not make a withdrawal until it is positive.");
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
			if (super.getBalance() < 0)
			{
				super.deposit(amt);
				throw new IllegalArgumentException("The account balance becomes negative after transferring. Please enter a valid amount.");
			}
			else
			{
				other.deposit(amt);
				numTransactions ++;
				if (numTransactions > FREE_TRANS)
				{
					super.withdraw(TRANSACTION_FEE);
				}
			}
		}
		else
		{
			throw new IllegalArgumentException("The owner of the bank accounts does not match. Please provide a correct account.");
		}
	}
	
	/**
	 * Method endOfMonthUpdate: Resets numTransactions to 0
	 * 
	 */
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
	
}
	

