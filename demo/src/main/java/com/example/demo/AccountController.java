package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	 private AccountService accountService;

	 @GetMapping("/account")
	 public List<Account> get() {
	  return accountService.get();
	 }


	 //Captures the request from client with user object
	 @PostMapping(path = "/account", consumes = "application/json", produces = "application/json")
	 public ResponseEntity<Object> save(@RequestBody Account account){

	 	//Before saving validate the account information given
		 String accountCheck = ValidateAccount.validate(account);
		 switch (accountCheck) {
			 case "valid":
				 break;
			 case "invalid email":
				 throw new InvalidRequest("The email address entered is not valid");
			 case "invalid username":
				 throw new InvalidRequest("The username entered is not valid");
			 case "invalid password":
				 throw new InvalidRequest("The password entered is not valid");
		 }

		//After validating the inputs are ok then check if an account with this info already exists
		 List<Account> emailExists = accountService.checkAccount(account);
		 if(emailExists != null){
			 throw new InvalidRequest("An account with this email already exists");
		 }
		 else{
			 List<Account> usernameExists = accountService.checkUser(account);
			 if(usernameExists != null) {
				 throw new InvalidRequest("An account with this username already exists");
			 }
			 else{
				 accountService.save(account);
			 }
		 }
		 return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account has been created");
	 }

	@ExceptionHandler(IllegalArgumentException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again and with a non empty string as 'name'");
	}
	 
	 @GetMapping("/account/{id}")
	 public Account get(@PathVariable int id) {
	  return accountService.get(id);
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
}
