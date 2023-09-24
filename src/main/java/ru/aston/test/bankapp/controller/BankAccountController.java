package ru.aston.test.bankapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.aston.test.bankapp.model.BankAccount;
import ru.aston.test.bankapp.service.BankAccountService;
import ru.aston.test.bankapp.util.JsonResponse;

@RestController
public class BankAccountController {

	private final BankAccountService bankAccountService;
	
	public BankAccountController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	@RequestMapping("/api/bank/account/list")
	public JsonResponse<List<BankAccount>> getListBankAccounts() {
		return new JsonResponse<>(bankAccountService.findAll(), true);
	}
	
	@RequestMapping("/api/bank/account/create")
	public JsonResponse<BankAccount> createBankAccount(@RequestParam String pinCode, 
			@RequestParam float sum) {
		JsonResponse<BankAccount> result;
		try {
			BankAccount bankAccount = new BankAccount(sum, pinCode);
			BankAccount saveBankAccount = bankAccountService.save(bankAccount);
			result = new JsonResponse<>(saveBankAccount, 
					"Bank account number " + saveBankAccount.getAccountNumber() + 
					" was successfully saved.", true);
		} catch(Exception e) {
			result = new JsonResponse<>("An error occurred while saving your account: " + 
							e.getMessage(), false);
		}
		return result;
	}
}
