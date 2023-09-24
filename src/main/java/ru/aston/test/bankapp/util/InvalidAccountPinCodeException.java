package ru.aston.test.bankapp.util;

public class InvalidAccountPinCodeException extends Exception{

	private static final long serialVersionUID = -6758883897293564837L;

	public InvalidAccountPinCodeException(String errorMessage) {
        super(errorMessage);
    }
	
	public InvalidAccountPinCodeException() {
		super("Invalid account PIN code.");
	}
	
}
