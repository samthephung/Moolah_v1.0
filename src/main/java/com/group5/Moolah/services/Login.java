package com.group5.Moolah.services;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class Login {
    public static String passwordHash(String password) {
        try {
            password += "m0ol4$"; //moolah salting

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] password_hash_bytes = md.digest(password.getBytes(StandardCharsets.UTF_8)); //hash into byte array

            return bytesToHex(password_hash_bytes);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("" + e);
            return "error";
        }
    }

    private static String bytesToHex(byte[] hash) { //shoutout baeldung
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) { //testing purposes
        System.out.println(passwordHash("854tn!@q894%^&hgdfASDFHj"));
        System.out.println(passwordHash("test").equals(passwordHash("test")));
    }
}