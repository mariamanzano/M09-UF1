public class RotX {
    public static final char[] MAJ = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static final char[] MIN = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'ì', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ù', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};

    public static String xifratRotX(String text, int desp) {
        StringBuilder codText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) { 
                for (int j = 0; j < MAJ.length; j++) {
                    if (MAJ[j] == text.charAt(i)) {
                        codText.append(MAJ[(j + desp) % MAJ.length]);
                        break;
                    }
                }
            } else if (Character.isLowerCase(text.charAt(i))) {
                for (int j = 0; j < MIN.length; j++) {
                    if (MIN[j] == text.charAt(i)) {
                        codText.append(MIN[(j + desp) % MIN.length]);
                        break;
                    }
                }
            } else {
                codText.append(text.charAt(i)); 
            }
        }
        return codText.toString();
    }

    public static String desxifratRotX(String text, int desp) {
        StringBuilder codText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) { 
                for (int j = 0; j < MAJ.length; j++) {
                    if (MAJ[j] == text.charAt(i)) {
                        codText.append(MAJ[(j - desp + MAJ.length) % MAJ.length]);
                        break;
                    }
                }
            } else if (Character.isLowerCase(text.charAt(i))) {
                for (int j = 0; j < MIN.length; j++) {
                    if (MIN[j] == text.charAt(i)) {
                        codText.append(MIN[(j - desp + MIN.length) % MIN.length]);
                        break;
                    }
                }
            } else {
                codText.append(text.charAt(i)); 
            }
        }
        return codText.toString();
    }

    public static String forçaBrutaRotX(String text) {
        for (int i = 0; i < MIN.length; i++) {
            String des = desxifratRotX(text, i);
            System.out.println("Desplaçament de " + i + " posicions: " + des);
        }
        return text;
    }

    public static void main(String[] args) {
        String text1 = "La façana de l'edifici és molt bonica";
        String text2 = "El niño pidió piña para el desayuno.";
        int desp1 = 15;
        int desp2 = 3;
        System.out.println("Primera frase: " + text1);
        System.out.println("Segona frase: " + text2);
        String xifrat1 = xifratRotX(text1, desp1);
        String xifrat2 = xifratRotX(text1, desp2);
        String xifrat3 = xifratRotX(text2, desp1);
        String xifrat4 = xifratRotX(text2, desp2);
        System.out.println("Primer text xifrat amb un desplaçament de " + desp1 + ": " + xifrat1);
        System.out.println("Primer text xifrat amb un desplaçament de " + desp2 + ": " + xifrat2);
        System.out.println("Segon text xifrat amb un desplaçament de " + desp1 + ": " + xifrat3);
        System.out.println("Segon text xifrat amb un desplaçament de " + desp2 + ": " + xifrat4);
        String desxifrat1 = desxifratRotX(xifrat1, desp1);
        String desxifrat2 = desxifratRotX(xifrat2, desp2);
        String desxifrat3 = desxifratRotX(xifrat3, desp1);
        String desxifrat4 = desxifratRotX(xifrat4, desp2);
        System.out.println("Primer text desxifrat  amb un desplaçament de " + desp1 + ": " + desxifrat1);
        System.out.println("Segon text desxifrat amb un desplaçament de " + desp2 + ": " +  desxifrat2);
        System.out.println("Primer text desxifrat amb un desplaçament de " + desp1 + ": " +  desxifrat3);
        System.out.println("Segon text desxifrat: amb un desplaçament de " + desp2 + ": " +  desxifrat4);
        forçaBrutaRotX(xifrat1);
    }   
}