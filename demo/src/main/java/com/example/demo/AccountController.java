package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	 private AccountService accountService;

	 //Captures the request from client with user object
	 @PostMapping(path = "/createAccount", consumes = "application/json", produces = "application/json")
	 public ResponseEntity<?> save(@RequestBody Account account){

	 	//Before saving validate the account information given
		 String accountCheck = ValidateAccount.validate(account); //Checks the inputs
		 switch (accountCheck) {
			 case "valid": //Inputs are valid, move onto checking if this account info exists in the database
				 break;
			 case "invalid email":
				 throw new InvalidRequest("The email address entered is not valid");
			 case "invalid username":
				 throw new InvalidRequest("The username entered is not valid");
			 case "invalid password":
				 throw new InvalidRequest("The password entered is not valid");
		 }

		//After validating the inputs are ok then check if an account with this info already exists
		 boolean emailExists = accountService.checkAccount(account); //Returns true if an account with this email address already exists
		 if(emailExists){
			 throw new InvalidRequest("An account with this email already exists");
		 }
		 else{
			 boolean usernameExists = accountService.checkUser(account);//Returns true if an account with this username already exists
			 if(usernameExists) {
				 throw new InvalidRequest("An account with this username already exists");
			 }
			 else{
				 accountService.save(account); //Has passed all checks, now can create the account in the database
			 }
		 }
		 return ResponseEntity.accepted().body(account);
	 }

	 /*
	 @GetMapping("/login")
	 public Account get(@PathVariable int id) {
	  return accountService.get(id);
	 }


	@GetMapping("/account")
	public List<Account> get() {
		return accountService.get();
	}
	 
	 @DeleteMapping("/account/{id}")
	 public String delete(@PathVariable int id) {
	  accountService.delete(id);
	  return "User removed with id "+id;
	  
	 }
	 
	 @PutMapping("/account")
	 public Account update(@RequestBody Account account) {
	  //accountService.save(account);
	  return account;
	 }
	 */
}