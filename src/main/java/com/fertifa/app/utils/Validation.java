package com.fertifa.app.utils;

public class Validation {

    public static boolean validate(String email) {
        String expressions = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
        return email.matches(expressions);
    }
}
