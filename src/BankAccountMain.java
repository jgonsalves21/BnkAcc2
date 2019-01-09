
import java.util.Scanner;
import java.util.ArrayList;
public class BankAccountMain {

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		ArrayList<BankAccount> BankAccounts = new ArrayList<BankAccount>();
		double OVER_DRAFT_FEE = 15;
		double RATE = .0025;
		double TRANSACTION_FEE = 1.5;
		double MIN_BAL = 300;
		double MIN_BAL_FEE = 10;
		int FREE_TRANSACTIONS = 10;
		Boolean terminate = false;
		
		while (!terminate)
		{
			System.out.println("Enter 'new' for a new account, 'transaction' for a transaction, or 'terminate' to terminate:" );
			String str = in.next();
			in.nextLine();
			if (str.equals("new"))
			{
				System.out.println("Enter 'check' for a checking account, and 'save' for a savings account (Case sensitive):");
				String accType = in.next();
				in.nextLine();
				boolean continue1 = true;
				while (continue1)
				{
					if (accType.equals("check"))
					{
						String name;
						System.out.println("Please enter the name of the owner of the bank account: ");
						name = in.nextLine();
						boolean continue4 = true;
						while (continue4)
						{
							for (int i = 0; i < name.length(); i++)
							{
								
							}
						}
						
						double bal;
						System.out.println("Would you like a balance of zero? Enter 'Y' if yes and 'N' if not:");
						String balChoice = in.next();
						in.nextLine();
						boolean continue2 = true;
						while (continue2)
						{
							if (balChoice.equals("Y") || balChoice.equals("y"))
							{
								bal = 0;
								continue2 = false;
							}
							else if (balChoice.equals("N") || balChoice.equals("n"))
							{
								System.out.println("What would you like the balance to be: ");
								bal = in.nextDouble();
								continue2 = false;
							}
							else
							{
								System.out.println("The input " + str + " is not a valid input. Please enter a valid one to continue.");
								balChoice = in.next();
								in.nextLine();
							}
						}
						
					}
						else  if (accType.equals("save"))
					{
						System.out.println("Would you like a balance of zero? Enter 'Y' if yes and 'N' if not (Case sensitive):");
						String balChoice = in.next();
						in.nextLine();
					}
					else 
					{
						System.out.println("The input " + str + " is not a valid input. Please enter a valid one to continue.");
						System.out.println("Enter 'check' for a checking account, and 'save' for a savings account (Case sensitive):");
						accType = in.next();
						in.nextLine();
					}
				}
			}
			else if (str.equals("transaction"))
			{
				System.out.println("Transfer");
			}
			else if (str.equals("terminate"))
			{
				terminate = !terminate;	
			}
			else
			{
				System.out.println("The input " + str + " is not a valid input. Please enter a valid one to continue.");
				System.out.println("Enter 1 for a new account, 2 for a transaction, or 3 to terminate:" );
				str = in.next();
				in.nextLine();
			}
		}
	}

}
