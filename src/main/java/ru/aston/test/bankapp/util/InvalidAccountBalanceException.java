package ru.aston.test.bankapp.util;

public class InvalidAccountBalanceException extends Exception{

	private static final long serialVersionUID = -4655226819226475480L;
	
	public InvalidAccountBalanceException(String errorMessage) {
        super(errorMessage);
    }

	public InvalidAccountBalanceException() {
        super("Invalid account balance.");
    }
}
