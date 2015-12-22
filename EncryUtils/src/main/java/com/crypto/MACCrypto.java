package com.crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 *MAC 消息摘要算法工具类
 *
 * Created by Administrator on 2015/12/1 0001.
 */
public class MACCrypto{

    /**
     * HmacMD5 算法
     * */
    public static final String ALGORITHM_HMACMD5="HmacMD5";
    /**
     * HmacSHA1 算法
     * */
    public static final String ALGORITHM_HMACSHA1 = "HmacSHA1";
    /**
     * HmacSHA256 算法
     * */
    public static final String ALGORITHM_HMACSHA256 = "HmacSHA256";
    /**
     * HmacSHA384 算法
     * */
    public static final String ALGORITHM_HMACSHA384 = "HmacSHA384";
    /**
     * HmacSHA512 算法
     * */
    public static final String ALGORITHM_HMACSHA512= "HmacSHA512";
    /**
     * 密钥长度
     * */
    private static final int DEFAULT_KEY_LENGTH=512;

    /**
     * 生成密钥
     *
     * 根据KeyGenerator.getInstance(ALGORITHM_HMACMD5)的参数不同获取不同的秘钥
     * */
    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_HMACMD5);
        kg.init(DEFAULT_KEY_LENGTH);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * MD5获取摘要
     *
     * 根据new SecretKeySpec(key,ALGORITHM_HMACMD5);的参数不同获取不同算法的摘要
     * */
    public static byte[] encodeMD5(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey scretkey = new SecretKeySpec(key,ALGORITHM_HMACMD5);
        Mac mac = Mac.getInstance(scretkey.getAlgorithm());
        mac.init(scretkey);
        return mac.doFinal(data);
    }


}
