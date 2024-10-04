import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    public static final char[] MAJ = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> abcPermutat = new ArrayList<Character>();
        for (char i : MAJ) {
            abcPermutat.add(i);
        }
        Collections.shuffle(abcPermutat);

        char[] alfabetPermutat = new char[abcPermutat.size()];
        for (int i = 0; i < abcPermutat.size(); i++) {
            alfabetPermutat[i] = abcPermutat.get(i);
        }
        return alfabetPermutat;
    }

    public static String xifraMonoAlfa(String text, char[] alfabetPermutat) {
        StringBuilder codText = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char lletra = text.charAt(i); 
            if (Character.isLetter(lletra)) {
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

    
    public static String desxifraMonoAlfa(String text, char[] alfabetPermutat) {
        StringBuilder codText = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char lletra = text.charAt(i); 
            if (Character.isLetter(lletra)) {
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
        String text1 = "La façana de l'edifici és molt bonica";
        String text2 = "El niño pidió piña para el desayuno.";
        char[] alfabetPermutat = permutaAlfabet(MAJ);
        String xifrat1 = xifraMonoAlfa(text1, alfabetPermutat);
        String xifrat2 = xifraMonoAlfa(text2, alfabetPermutat);
        String desxifrat1 = desxifraMonoAlfa(xifrat1, alfabetPermutat);
        String desxifrat2 = desxifraMonoAlfa(xifrat2, alfabetPermutat);
        System.out.println("Primera frase: " + text1);
        System.out.println("Segona frase: " + text2);
        System.out.println("Primera frase xifrada: " + xifrat1);
        System.out.println("Segona frase xifrada: " + xifrat2);
        System.out.println("Primera frase desxifrada: " + desxifrat1);
        System.out.println("Segona frase desxifrada: " + desxifrat2);
    }
}
