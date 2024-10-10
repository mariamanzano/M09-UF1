import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {
    public static final char[] MAJ = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] alfabetPermutat;
    public static final int clauSecreta = 233;
    public static Random random = new Random();

    public static void initRandom(int clauSecreta) {
        random.setSeed(clauSecreta);
    }

    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> abcPermutat = new ArrayList<Character>();
        for (char i : MAJ) {
            abcPermutat.add(i);
        }
        Collections.shuffle(abcPermutat, random);   

        char[] alfabetP = new char[abcPermutat.size()];
        for (int i = 0; i < abcPermutat.size(); i++) {
            alfabetP[i] = abcPermutat.get(i);
        }
        alfabetPermutat = alfabetP;
        return alfabetP;
    }

    public static String xifraPoliAlfa(String text) {
        StringBuilder codText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char lletra = text.charAt(i); 
            if (Character.isLetter(lletra)) {
                permutaAlfabet(MAJ);
                char llMaj = Character.toUpperCase(lletra); 
                for (int j = 0; j < MAJ.length; j++) {
                    if (llMaj == MAJ[j]) { 
                        char llXifrada = alfabetPermutat[j];
                        if (Character.isLowerCase(lletra)) {
                            codText.append(Character.toLowerCase(llXifrada)); 
                        } else {
                            codText.append(llXifrada); 
                        }
                        break;
                    }
                }
            } else {
                codText.append(lletra);
            }
        }
        return codText.toString(); 
    }

    
    public static String desxifraPoliAlfa(String text) {
        StringBuilder codText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char lletra = text.charAt(i); 
            if (Character.isLetter(lletra)) {
                permutaAlfabet(MAJ);
                char llMaj = Character.toUpperCase(lletra);
                for (int j = 0; j < MAJ.length; j++) {
                    if (llMaj == alfabetPermutat[j]) { 
                        char llXifrada = MAJ[j];
                        if (Character.isLowerCase(lletra)) {
                            codText.append(Character.toLowerCase(llXifrada)); 
                        } else {
                            codText.append(llXifrada); 
                        }
                        break;
                    }
                }
            } else {
                codText.append(lletra);
            }
        }
        return codText.toString(); 
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
        "Test 02 Taüll, DÍA, año",
        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
        System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
        
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        String msg = desxifraPoliAlfa(msgsXifrats[i]);
        System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }       
}