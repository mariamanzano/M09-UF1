package iticbcn.xifratge;
public class XifradorRotX implements Xifrador {
    public static final char[] MAJ = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static final char[] MIN = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'ì', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ù', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};

    public String xifratRotX(String text, int desp) {
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

    public String desxifratRotX(String text, int desp) {
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

    public String forçaBrutaRotX(String text) {
        for (int i = 0; i < MIN.length; i++) {
            String des = desxifratRotX(text, i);
            System.out.println("Desplaçament de " + i + " posicions: " + des);
        }
        return text;
    }
}