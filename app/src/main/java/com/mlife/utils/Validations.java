package com.mlife.utils;

public class Validations {
//    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String emailPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    String indianMobileNumber = "^[7-9][0-9]{9}$";
    String namePattern = "^[\\p{L} .'-]+$";



    public boolean emailValidation(String s) {
        if (s.matches(emailPattern)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean nameValidation(String s) {
        if (s.matches(namePattern)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean emailAndPhoneValidation(String s) {
        if (s.matches(emailPattern)) {
            return true;
        } else if (s.matches(indianMobileNumber)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean passwordValidation(String string) {
        if (string.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean notEmpty(String s) {
        if (s.trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean indiaNumber(String s) {
        if (s.matches(indianMobileNumber)) {
            return true;
        } else {
            return false;
        }
    }

}
