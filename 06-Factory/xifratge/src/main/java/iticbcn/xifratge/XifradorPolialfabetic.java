package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    public static final char[] MAJ = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public char[] alfabetPermutat;
    public Random random = new Random();

    public void initRandom(long clauSecreta) {
        random.setSeed(clauSecreta);
    }

    public char[] permutaAlfabet(char[] alfabet) {
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

    public String xifraPoliAlfa(String text, long clau) {
        initRandom(clau); 
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
   
    public String desxifraPoliAlfa(String text, long clau) {
        initRandom(clau);
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauNum;
        try {
            clauNum = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        String msgXifrat = xifraPoliAlfa(msg, clauNum);
        return new TextXifrat(msgXifrat.getBytes());
    }    
    
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauNum;
        try {
            clauNum = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        String codText = desxifraPoliAlfa(new String(xifrat.getBytes()), clauNum);
        return codText;
    }
}