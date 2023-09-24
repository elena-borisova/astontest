package ru.aston.test.bankapp.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.aston.test.bankapp.util.InvalidAccountBalanceException;
import ru.aston.test.bankapp.util.InvalidAccountPinCodeException;

@Entity
@Table
public class BankAccount {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;
	
	@Column
	private float balance;
	
	@JsonIgnore
	@Column
	private String pinCode;
	
	public BankAccount() {
		
	}
	
	public BankAccount(float balance, String pinCode) throws InvalidAccountBalanceException, 
			InvalidAccountPinCodeException {
		this.setPinCode(pinCode);
		this.setBalance(balance);
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) throws InvalidAccountBalanceException {
		if(balance < 0) {
			throw new InvalidAccountBalanceException();
		}
		this.balance = balance;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) throws InvalidAccountPinCodeException {
		String regex = "^[0-9]{4}$";

	    Pattern p = Pattern.compile(regex);
	    if (pinCode == null) {
	    	throw new InvalidAccountPinCodeException();
	    }
	    Matcher m = p.matcher(pinCode);
		if(m.matches()) {
			this.pinCode = pinCode;
		} else {
			throw new InvalidAccountPinCodeException();
		}
	}
	
	public void increaseBalance(float sum) throws InvalidAccountBalanceException {
		this.setBalance(this.balance + sum);
	}
	
	public void reduceBalance(float sum) throws InvalidAccountBalanceException {
		this.setBalance(this.balance - sum);
	}
	
}
