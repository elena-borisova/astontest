package ru.aston.test.bankapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.aston.test.bankapp.model.BankAccount;
import ru.aston.test.bankapp.service.BankOperationService;
import ru.aston.test.bankapp.util.JsonResponse;

@RestController
public class BankOperationController {

	public final BankOperationService bankOperationService;
	
	public BankOperationController(BankOperationService bankOperationService) {
		this.bankOperationService = bankOperationService;
	}
	
	@RequestMapping("/api/bank/operation/deposit")
	public JsonResponse<BankAccount> deposit(@RequestParam long accountNumber, 
			@RequestParam float sum) {
		JsonResponse<BankAccount> result;
		try {	
			BankAccount bankAccount = bankOperationService.deposit(accountNumber, sum);
			result = new JsonResponse<>(bankAccount, "Deposit completed successfully. "
					+ "Current balance: " + bankAccount.getBalance() + "." , true); 
		} catch(Exception e) {
			result = new JsonResponse<>(null, "The operation was not completed. An error has occurred: "+
		 e.getMessage(), false, true);
		}
		return result;
	}
	
	@RequestMapping("/api/bank/operation/withdraw")
	public JsonResponse<BankAccount> withdraw(@RequestParam long accountNumber, @RequestParam String pinCode, 
			@RequestParam float sum) {
		JsonResponse<BankAccount> result;
		try {
			BankAccount bankAccount = bankOperationService.withdraw(accountNumber, pinCode, sum);
			result = new JsonResponse<>(bankAccount, "Withdraw completed successfully. "
					+ "Current balance: "+ bankAccount.getBalance(), true);
		} catch(Exception e) {
			result = new JsonResponse<>(null, "The operation was not completed. An error has occurred: "+
					 e.getMessage(), false, true);
		}
		return result;
	}
	
	@RequestMapping("/api/bank/operation/transfer")
	public JsonResponse<BankAccount> transfer(@RequestParam long senderAccountNumber, @RequestParam String pinCode, 
			@RequestParam long benefeciaryAccountNumber, @RequestParam float sum) {
		JsonResponse<BankAccount> result;
		try {
			BankAccount bankAccount = bankOperationService.transfer(senderAccountNumber, pinCode, 
					benefeciaryAccountNumber, sum);
			result = new JsonResponse<>(bankAccount, "Transfer completed successfully. "
					+ "Current balance: "+ bankAccount.getBalance(), true);
		} catch(Exception e) {
			result = new JsonResponse<>(null, "The operation was not completed. An error has occurred: "+
					 e.getMessage() , false, true);
		}
		return result;
	}
}
