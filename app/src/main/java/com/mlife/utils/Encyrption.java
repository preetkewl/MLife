package com.mlife.utils;

import android.util.Log;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by milagro on 2/19/2018.
 */

public class Encyrption {

    public String trytest(String keys, String password, String ivs) {
        try {
            Base64 base64 = new Base64();
            Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec key = new SecretKeySpec(keys.getBytes("UTF-8"), "AES");
            Log.i("key",key.toString());
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes("UTF-8"), 0, ciper.getBlockSize());
            Log.i("key",iv.toString());

            //Encrypt
            ciper.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] encryptedCiperBytes = base64.encode((ciper.doFinal(password.getBytes())));
            System.out.println("Ciper : " + new String(encryptedCiperBytes));
            //Decrypt
//            ciper.init(Cipher.DECRYPT_MODE, key, iv);
//            byte[] text = ciper.doFinal(base64.decode(encryptedCiperBytes));
//            System.out.println("Decrypt text : " + new String(text));

            return new String(encryptedCiperBytes);
        } catch (Exception e) {
        }
        return null;
    }
}