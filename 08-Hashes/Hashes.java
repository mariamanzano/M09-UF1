import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
public class Hashes {

    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
            HexFormat hex = HexFormat.of();
            return hex.formatHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); 
            return null;
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            int iteracions = 845;
            int clauLL = 512;
            char[] pwChars = pw.toCharArray();
            byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);

            PBEKeySpec spec = new PBEKeySpec(pwChars, saltBytes, iteracions, clauLL);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hashByte = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int npass = 0;
    public String forcaBruta(String alg, String targetHash, String salt) {
        String charset = "abcdefABCDEF1234567890!";
        npass = 0;

        for (int ll = 1; ll <= 6; ll++) {
            char[] intent = new char[ll];
            int num = charset.length();

            for (int i = 0; i < num; i++) {
                intent[0] = charset.charAt(i);
                if (ll == 1 && testPw(intent, alg, targetHash, salt)) {
                    return new String(intent);
                } 

                for (int j = 0; j < num; j++) {
                    if (ll > 1) {
                        intent[1] = charset.charAt(j);
                    } 
                    if (ll == 2 && testPw(intent, alg, targetHash, salt)) {
                         return new String(intent);
                    }

                    for (int k = 0; k < num; k++) {
                        if (ll > 2) {
                            intent[2] = charset.charAt(k);
                        }
                        if (ll == 3 && testPw(intent, alg, targetHash, salt)) {
                            return new String(intent);
                        } 
                        for (int l = 0; l < num; l++) {
                            if (ll > 3) {
                                 intent[3] = charset.charAt(l);
                            }
                            if (ll == 4 && testPw(intent, alg, targetHash, salt)){
                                return new String(intent);
                            }

                            for (int m = 0; m < num; m++) {
                                if (ll > 4) {
                                    intent[4] = charset.charAt(m);
                                }
                                if (ll == 5 && testPw(intent, alg, targetHash, salt)){
                                    return new String(intent);
                                } 

                                for (int n = 0; n < num; n++) {
                                    if (ll > 5) {
                                        intent[5] = charset.charAt(n);
                                    }
                                    if (ll == 6 && testPw(intent, alg, targetHash, salt)){
                                         return new String(intent);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private boolean testPw(char[] attempt, String alg, String targetHash, String salt) {
        npass++;
        String attemptStr = new String(attempt);
        String attemptHash = alg.equals("SHA-512") ? getSHA512AmbSalt(attemptStr, salt) : getPBKDF2AmbSalt(attemptStr, salt);
        return attemptHash != null && attemptHash.equals(targetHash);
    }

    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;

        long dies = millis / (24 * 60 * 60 * 1000);
        millis %= (24 * 60 * 60 * 1000);
        
        long hores = millis / (60 * 60 * 1000);
        millis %= (60 * 60 * 1000);
        
        long minuts = millis / (60 * 1000);
        millis %= (60 * 1000);
        
        long segons = millis / 1000;
        millis %= 1000;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, millis);
    }
    
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
        System.out.printf("===========================\n");
        System.out.printf("Algorisme: %s\n", algorismes[i]);
        System.out.printf("Hash: %s\n",aHashes[i]);
        System.out.printf("---------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");
        
        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();
        
        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("---------------------------\n\n");
        }
    }
}