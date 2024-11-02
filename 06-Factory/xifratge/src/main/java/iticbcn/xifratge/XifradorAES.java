package iticbcn.xifratge;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Arrays;

public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
       
    private final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    private final String CLAU = "LaClauSecretaQueVulguis";

    public byte[] xifraAES(String msg, String clau) throws Exception {
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

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

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

    @Override
    public TextXifrat xifra(String msg, String clau) {
        try {
            if (clau == null && !clau.equals(CLAU)) {
                throw new ClauNoSuportada("La clau no es soportada");
            }
            byte[] msgXifrat = xifraAES(msg, CLAU);
            return new TextXifrat(msgXifrat);
        } catch (Exception e) {
            System.err.println("Error en xifrat: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) {
        try {
            if (clau == null && !clau.equals(CLAU)) {
                throw new ClauNoSuportada("La clau no es soportada");
            }
            return desxifraAES(xifrat.getBytes(), CLAU);
        } catch (Exception e) {
            System.err.println("Error en desxifrat: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }
}