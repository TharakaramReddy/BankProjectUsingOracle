package com.cg.BankAppOracleParallel.dao;

import com.cg.BankAppOracleParallel.model.CustomerDetails;

public interface UserEntryDao {
	int register(CustomerDetails cd);
	CustomerDetails login(int id,String password);
}
