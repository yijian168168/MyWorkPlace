package com.crypto;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * DES算法工具类
 * <p/>
 * Created by Administrator on 2015/12/1 0001.
 */
public class DESCrypto {
    /**
     * 加密算法
     * */
    public static final String ALGORITHM_DES = "DES";
    /**
     * 加密填充方式
     * */
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    /**
     * 密钥长度
     * */
    public static final int DEFAULT_KEY_LENGTH = 56;

    /**
     * 生成密钥
     * */
    private static Key toKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        return secretKey;
    }

    /**
     * 解密
     * */
    public static byte[] decrpt(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     * */
    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = null;
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 生成密钥
     * */
    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_DES);
        kg.init(DEFAULT_KEY_LENGTH);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }
}
