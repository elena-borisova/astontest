package ru.aston.test.bankapp.service;

import org.springframework.stereotype.Service;

import ru.aston.test.bankapp.model.BankAccount;
import ru.aston.test.bankapp.util.BankAccountNotFoundException;
import ru.aston.test.bankapp.util.InvalidAccountBalanceException;
import ru.aston.test.bankapp.util.InvalidAccountPinCodeException;

@Service
public class BankOperationService {

	private final BankAccountService bankAccountService;
	
	public BankOperationService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	public BankAccount deposit(long accountNumber, float sum) 
			throws InvalidAccountBalanceException, BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountService.getAccount(accountNumber);
		return deposit(bankAccount, sum);
	}
	
	public BankAccount deposit(BankAccount bankAccount, float sum) 
			throws InvalidAccountBalanceException, BankAccountNotFoundException {
		if(bankAccount != null) {
			bankAccount.increaseBalance(sum);
			bankAccountService.save(bankAccount);
		} else {
			throw new BankAccountNotFoundException();
		}
		return bankAccount;
	}
	
	public BankAccount withdraw(long accountNumber, String pinCode, float sum) 
			throws InvalidAccountBalanceException, InvalidAccountPinCodeException, BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountService.getAccount(accountNumber);
		return withdraw(bankAccount, pinCode, sum);
	}
	
	public BankAccount withdraw(BankAccount bankAccount, String pinCode, float sum) 
			throws InvalidAccountBalanceException, InvalidAccountPinCodeException, BankAccountNotFoundException {
		if(bankAccount != null) {
			if(!bankAccount.getPinCode().equals(pinCode)) {
				throw new InvalidAccountPinCodeException();
			}
			bankAccount.reduceBalance(sum);
			bankAccountService.save(bankAccount);
		} else {
			throw new BankAccountNotFoundException();
		}
		return bankAccount;
	}
	
	public BankAccount transfer(long senderAccountNumber, String senderPinCode, 
			long beneficiaryAccountNumber, float sum) 
			throws InvalidAccountBalanceException, InvalidAccountPinCodeException, BankAccountNotFoundException {
		BankAccount senderBankAccount = bankAccountService.getAccount(senderAccountNumber);
		BankAccount beneficiaryBankAccount = bankAccountService.getAccount(beneficiaryAccountNumber);
		if(senderBankAccount != null && beneficiaryBankAccount != null) {
			withdraw(senderBankAccount, senderPinCode, sum);
			deposit(beneficiaryBankAccount, sum);
		} else {
			throw new BankAccountNotFoundException();
		}
		return senderBankAccount;
	}
}
