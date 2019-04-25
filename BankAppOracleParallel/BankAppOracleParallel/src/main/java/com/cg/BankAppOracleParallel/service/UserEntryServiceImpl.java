package com.cg.BankAppOracleParallel.service;

import com.cg.BankAppOracleParallel.dao.UserEntryDao;
import com.cg.BankAppOracleParallel.dao.UserEntryDaoImpl;
import com.cg.BankAppOracleParallel.model.CustomerDetails;

public class UserEntryServiceImpl implements UserEntryService{
	UserEntryDao userEntryDao = new UserEntryDaoImpl();
	public int register(CustomerDetails cd) {
		// TODO Auto-generated method stub
		return userEntryDao.register(cd);
	}

	public boolean isAadharNoCorrect(String aadharNo) {
		// TODO Auto-generated method stub
		if(aadharNo.length() == 12)
			return true;
		else
			return false;
	}

	public boolean isMobileNoCorrect(String mobileNo) {
		
		if(mobileNo.length() == 10)
			return true;
		else
			return false;
	}

	public CustomerDetails login(int accountNo, String password) {
		
		return userEntryDao.login(accountNo, password);
	}

}
