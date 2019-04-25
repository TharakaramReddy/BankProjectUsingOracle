package com.cg.BankAppOracleParallel.model;

public class TransactionDetails {
	private int TransactionId;
	private int fromAccountNo;
	private int toaccountNo;
	private int amount;
	public int getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}
	public int getFromAccountNo() {
		return fromAccountNo;
	}
	public void setFromAccountNo(int fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}
	public int getToaccountNo() {
		return toaccountNo;
	}
	public void setToaccountNo(int toaccountNo) {
		this.toaccountNo = toaccountNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
