package com.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA算法工具类
 * <p/>
 * Created by Administrator on 2015/12/1 0001.
 */
public class RSACrypto {
    /**
     * RSA加密算法
     * */
    public static final String ALGORITHM_RSA = "RSA";
    /**
     *MD5withRSA加密算法
     * */
    public static final String ALGORITHM_MD5WITHRSA = "MD5withRSA";
    /**
     *RSA公钥
     * */
    public static final String PUBLIC_KEY = "RSAPublicKey";
    /**
     *RSA私钥
     * */
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    /**
     *密钥长度
     * */
    private static final int DEFAULT_KEY_LENGTH = 512;

    /**
     * 私钥解密
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyfactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PrivateKey privateKey = keyfactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(keyfactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyfactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PublicKey publicKey = keyfactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance(keyfactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyfactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PrivateKey privateKey = keyfactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(keyfactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     */
    public static byte[] encryByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyfactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PublicKey publicKey = keyfactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyfactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 使用私钥进行签名
     */
    public static byte[] sign(byte[] data, byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyfactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PrivateKey prikey = keyfactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(ALGORITHM_MD5WITHRSA);
        signature.initSign(prikey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 使用公钥验签
     */
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(publicKey);

        KeyFactory keyfactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PrivateKey pubkey = keyfactory.generatePrivate(pkcs8KeySpec);

        Signature signature = Signature.getInstance(ALGORITHM_MD5WITHRSA);
        signature.initSign(pubkey);
        signature.update(data);
        return signature.verify(sign);
    }

    /**
     * 初始化密钥串
     */
    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        keyPairGen.initialize(DEFAULT_KEY_LENGTH);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publickey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey peivateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PRIVATE_KEY, peivateKey);
        keyMap.put(PUBLIC_KEY, publickey);
        return keyMap;
    }

}