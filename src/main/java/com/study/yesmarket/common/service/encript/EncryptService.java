package com.study.yesmarket.common.service.encript;

public interface EncryptService {

    String encrypt(String plainText);

    boolean isMatch(String rawText, String hashedText);
}
