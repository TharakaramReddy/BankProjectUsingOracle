package com.cg.BankAppOracleParallel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.BankAppOracleParallel.utility.Database;
import com.cg.BankAppOracleParallel.utility.InsufficientBalanceException;
import com.cg.BankAppOracleParallel.utility.InvalidIdException;


public class TransactionDaoImpl implements TransactionDao {
	Database db = new Database();
	Connection con = db.getConnection();
	public int withdraw(int accountNo, int amount, int balance) {
		// TODO Auto-generated method stub
		int c = 0;
		if(balance >= amount)
        {
            balance = balance - amount;
            int i = updateBalance(balance,accountNo);
            if(i == 1)
            {
            	c++;
            }
        }
		if(c == 1)
		{
			if(balance == 0)
				return 1;
			else
				return balance;
		}
		else
			return 0;
	}

	private int updateBalance(int balance, int accountNo) {
		
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement("update customer_info set balance = ? where account_no = ?");
			ps.setInt(1, balance);
			ps.setInt(2, accountNo);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int deposit(int accountNo, int amount, int balance) {
		// TODO Auto-generated method stub
		balance = balance + amount;
		int i = updateBalance(balance,accountNo);
		if(i == 1)
       return balance;
		else
			return 0;
	}

	public int showBalance(int accountNo) {
		// TODO Auto-generated method stub
		int balance = 0;
		try {
			PreparedStatement ps = con.prepareStatement("select * from customer_info where account_no = ?");
			ps.setInt(1, accountNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				balance = rs.getInt(10);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}

	public int fundTransfer(int fromAccountNo, int toaccountNo, int amount) {
		// TODO Auto-generated method stub
		int toBalance = 0;
		int fromBalance = 0;
		int i = 0;
		try {
			int c=0;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer_info");
			while(rs.next())
			{
				if(rs.getInt(1) == toaccountNo)
				{
					c++;
					toBalance = rs.getInt(10);
				}
				if(rs.getInt(1) == fromAccountNo)
				{
					fromBalance = rs.getInt(10);
				}
			}
			st.close();
			if(c == 1)
			{
				if(fromBalance >= amount)
				{
					i = insertTransactionDetails(fromAccountNo,toaccountNo,amount);
					if(i == 1)
					{
						toBalance += amount;
						fromBalance -= amount;
						updateBalance(fromBalance, fromAccountNo);
						updateBalance(toBalance, toaccountNo);
					}
				}
				else
				{
					throw new InsufficientBalanceException();
				}
			}	
			else
			{
				try {
					throw new InvalidIdException();
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			
		} 
		if(fromBalance != 0)
		return fromBalance;
		else return 1;
	}

	private int insertTransactionDetails(int fromAccountNo, int toaccountNo, int amount) {
		// TODO Auto-generated method stub
		int i = 0;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into Transaction_table values(transaction_no_seq.nextval,?,?,?)");
			ps.setInt(1, fromAccountNo);
			ps.setInt(2, toaccountNo);
			ps.setInt(3, amount);
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

}
