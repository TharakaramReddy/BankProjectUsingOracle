package com.cg.BankAppOracleParallel.daoTest;


import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import com.cg.BankAppOracleParallel.dao.UserEntryDaoImpl;
import com.cg.BankAppOracleParallel.model.CustomerDetails;

import junit.framework.TestCase;

public class UserEntryDaoImplTest extends TestCase {
	static UserEntryDaoImpl user;
	static CustomerDetails cd;
	@BeforeAll
	public static void init()
	{
		user = new UserEntryDaoImpl();
		cd = new CustomerDetails();
	}
//	@Test
//	public void testRegister() {
//		UserEntryDaoImpl user = new UserEntryDaoImpl();
//		CustomerDetails cd = new CustomerDetails();
//		cd.setFirstName("tharak");
//		cd.setLastName("ram");
//		cd.setEmailId("ram@gmail");
//		cd.setPassword("tharak");
//		cd.setPancardNo(1234345);
//		cd.setAadharNo("234567890360");
//		cd.setAddress("hyd");
//		cd.setMobileNo("2323232323");
//		cd.setBalance(0);
//		
//		assertEquals(534363548, (user.register(cd)));
//	}

	@Test
	public void testLogin() {
		UserEntryDaoImpl user = new UserEntryDaoImpl();
		assertEquals(534363548,user.login(534363548, "tharak").getAccountNo());
	}


}
