package com.crypto;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * AES 算法工具类
 * <p/>
 * Created by Administrator on 2015/12/1 0001.
 */
public class AESCrypto {

    /**
     * AES 加密算法
     */
    public static final String AlGORITHM_AES = "AES";
    /**
     * AES/ECB/PKCS5PADDING加密填充方式
     */
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    /**
     * 默认密钥长度
     */
    private static final int DEFAULT_KEY_LENGTH = 128;

    /**
     * 生成秘钥
     */
    private static Key toKey(byte[] key) {
        SecretKey secretKey = new SecretKeySpec(key, AlGORITHM_AES);
        return secretKey;
    }

    /**
     * 加密 使用PKCS7Padding填充方式进行加密
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher;
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     */
    public static byte[] decrpt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 生成密钥
     */
    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(AlGORITHM_AES);
        kg.init(DEFAULT_KEY_LENGTH);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }
}
