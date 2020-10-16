package com.example.demo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "users")

public class User {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column
	 private Integer id;
	
	@Column
	 private String email;
	
	@Column
	 private String username;
	
	@Column
	 private String password;
	
	@Column
	 private Integer games_played;
	
	@Column
	 private Integer games_won;
	
	@Override
	 public String toString() {
	  return "Employee [id= " + id + ", email=" + email + ", username=" + username + ", password=" + password + ", games_played="
	    + games_played + ", games_won=" + games_won + "]";
	 }
	
	public Integer getId() {
	  return id;
	 }
	
	public void setId(Integer id) {
		  this.id = id;
	 }
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		  this.email = email;
		 }
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		  this.username = username;
		 }
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pw) {
		  this.password = pw;
		 }
	
	public Integer getGames_Won() {
		return games_won;
	}
	
	public void setGames_Won(Integer gw) {
		  this.games_won = gw;
	 }
	
	public Integer getGames_Played() {
		return games_played;
	}
	
	public void setGames_Played(Integer gp) {
		  this.games_played = gp;
	 }
	
	
	
	

}
