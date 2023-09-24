package ru.aston.test.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.aston.test.bankapp.model.BankAccount;

public interface BankAccountDAO extends JpaRepository<BankAccount, Long>{

	
	
}
