package com.study.yesmarket.common.service.encript;

import org.mindrot.jbcrypt.BCrypt;

/**
 * BCrypt 암호화
 */

public class BcryptEncryptService implements EncryptService{

    @Override
    public String encrypt(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String rawText, String hashedText) {
        return BCrypt.checkpw(rawText, hashedText);
    }
}
