package com.cg.BankAppOracleParallel.service;

import com.cg.BankAppOracleParallel.dao.TransactionDao;
import com.cg.BankAppOracleParallel.dao.TransactionDaoImpl;

public class TransactionServiceImpl implements TransactionService{
	TransactionDao transactionDetails = new TransactionDaoImpl();
	public int withdraw(int accountNo, int amount, int balance) {
		
		return transactionDetails.withdraw(accountNo, amount, balance);
	}

	public int deposit(int accountNo, int amount, int balance) {
		
		return transactionDetails.deposit(accountNo, amount, balance);
	}

	public int showBalance(int accountNo) {
	
		return transactionDetails.showBalance(accountNo);
	}

	public int fundTransfer(int fromAccountNo, int toaccountNo, int amount) {
		
		return transactionDetails.fundTransfer(fromAccountNo, toaccountNo, amount);
	}
	

}
