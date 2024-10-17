import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;
import java.sql.Array;
import java.util.Arrays;
public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
       
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
    //Obtenir els bytes de l’String
        byte[] strB = msg.getBytes();
    // Genera IvParameterSpec
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        IvParameterSpec ivS = new IvParameterSpec(iv);
    // Genera hash
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hs = md.digest(clau.getBytes("UTF-8"));
        hs = Arrays.copyOf(hs, 16);
        SecretKeySpec secretKey = new SecretKeySpec(hs, ALGORISME_XIFRAT);
    // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivS);
        byte[] msgxifrat = cipher.doFinal(strB);

    // Combinar IV i part xifrada.
        byte[] IvIMsgXifrat = new byte[iv.length + msgxifrat.length];
        System.arraycopy(iv, 0, IvIMsgXifrat, 0, iv.length);
        System.arraycopy(msgxifrat, 0, IvIMsgXifrat, iv.length, msgxifrat.length);        
    // return iv+msgxifrat
        return IvIMsgXifrat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

    // Extreure l'IV.
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

    // Extreure la part xifrada.
        byte[] strB = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

    // Fer hash de la clau
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hs = md.digest(clau.getBytes("UTF-8"));
        hs = Arrays.copyOf(hs, 16);
        SecretKeySpec secretKey = new SecretKeySpec(hs, ALGORISME_XIFRAT);

    // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] msgDesxifrat = cipher.doFinal(strB);

    // return String desxifrat
        return new String(msgDesxifrat, "UTF-8");
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", 
        "Hola Andrés cómo está tu cuñado", 
        "Àgora ïlla Ôtto"};
        
        for (int i = 0; i < msgs.length; i++) {
        String msg = msgs[i]; 
        
        byte[] bXifrats = null;
        String desxifrat = "";
        try {
        bXifrats = xifraAES(msg, CLAU);
        desxifrat = desxifraAES(bXifrats, CLAU);
        } catch (Exception e) {
        System.err.println("Error de xifrat: " 
        + e.getLocalizedMessage());
        }
        System.out.println("--------------------");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
        }
    }
        
}