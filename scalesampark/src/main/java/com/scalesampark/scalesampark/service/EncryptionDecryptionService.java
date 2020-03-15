package com.scalesampark.scalesampark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class EncryptionDecryptionService {

    private SecretKeySpec secret;

    @Autowired
    public EncryptionDecryptionService(@Value("${scalesamparl.security.privatekey}") String privateKey){
        MessageDigest digest = null;
        try
        {
            byte[] key = privateKey.getBytes("UTF-8");
            digest = MessageDigest.getInstance("SHA-1");
            key = digest.digest(key);
            key = Arrays.copyOf(key, 16);
            secret = new SecretKeySpec(key, "AES");
        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw new Error(e.getCause());
        }
    }

    public String encrypt(String message) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, this.secret);
            return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }

    }

    public String decrypt(String message) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, this.secret);
            return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

}
