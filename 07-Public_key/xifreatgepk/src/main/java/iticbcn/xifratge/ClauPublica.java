package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.PublicKey;

import javax.crypto.Cipher;

import java.security.PrivateKey;
import java.security.KeyPairGenerator;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator clau = KeyPairGenerator.getInstance("RSA"); 
        clau.initialize(2048);
        return clau.generateKeyPair();
    }
    
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher xifratge = Cipher.getInstance("RSA");    
        xifratge.init(Cipher.ENCRYPT_MODE, clauPublica);
        return xifratge.doFinal(msg.getBytes("UTF-8"));
    }
    
    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception {
        Cipher xifratge = Cipher.getInstance("RSA");
        xifratge.init(Cipher.DECRYPT_MODE, ClauPrivada);
        byte[] msgDesxifrat = xifratge.doFinal(msgXifrat);
        return new String(msgDesxifrat, "UTF-8");
    }
}