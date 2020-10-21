package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.regex.Pattern;

@Service
public class ValidateAccount {


    @Autowired
    private static EntityManager entityManager;

    //Takes account object passed from client and verifies the info is valid to be put into the database
    //Returns true if this is the case, false if not

    public static String validate(Account account) {

        boolean valid = true;

        valid = validateEmail(account);
        if(! valid){
            return "invalid email";
        }
        valid = validateUsername(account);
        if(! valid){
            return "invalid username";
        }
        valid = validatePassword(account);
        if(! valid){
            return "invalid password";
        }

        return "valid";
    }

    private static boolean validateEmail(Account account) {

        if (account.getEmail() == null || account.getEmail().equals("")) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(account.getEmail()).matches();
    }

    private static boolean validateUsername(Account account) {
        return account.getUsername() != null && !account.getUsername().equals("");
    }

    private static boolean validatePassword(Account account) {
        return account.getPassword().length() >= 4;
    }


}
