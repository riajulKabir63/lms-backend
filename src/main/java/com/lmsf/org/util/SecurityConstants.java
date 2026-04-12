package com.lmsf.org.util;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class SecurityConstants {

    public static final Long JWT_SHORT_EXPIRATION = 7000000L;
    public static final Long JWT_LONG_EXPIRATION = 700000000L;

    public static String secretkey1 = "a9c71ce5d3412d7acf45373eb3cb619483a5ccdfb0a9026752107d751beee67c";
    public static String secretkey2 = "secret2";


    public static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey1);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
