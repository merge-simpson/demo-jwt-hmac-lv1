package me.letsdev.demo;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGen {
    public static void main(String[] args) {
        // 512비트(64바이트) 랜덤 시크릿 생성
        byte[] secret = new byte[64];
        new SecureRandom().nextBytes(secret);

        // Base64 인코딩
        String base64Secret = Base64.getEncoder().withoutPadding().encodeToString(secret);
        System.out.println("Base64 시크릿키: " + base64Secret);
    }
}
