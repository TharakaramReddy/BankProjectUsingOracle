package com.cg.BankAppOracleParallel.UI;

import java.util.Scanner;

import com.cg.BankAppOracleParallel.model.CustomerDetails;
import com.cg.BankAppOracleParallel.service.TransactionService;
import com.cg.BankAppOracleParallel.service.TransactionServiceImpl;
import com.cg.BankAppOracleParallel.service.UserEntryService;
import com.cg.BankAppOracleParallel.service.UserEntryServiceImpl;
import com.cg.BankAppOracleParallel.utility.InvalidAadhaarNoException;
import com.cg.BankAppOracleParallel.utility.InvalidMobileNoException;

public class MainUI {

	static Scanner scan = new Scanner(System.in);
	static CustomerDetails cd = new CustomerDetails();
	static UserEntryService userEntryService = new UserEntryServiceImpl();
	static TransactionService transaction = new TransactionServiceImpl();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Enter '1' to Register ");
		System.out.println("Enter '2' to Login");
		int n = scan.nextInt();
		switch (n) {
		case 1:
			int i = setCustomerDetails();
			if (i == 1) {
				int accountNo = userEntryService.register(cd);
				if (accountNo != 0) {
					System.out.println("Your Account No:" + accountNo);
					System.out.println("Please Login to continue");
					login();
				}
			}
			break;
		case 2:
			login();
			break;
		}
	}

	private static void login() {
		// TODO Auto-generated method stub
		System.out.println("Your Account No");
		int accountNo = scan.nextInt();
		System.out.println("Your Password");
		String password = scan.next();
		CustomerDetails customerDetails = userEntryService.login(accountNo, password);
		if (customerDetails != null)
			options(customerDetails);
		else {
			System.out.println("Wrong Credentials");
			login();
		}

	}

	private static int setCustomerDetails() {
		// TODO Auto-generated method stub
		int i = 0;
		System.out.println("Enter your details" + "\n");
		System.out.println("Your First Name");
		cd.setFirstName(scan.next());
		System.out.println("Your Last Name");
		cd.setLastName(scan.next());
		System.out.println("Your Email Id");
		cd.setEmailId(scan.next());
		System.out.println("Your Pancard No");
		cd.setPancardNo(scan.nextLong());
		System.out.println("Your Aadhaar No");
		String aadharNo = scan.next();
		if (userEntryService.isAadharNoCorrect(aadharNo)) {
			cd.setAadharNo(aadharNo);
			System.out.println("Your Address");
			cd.setAddress(scan.next());
			System.out.println("Your Mobile No");
			String mobileNo = scan.next();
			if (userEntryService.isMobileNoCorrect(mobileNo)) {
				cd.setMobileNo(mobileNo);
				cd.setBalance(0);
				System.out.println("Your Password");
				cd.setPassword(scan.next());
				i++;
			} else {
				try {
					throw new InvalidMobileNoException();
				} catch (InvalidMobileNoException e) {
					// TODO Auto-generated catch block
				}
			}
		} else {
			try {
				throw new InvalidAadhaarNoException();
			} catch (InvalidAadhaarNoException e) {
				// TODO Auto-generated catch block
			}
		}
		return i;
	}

	private static void options(CustomerDetails customerDetails) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Options");
			System.out.println("'1' to Withdraw" + "\n" + "'2' to Deposit" + "\n" + "'3' to check balance" + "\n"
					+ "'4' to fund transfer" + "\n" + "'5' to exit");
			int n = scan.nextInt();
			switch (n) {
			case 1:
				System.out.println("Enter the amount to withdraw");
				int withdrawAmt = scan.nextInt();
				int balance = transaction.withdraw(customerDetails.getAccountNo(), withdrawAmt, customerDetails.getBalance());
				if (balance != 0) {
					if (balance != 1) {
						customerDetails.setBalance(balance);
					} else
						customerDetails.setBalance(0);
				} else
					System.out.println("Insufficient balance");
				break;
			case 2:
				System.out.println("Enter the amount to deposit");
				int depositAmount = scan.nextInt();
				int balance1 = transaction.deposit(customerDetails.getAccountNo(), depositAmount,
						customerDetails.getBalance());
				if (balance1 != 0) {
					customerDetails.setBalance(balance1);
					System.out.println("Deposit done");

				}
				break;
			case 3:
				int balance11 = transaction.showBalance(customerDetails.getAccountNo());
				System.out.println("Your Balance:" + balance11);
				break;
			case 4:
				System.out.println("Enter the amount to transfer");
				int transferingAmt = scan.nextInt();
				System.out.println("Enter the account no to which you want to transfer the money");
				int toAccNo = scan.nextInt();
				int details = transaction.fundTransfer(customerDetails.getAccountNo(), toAccNo, transferingAmt);
				if (details != 0) {
					System.out.println(transferingAmt + " Rs/- of money is transferred from " + customerDetails.getAccountNo()
							+ " to " + toAccNo);
					customerDetails.setBalance(details);
				}

				break;
			case 5:
				System.out.println("Thank You");
				System.exit(0);
			default:
				System.out.println("Give Proper Input");
			}
		}

	}

}
