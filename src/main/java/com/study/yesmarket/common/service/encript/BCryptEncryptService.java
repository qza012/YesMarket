package com.study.yesmarket.common.service.encript;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * BCrypt μνΈν
 */
@Service("BCrypt")
public class BCryptEncryptService implements EncryptService{

    @Override
    public String encrypt(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String rawText, String encryptedText) {
        return BCrypt.checkpw(rawText, encryptedText);
    }
}
