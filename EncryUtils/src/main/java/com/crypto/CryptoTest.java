package com.crypto;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * 加密算法测试类
 *
 * Created by Administrator on 2015/12/1 0001.
 */
public class CryptoTest {

    private String data = "qingrong";

    @Test
    public void testAESCrypto(){
        try {
            byte[] key = AESCrypto.initKey();
            byte[] encryData = AESCrypto.encrypt(data.getBytes(),key);
            System.out.println("testAESCrypto_encry:" + new String(encryData));
            byte[] decryData = AESCrypto.decrpt(encryData,key);
            System.out.println("testAESCrypto_decrpt:" + new String(decryData));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDESCrypto(){
        try {
            byte[] key = DESCrypto.initKey();
            byte[] encryData = DESCrypto.encrypt(data.getBytes(), key);
            System.out.println("testAESCrypto_encry:" + new String(encryData));
            byte[] decryData = DESCrypto.decrpt(encryData,key);
            System.out.println("testAESCrypto_decrpt:" + new String(decryData));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMACCrypto(){
        try {
            byte[] key = MACCrypto.initKey();
            byte[] encode1 = MACCrypto.encodeMD5(data.getBytes(), key);
            System.out.println(new String(encode1));
            byte[] encode2 = MACCrypto.encodeMD5(data.getBytes(), key);
            System.out.println(new String(encode2));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRSACrypto(){
        try {
            Map<String,Object> keyMap = RSACrypto.initKey();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyMap.get("RSAPublicKey");
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyMap.get("RSAPrivateKey");
            byte[] publicKey = rsaPublicKey.getEncoded();
            System.out.println(new String(publicKey));
            byte[] privateKey = rsaPrivateKey.getEncoded();
            System.out.println(new String(privateKey));
            byte[] encryByPublic = RSACrypto.encryByPublicKey(data.getBytes(), publicKey);
            System.out.println("公钥加密："  + new String(encryByPublic));
//            byte[] decryByPublic = RSACrypto.decryptByPublicKey(encryByPublic,publicKey);
//            System.out.println("公钥解密：" + new String(decryByPublic));
            byte[] decryByPrivate = RSACrypto.decryptByPrivateKey(encryByPublic, privateKey);
            System.out.println("私钥解密：" + new String(decryByPrivate));

            byte[] encryByPrivate = RSACrypto.encryptByPrivateKey(data.getBytes(),privateKey);
            System.out.println("私钥加密："  + new String(encryByPrivate));
            byte[] decryByPublic = RSACrypto.decryptByPublicKey(encryByPrivate,publicKey);
            System.out.println("公钥解密：" + new String(decryByPublic));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

}
