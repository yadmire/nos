package com.weiyx.nos.utils;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

import java.util.UUID;

public class JWTutil {
    public static final long TOKEN_EXPIRE_TIME = 7200 * 1000;
    private static final String ISSUER = "nos-service";


    /**
     * 创建jwk keyId , 公钥 ，秘钥
     */
    public static void createKeyPair(){
        String keyId = UUID.randomUUID().toString().replaceAll("-", "");
        RsaJsonWebKey jwk = null;
        try {
            jwk = RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException e) {
            e.printStackTrace();
        }
        jwk.setKeyId(keyId);
        jwk.setAlgorithm(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256);
        String publicKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.PUBLIC_ONLY);
        String privateKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);

        System.out.println("keyId="+keyId);
        System.out.println();
        System.out.println("公钥 publicKeyStr="+publicKey);
        System.out.println();
        System.out.println("私钥 privateKeyStr="+privateKey);

    }
    public static void main(String[] args){
        //create key pair
        createKeyPair();
    }

}
