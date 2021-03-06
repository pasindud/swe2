/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.crypt;

import com.app.enties.Customer;
import com.app.enties.Users;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import static com.google.common.base.Strings.isNullOrEmpty;  
import com.app.Exceptions.JCEException;
/**
 *
 * @author dilsh
 */
public class CustomerCryptor {

    private static final String KEYSTORE = "aes-keystore.jck";
    private static final String STOREPASS = "8u5+6an-QR!CagS<";
    String KEYPASS_STRING = "";

    public Customer encodeCustomer(String userName, String password,Customer cust) throws NoSuchAlgorithmException,JCEException,Exception{
        try{
        String ALIAS =userName;
        String KEYPASS=KEYPASS_STRING;

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // for example
        
        Key secretKey = keyGen.generateKey();

        KeystoreUtil.seKeyStoreEntry(KEYSTORE, STOREPASS, ALIAS, KEYPASS, secretKey, true);
        Key Mykey=KeystoreUtil.getKeyFromKeyStore(KEYSTORE,STOREPASS,ALIAS,KEYPASS, true);
        
        if(Mykey!=null){
        AESCipher cipher = new AESCipher(Mykey);
        
        
        if(!isNullOrEmpty(cust.getFirstName()))
            cust.setFirstName(cipher.getEncryptedText(cust.getFirstName()));
        if(!isNullOrEmpty(cust.getAddressLine1()))
            cust.setAddressLine1(cipher.getEncryptedText(cust.getAddressLine1()));
        if(!isNullOrEmpty(cust.getAddressLine2()))
            cust.setAddressLine2(cipher.getEncryptedText(cust.getAddressLine2()));
        if(!isNullOrEmpty(cust.getAddressLine3()))
            cust.setAddressLine3(cipher.getEncryptedText(cust.getAddressLine3()));
        if(!isNullOrEmpty(cust.getLastName()))
            cust.setLastName(cipher.getEncryptedText(cust.getLastName()));
        if(!isNullOrEmpty(cust.getMobileNo()))
            cust.setMobileNo(cipher.getEncryptedText(cust.getMobileNo()));
        if(!isNullOrEmpty(cust.getEmail()))
            cust.setEmail(cipher.getEncryptedText(cust.getEmail()));
        if(!isNullOrEmpty(cust.getTelephoneNo()))
            cust.setTelephoneNo(cipher.getEncryptedText(cust.getTelephoneNo()));
        if(!isNullOrEmpty(cust.getNic()))
            cust.setNic(cipher.getEncryptedText(cust.getNic()));
        if(!isNullOrEmpty(cust.getCity()))
             cust.setCity(cipher.getEncryptedText(cust.getCity()));
        }
            return cust;
        }catch(Exception ex){
          ex.printStackTrace();
            throw new JCEException ("Make sure Java Cryptography Extension (JCE) unlimited strength jurisdiction policy files are installed in the server. "
            +"Please Visit http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html ");
        }
      
    }

    public Customer decodeCustomer(String userName, String password,Customer cust){
        String KEYPASS=KEYPASS_STRING;
        String ALIAS =userName;
        try {
        Key key=KeystoreUtil.getKeyFromKeyStore(KEYSTORE,STOREPASS,ALIAS,KEYPASS, false);

        if(key!=null){
        AESCipher cipher = new AESCipher(key);

        if(!isNullOrEmpty(cust.getFirstName()))
            cust.setFirstName(cipher.getDecryptedText(cust.getFirstName()));
        if(!isNullOrEmpty(cust.getAddressLine1()))
            cust.setAddressLine1(cipher.getDecryptedText(cust.getAddressLine1()));
        if(!isNullOrEmpty(cust.getAddressLine2()))
            cust.setAddressLine2(cipher.getDecryptedText(cust.getAddressLine2()));
        if(!isNullOrEmpty(cust.getAddressLine3()))
            cust.setAddressLine3(cipher.getDecryptedText(cust.getAddressLine3()));
        if(!isNullOrEmpty(cust.getLastName()))
            cust.setLastName(cipher.getDecryptedText(cust.getLastName()));
        if(!isNullOrEmpty(cust.getMobileNo()))
            cust.setMobileNo(cipher.getDecryptedText(cust.getMobileNo()));
        if(!isNullOrEmpty(cust.getEmail()))
            cust.setEmail(cipher.getDecryptedText(cust.getEmail()));
        if(!isNullOrEmpty(cust.getTelephoneNo()))
            cust.setTelephoneNo(cipher.getDecryptedText(cust.getTelephoneNo()));
        if(!isNullOrEmpty(cust.getNic()))
            cust.setNic(cipher.getDecryptedText(cust.getNic()));
        if(!isNullOrEmpty(cust.getCity()))
            cust.setCity(cipher.getDecryptedText(cust.getCity()));
        }
        return cust;
        
        }catch (Exception e){
          return null;
        }
    }
    public boolean changeCustomerPrivateKey (String userName, String old_password,String new_password){
        try {
        if(KeystoreUtil.updateEntry(KEYSTORE,STOREPASS,userName,old_password,new_password))
            return true;
        else
            return false;
        }catch (Exception e){
            return false;
        }
    }
    
}
