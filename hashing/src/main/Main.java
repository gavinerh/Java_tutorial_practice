package main;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    // Java MessageDigest Class for hashing passwords
    public static void main(String[] args){
        String p1 = "hello123";
        String encodedP1 = bytesToHex(hashing(p1));

        String p2 = "hello123";
        String encodedP2 = bytesToHex(hashing(p2));

        System.out.println("P1 encoded: " + encodedP1);
        System.out.println("P2 encoded: " + encodedP2);
        System.out.println(encodedP1.equals(encodedP2));
    }

    public static byte[] hashing(String password){
        byte[] encodedHash = null;
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e){
            System.out.println("Error");
        }
        return encodedHash;
    }

    public static String bytesToHex(byte[] hash){
        StringBuilder hexString = new StringBuilder(2*hash.length);
        for(int i=0; i<hash.length; i++){
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
