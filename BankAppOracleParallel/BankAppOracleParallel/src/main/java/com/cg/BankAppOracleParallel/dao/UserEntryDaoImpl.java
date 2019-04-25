package com.cg.BankAppOracleParallel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.BankAppOracleParallel.model.CustomerDetails;
import com.cg.BankAppOracleParallel.utility.AadhaarAlreadyExistsException;
import com.cg.BankAppOracleParallel.utility.Database;



public class UserEntryDaoImpl implements UserEntryDao {
	Database db = new Database();
	Connection connection = db.getConnection();
	public int register(CustomerDetails cd) {
		// TODO Auto-generated method stub
		int i = 0 , accountNo = 0 , c = 0;
		PreparedStatement preparedStatement = null;
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from customer_info");
			while(rs.next())
			{
				if(cd.getAadharNo().equals(rs.getString(7)))
					c++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(c == 0) {
		try {
			preparedStatement = connection.prepareStatement("insert into customer_info values(account_no.nextval,?,?,?,?,?,?,?,?,?)");
			
			preparedStatement.setString(1, cd.getFirstName());
			preparedStatement.setString(2, cd.getLastName());
			preparedStatement.setString(3, cd.getEmailId());
			preparedStatement.setString(4, cd.getPassword());
			preparedStatement.setLong(5, cd.getPancardNo());
			preparedStatement.setString(6, cd.getAadharNo());
			preparedStatement.setString(7, cd.getAddress());
			preparedStatement.setString(8, cd.getMobileNo());
			preparedStatement.setInt(9, cd.getBalance());
			
			i = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1)
		{
			try {
				preparedStatement = connection.prepareStatement("select * from customer_info where aadhaar_no = ?");
				preparedStatement.setString(1, cd.getAadharNo());
				ResultSet rs1 = preparedStatement.executeQuery();
				while(rs1.next())
					accountNo = rs1.getInt(1);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else
		{
			try {
				throw new AadhaarAlreadyExistsException();
			} catch (AadhaarAlreadyExistsException e) {
				// TODO Auto-generated catch block

			}
		}
		return accountNo;
	}
		
	public CustomerDetails login(int id, String password) {
		// TODO Auto-generated method stub
		CustomerDetails customerDetails = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from customer_info");
			while(resultSet.next())
			{
				if(resultSet.getInt(1) == id && resultSet.getString(5).equals(password))
				{
					customerDetails = new CustomerDetails();
					customerDetails.setAccountNo(id);
					customerDetails.setFirstName(resultSet.getString(2));
					customerDetails.setLastName(resultSet.getString(3));
					customerDetails.setEmailId(resultSet.getString(4));
					customerDetails.setPancardNo(resultSet.getLong(6));
					customerDetails.setAadharNo(resultSet.getString(7));
					customerDetails.setAddress(resultSet.getString(8));
					customerDetails.setMobileNo(resultSet.getString(9));
					customerDetails.setBalance(resultSet.getInt(10));
				}
			}		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return customerDetails;
	}

}
