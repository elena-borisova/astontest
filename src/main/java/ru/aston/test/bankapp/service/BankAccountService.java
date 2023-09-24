package ru.aston.test.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.aston.test.bankapp.dao.BankAccountDAO;
import ru.aston.test.bankapp.model.BankAccount;

@Service
public class BankAccountService {

	private final BankAccountDAO bankAccountDAO;
	
	public BankAccountService(BankAccountDAO bankAccountDAO) {
		this.bankAccountDAO = bankAccountDAO;
	}
	
	public List<BankAccount> findAll() {
		return bankAccountDAO.findAll();
	}
	
	public BankAccount save(BankAccount bankAccount) {
		return bankAccountDAO.save(bankAccount);
	}
	
	public BankAccount getAccount(long accountNumber) {
		Optional<BankAccount> bankAccount = bankAccountDAO.findById(accountNumber);
		if(bankAccount.isEmpty()) {
			return null;
		} 
 		return bankAccount.get();
	}
}
