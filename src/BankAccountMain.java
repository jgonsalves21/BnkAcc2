/**
* @author Joshua Gonsalves
* class BankAccountMain
*
*/
import java.util.Scanner;
import java.util.ArrayList;
public class BankAccountMain {

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		double OVER_DRAFT_FEE = 15;
		double RATE = .0025;
		double TRANSACTION_FEE = 1.5;
		double MIN_BAL = 300;
		double MIN_BAL_FEE = 10;
		int FREE_TRANSACTIONS = 10;
		boolean terminate = false;
		
		while (!terminate)
		{
		System.out.println("Enter 'A' for a new account, 'T' for a transaction, or 'TRM' to terminate:" );
		String str = in.next();
		in.nextLine();
		switch(str)
		{
			case "A":
			{
				boolean accPick = true;
				while (accPick)
				{
					System.out.println("Enter 'check' for a checking account, and 'save' for a savings account:");
					String accType = in.next();
					in.nextLine();
					switch(accType)
					{
						case "check":
						{
							String name;
							System.out.println("Please enter the name of the owner of the bank account: ");
							name = in.nextLine();
		
							double bal = 0;
							System.out.println("What would you like the balance to be? Please enter it here: ");
							String balChoice = in.next();
							in.nextLine();
							while (!isNumeric(balChoice))
							{
								System.out.println("An invalid input was entered. What would you like the balance to be? Please enter it here: ");
								balChoice = in.next();
								in.nextLine();	
							}
							if (isNumeric(balChoice))
							{
								bal = Double.parseDouble(balChoice);
							}
							
							bankAccounts.add(new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
							System.out.println("Your account has been made. \n");
							accPick = false;
							break;
						}
						case "save":
						{
							String name;
							System.out.println("Please enter the name of the owner of the bank account: ");
							name = in.nextLine();
		
							double bal = 0;
							System.out.println("What would you like the balance to be? Please enter it here: ");
							String balChoice = in.next();
							in.nextLine();
							while (!isNumeric(balChoice))
							{
								System.out.println("An invalid input was entered. What would you like the balance to be? Please enter it here: ");
								balChoice = in.next();
								in.nextLine();	
							}
							if (isNumeric(balChoice))
							{
								bal = Double.parseDouble(balChoice);
							}
							
							bankAccounts.add(new SavingsAccount(name, bal, RATE, MIN_BAL, MIN_BAL_FEE));
							System.out.println("Your account has been made. \n");
							accPick = false;
							break;
						}
						default:
						{
							break;
						}
					}
					
				}
				break;
			}
				case "T":
				{
					if (bankAccounts.size() == 0)
					{
						System.out.println("There are no bank accounts avaliable. Please make one before proceeding.");
						break;
					}
					else
					{
						boolean tChoice = true;
						while (tChoice)
						{
						System.out.println("To withdraw, enter 'W'. To deposit, enter 'D'. To transfer, enter 'T'. To get an account number, enter 'A': ");
						String transChoice = in.next();
						in.nextLine();
						while (isNumeric(transChoice))
						{
							System.out.println("That was an invalid input. To withdraw, enter 'W'. To deposit, enter 'D'. To transfer, enter 'T'. To get an account number, enter 'A': ");
							transChoice = in.next();
							in.nextLine();
						}
						switch(transChoice)
						{
							case "W":
							{
								int accNum = 0;
								BankAccount desiredAcc;
								boolean valid = false;
								while (!valid)
								{
									System.out.println("Please enter the account number from which you want make changes: ");
									String accNumStr = in.next();
									in.nextLine();
									while (!isNumeric(accNumStr))
									{
										System.out.println("An invalid input was entered. Please enter the account number again: ");
										accNumStr = in.next();
										in.nextLine();	
									}
									if (isNumeric(accNumStr))
									{
										accNum = Integer.parseInt(accNumStr);
									}
									for (BankAccount acc : bankAccounts)
									{
										if (acc.getAccNum() == accNum)
										{
											desiredAcc = acc;
											valid = true;
										}
									}
									if (valid == false)
									{
										System.out.println("You have entered an invalid account number. If you want to re-enter your number, press 'R', and if you want to look at all of your accounts, press 'L': ");
										String accNumChoice = in.next();
										in.nextLine();
										switch(accNumChoice)
										{
										case"R":
										{
											break;
										}
										case "L":
										{
											String accs = "";
											boolean invalidName = true;
											System.out.println("Please enter your name: ");
											String name1 = in.nextLine();
											while (invalidName)
											{
											for (BankAccount acc : bankAccounts)
											{
												if (name1.equals(acc.getName()))
												{
													if (getType(acc))
													accs = accs + "Savings Account: " + acc.toString() + "\n";
													else if (!getType(acc))
													{
													accs = accs + "Checking Account: " + acc.toString() + "\n";
													}
												}
											}
											if (accs.equals(""))
											{
												System.out.println("Your entry is invalid. Please enter your name again: ");
												name1 = in.next();
												in.nextLine();
											}
											else
											{
												System.out.println(accs);
												invalidName = false;
											}
											}
											break;
										}
										default:
										{
											break;
										}
										}
									}	
								}
								double amt = 0;
								System.out.println("Please enter how much money you would like to withdraw: ");
								String amtChoice = in.next();
								in.nextLine();
								while (!isNumeric(amtChoice))
								{
									System.out.println("An invalid input was entered. How much would you like to withdraw? Please enter it here: ");
									amtChoice = in.next();
									in.nextLine();	
								}
								if (isNumeric(amtChoice))
								{
									amt = Double.parseDouble(amtChoice);
								}
								
								try
								{
									bankAccounts.get(accNum-1).withdraw(amt);
									String display = "";
									if (getType(bankAccounts.get(accNum-1)))
									{
										display = display + "Savings Account: " + bankAccounts.get(accNum-1).toString() + "\n";
									}
									else if (!getType(bankAccounts.get(accNum-1)))
									{
										display = display + "Checking Account: " + bankAccounts.get(accNum-1).toString() + "\n";
									}
									System.out.println(display);
								}
								catch(Exception err)
								{
									err.getMessage();
									System.out.println("Transaction not authorized");
								}
								tChoice = false;
								break;
							}
							case "D":
							{
								int accNum = 0;
								BankAccount desiredAcc;
								boolean valid = false;
								while (!valid)
								{
									System.out.println("Please enter the account number from which you want make changes: ");
									String accNumStr = in.next();
									in.nextLine();
									while (!isNumeric(accNumStr))
									{
										System.out.println("An invalid input was entered. Please enter the account number again: ");
										accNumStr = in.next();
										in.nextLine();	
									}
									if (isNumeric(accNumStr))
									{
										accNum = Integer.parseInt(accNumStr);
									}
									for (BankAccount acc : bankAccounts)
									{
										if (acc.getAccNum() == accNum)
										{
											desiredAcc = acc;
											valid = true;
										}
									}
									if (valid == false)
									{
										System.out.println("You have entered an invalid account number. If you want to re-enter your number, press 'R', and if you want to look at all of your accounts, press 'L': ");
										String accNumChoice = in.next();
										switch(accNumChoice)
										{
										case"R":
										{
											break;
										}
										case "L":
										{
											String accs = "";
											boolean invalidName = true;
											System.out.println("Please enter your name: ");
											String name1 = in.nextLine();
											name1 = in.nextLine();
											while (invalidName)
											{
											for (BankAccount acc : bankAccounts)
											{
												if (name1.equals(acc.getName()))
												{
													if (getType(acc))
													accs = accs + "Savings Account: " + acc.toString() + "\n";
													else if (!getType(acc))
													{
													accs = accs + "Checking Account: " + acc.toString() + "\n";
													}
												}
											}
											if (accs.equals(""))
											{
												System.out.println("Your entry is invalid. Please enter your name again: ");
												name1 = in.next();
												in.nextLine();
											}
											else
											{
												System.out.println(accs);
												invalidName = false;
											}
											}
											break;
										}
										default:
										{
											continue;
										}
										}
									}	
								}
								double amt = 0;
								System.out.println("Please enter how much money you would like to deposit: ");
								String amtChoice = in.next();
								in.nextLine();
								while (!isNumeric(amtChoice))
								{
									System.out.println("An invalid input was entered. How much would you like to deposit? Please enter it here: ");
									amtChoice = in.next();
									in.nextLine();	
								}
								if (isNumeric(amtChoice))
								{
									amt = Double.parseDouble(amtChoice);
								}
								
								try
								{
									bankAccounts.get(accNum-1).deposit(amt);
									String display = "";
									if (getType(bankAccounts.get(accNum-1)))
									{
										display = display + "Savings Account: " + bankAccounts.get(accNum-1).toString() + "\n";
									}
									else if (!getType(bankAccounts.get(accNum-1)))
									{
									display = display + "Checking Account: " + bankAccounts.get(accNum-1).toString() + "\n";
									}
									System.out.println(display);
								}
								catch(Exception err)
								{
									err.getMessage();
									System.out.println("Transaction not authorized");
								}
								tChoice = false;
								break;
							}
							case "T":
							{
								if (bankAccounts.size() <= 1)
								{
									System.out.println("There is only one bank account avaliable. Please make another before proceeding.");
									break;
								}
								int accNum1 = 0;
								int accNum2 = 0;
								for (int i = 0; i < 2; i++)
								{
									int accNum = 0;
									BankAccount desiredAcc;
									boolean valid = false;
									while (!valid)
									{
										String accNumStr = "";
										if (i == 0)
										{
											System.out.println("Please enter the account number from which you want to withdraw money: ");
											accNumStr = in.next();
											in.nextLine();
										}
										else
										{
											System.out.println("Please enter the account number from which you want to deposit money: ");
											accNumStr = in.next();
											in.nextLine();
										}
										while (!isNumeric(accNumStr))
										{
											System.out.println("An invalid input was entered. Please enter the account number again: ");
											accNumStr = in.next();
											in.nextLine();	
										}
										if (isNumeric(accNumStr))
										{
											accNum = Integer.parseInt(accNumStr);
										}
										for (BankAccount acc : bankAccounts)
										{
											if (acc.getAccNum() == accNum)
											{
												desiredAcc = acc;
												valid = true;
											}
										}
										if (valid == false)
										{
											System.out.println("You have entered an invalid account number. If you want to re-enter your number, press 'R', and if you want to look at all of your accounts, press 'L': ");
											String accNumChoice = in.next();
											in.nextLine();
											switch(accNumChoice)
											{
											case"R":
											{
												break;
											}
											case "L":
											{
												String accs = "";
												boolean invalidName = true;
												System.out.println("Please enter your name: ");
												String name1 = in.nextLine();
												in.nextLine();
												while (invalidName)
												{
												for (BankAccount acc : bankAccounts)
												{
													if (name1.equals(acc.getName()))
													{
														if (getType(acc))
														accs = accs + "Savings Account: " + acc.toString() + "\n";
														else if (!getType(acc))
														{
														accs = accs + "Checking Account: " + acc.toString() + "\n";
														}
													}
												}
												if (accs.equals(""))
												{
													System.out.println("Your entry is invalid. Please enter your name again: ");
													name1 = in.next();
													in.nextLine();
												}
												else
												{
													System.out.println(accs);
													invalidName = false;
												}
												}
												break;
											}
											default:
											{
												continue;
											}
											}
										}	
									}
									if (i == 0)
									{
										accNum1 = accNum;
									}
									else
									{
										accNum2 = accNum;
									}
								}
								double amt = 0;
								System.out.println("Please enter how much money you would like to transfer: ");
								String amtChoice = in.next();
								in.nextLine();
								while (!isNumeric(amtChoice))
								{
									System.out.println("An invalid input was entered. How much would you like to transfer? Please enter it here: ");
									amtChoice = in.next();
									in.nextLine();	
								}
								if (isNumeric(amtChoice))
								{
									amt = Double.parseDouble(amtChoice);
								}
								
								try 
								{
									bankAccounts.get(accNum1-1).transfer(bankAccounts.get(accNum2-1), amt);
									String display = "";
									if (getType(bankAccounts.get(accNum1-1)))
									{
										display = display + "Savings Account: " + bankAccounts.get(accNum1-1).toString() + "\n";
									}
									else if (!getType(bankAccounts.get(accNum1-1)))
									{
										display = display + "Checking Account: " + bankAccounts.get(accNum1-1).toString() + "\n";
									}
									if (getType(bankAccounts.get(accNum2-1)))
									{
										display = display + "Savings Account: " + bankAccounts.get(accNum2-1).toString() + "\n";
									}
									else if (!getType(bankAccounts.get(accNum2-1)))
									{
										display = display + "Checking Account: " + bankAccounts.get(accNum2-1).toString() + "\n";
									}
									System.out.println(display);
								}
								catch (Exception err)
								{
									err.getMessage();
									System.out.println("Transaction not authorized");
								}
								tChoice = false;
								break;
							}
							case "A":
							{
								String accs = "";
								boolean invalidName = true;
								System.out.println("Please enter your name: ");
								String name1 = in.nextLine();
								while (invalidName)
								{
								for (BankAccount acc : bankAccounts)
								{
									if (name1.equals(acc.getName()))
									{
										if (getType(acc))
										accs = accs + "Savings Account: " + acc.toString() + "\n";
										else if (!getType(acc))
										{
										accs = accs + "Checking Account: " + acc.toString() + "\n";
										}
									}
								}
								if (accs.equals(""))
								{
									System.out.println("Your entry is invalid. Please enter your name again: ");
									name1 = in.next();
									in.nextLine();
								}
								else
								{
									System.out.println(accs);
									invalidName = false;
								}
								}
								tChoice = false;
								break;
							}
							
							default:
							{
								break;
							}
				
						}
						
						}
					}	
					break;
				}
				case "TRM":
				{
					if (str.equals("TRM"))
					{
						System.exit(0);	
					}
				}
				default:
				{
					System.out.println("The input " + str + " is not a valid input. Please enter a valid one to continue.");
				}
			}
		}
	}
	
	
	private static boolean isNumeric(String str)
	{
	try
	{
	Double.parseDouble(str);
	return true;
	}
			catch(IllegalArgumentException e)
			{
				return false;
			}
	}
	
	private static boolean getType(BankAccount a)
	{
		if(a instanceof SavingsAccount)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
	


}
