public class Rot13 {
    public static final char[] MAJ = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};
    public static final char[] MIN = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'ì', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ù', 'ú', 'v', 'w', 'x', 'y', 'z'};

    public static String xifratRot13(String text) {
        String codText = "";
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) { 
                for (int j = 0; j < MAJ.length; j++) {
                    if (MAJ[j] == text.charAt(i)) {
                        codText += MAJ[(j + 13) % MAJ.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(text.charAt(i))) {
                for (int j = 0; j < MIN.length; j++) {
                    if (MIN[j] == text.charAt(i)) {
                        codText += MIN[(j + 13) % MIN.length];
                        break;
                    }
                }
            } else {
                codText += text.charAt(i); 
            }
        }
        return codText;
    }

    public static String desxifratRot13(String text) {
        String codText = "";
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) { 
                for (int j = 0; j < MAJ.length; j++) {
                    if (MAJ[j] == text.charAt(i)) {
                        codText += MAJ[(j - 13 + MAJ.length) % MAJ.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(text.charAt(i))) {
                for (int j = 0; j < MIN.length; j++) {
                    if (MIN[j] == text.charAt(i)) {
                        codText += MIN[(j - 13 + MIN.length) % MIN.length];
                        break;
                    }
                }
            } else {
                codText += text.charAt(i); 
            }
        }
        return codText;
    }

    public static void main(String[] args) {
        String text1 = "La façana de l'edifici és molt bonica";
        String text2 = "El niño pidió piña para el desayuno.";
        System.out.println("Primera frase: " + text1);
        System.out.println("Segona frase: " + text2);
        String xifrat1 = xifratRot13(text1);
        String xifrat2 = xifratRot13(text2);
        System.out.println("Primer text xifrat: " + xifrat1);
        System.out.println("Segon text xifrat: " + xifrat2);
        String desxifrat1 = desxifratRot13(xifrat1);
        String desxifrat2 = desxifratRot13(xifrat2);
        System.out.println("Primer text desxifrat: " + desxifrat1);
        System.out.println("Segon text desxifrat: " + desxifrat2);
    }   
}
