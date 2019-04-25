package com.cg.BankAppOracleParallel.utility;

public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException()
	{
		System.out.println("Insufficient amount in your account");
	}
}
