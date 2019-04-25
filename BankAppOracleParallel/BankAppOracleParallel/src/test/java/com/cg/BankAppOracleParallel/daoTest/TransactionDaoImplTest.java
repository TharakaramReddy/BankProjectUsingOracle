package com.cg.BankAppOracleParallel.daoTest;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.BankAppOracleParallel.dao.TransactionDaoImpl;

import junit.framework.TestCase;

public class TransactionDaoImplTest extends  TestCase{
	static TransactionDaoImpl td;
	@BeforeAll
	public static void init() {
     td = new TransactionDaoImpl();
		}
	@Test
	void testWithdraw() {
		assertEquals(1000, td.withdraw(127, 300, 1300));
	}

	@Test
	void testDeposit() {
		assertEquals(1500, td.deposit(128, 300, 1200));
	}

	@Test
	void testShowBalance() {
		assertEquals(2700, td.showBalance(127));
	}

	@Test
	void testFundTransfer() {
		assertEquals(1, td.fundTransfer(1003, 1002, 100));	}

}
