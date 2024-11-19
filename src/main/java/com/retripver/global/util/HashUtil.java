package com.retripver.global.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class HashUtil {

    public String encrypt(String plainText, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.reset();

            String textWithSalt = plainText + salt;
            messageDigest.update(textWithSalt.getBytes(StandardCharsets.UTF_8));

            return String.format("%128x", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 16바이트(128비트) 랜덤 솔트 생성
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return String.format("%032x", new BigInteger(1, salt));
    }
}
