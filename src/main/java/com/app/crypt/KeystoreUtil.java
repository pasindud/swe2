/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.crypt;

import com.google.common.io.BaseEncoding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.logging.Level;
import javax.crypto.SecretKey;
import javax.security.cert.CertificateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author dilsh
 */
public class KeystoreUtil {
     private static final Logger LOG = LoggerFactory.getLogger(KeystoreUtil.class);

    public static Key getKeyFromKeyStore(final String keystoreLocation, final String keystorePass, final String alias, final String keyPass, boolean delete) {
        try {

            InputStream keystoreStream = new FileInputStream(keystoreLocation);

            KeyStore keystore = KeyStore.getInstance("JCEKS");

            keystore.load(keystoreStream, keystorePass.toCharArray());

            LOG.debug("Keystore with alias {} found == {}", alias, keystore.containsAlias(alias));
            if (!keystore.containsAlias(alias)) {
                throw new RuntimeException("Alias for key not found");
            }

            Key key = keystore.getKey(alias, keyPass.toCharArray());

            LOG.debug("Key Found {} -> {}", key.getAlgorithm(), BaseEncoding.base64().encode(key.getEncoded()));

            return key;

        } catch (UnrecoverableKeyException | KeyStoreException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (java.security.cert.CertificateException ex) {
             java.util.logging.Logger.getLogger(KeystoreUtil.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
    }
    
    public static boolean seKeyStoreEntry(final String keystoreLocation, final String keystorePass, String alias, String keyPass,Key userKey,boolean delete) {
        try {

            InputStream keystoreStream = new FileInputStream(keystoreLocation);

            KeyStore keystore = KeyStore.getInstance("JCEKS");

            keystore.load(keystoreStream, keystorePass.toCharArray());

            LOG.debug("Keystore with alias {} found == {}", alias, keystore.containsAlias(alias));
            if (keystore.containsAlias(alias)) {
              if (delete) {
                keystore.deleteEntry(alias);
                OutputStream writeStream = new FileOutputStream(keystoreLocation);
                keystore.store(writeStream, keystorePass.toCharArray());
              } else {
                throw new RuntimeException("Alias for key  found");
              }
            }
            
            keystore.setKeyEntry(alias, userKey, keyPass.toCharArray(), null);
            OutputStream writeStream = new FileOutputStream(keystoreLocation);
            keystore.store(writeStream, keystorePass.toCharArray());
            writeStream.close();
            return true;
           

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (java.security.cert.CertificateException ex) {
             return false;
         }
         
    }
    
    public static boolean updateEntry(final String keystoreLocation, final String keystorePass, String alias, String keyPass,String newpassword) throws UnrecoverableKeyException {
        try {

            InputStream keystoreStream = new FileInputStream(keystoreLocation);

            KeyStore keystore = KeyStore.getInstance("JCEKS");

            keystore.load(keystoreStream, keystorePass.toCharArray());

            LOG.debug("Keystore with alias {} found == {}", alias, keystore.containsAlias(alias));
            if (!keystore.containsAlias(alias)) {
                throw new RuntimeException("No Alias for key  found");
            }
            
            Key privateKey = keystore.getKey(alias, keyPass.toCharArray());
            keystore.deleteEntry(alias);
            keystore.setKeyEntry(alias, privateKey, newpassword.toCharArray(), null);

            OutputStream writeStream = new FileOutputStream(keystoreLocation);
            keystore.store(writeStream, keystorePass.toCharArray());
            writeStream.close();
            return true;
           

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (java.security.cert.CertificateException ex) {
             return false;
         }
         
    }
}
