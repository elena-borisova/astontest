package ru.aston.test.bankapp.util;

public class BankAccountNotFoundException extends Exception{

	private static final long serialVersionUID = -4955376481070315033L;

	public BankAccountNotFoundException() {
		super("Bank account not found.");
	}
	
}
