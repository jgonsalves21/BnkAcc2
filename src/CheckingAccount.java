
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;
	
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
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
		
	public void transfer(BankAccount other, double amt)
	{
		if (other.GetName().equals(super.GetName()))
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
	
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
	
}
	

