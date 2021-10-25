package com.study.yesmarket.common.service.encript;

import java.security.MessageDigest;

/**
 * SHA-256 암호화
 */

public class SHA256EncryptService implements EncryptService{

    @Override
    public String encrypt(String plainText) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(plainText.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean isMatch(String rawText, String hashedText) {
        return this.encrypt(rawText).equals(hashedText);
    }
}
