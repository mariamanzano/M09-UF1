public class Rot13 {
    public static char[] maj = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] min = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'ì', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ù', 'ú', 'v', 'w', 'x', 'y', 'z'};

    public static String xifratRt13(String text) {
        String codText = "";
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) { 
                for (int j = 0; j < maj.length; j++) {
                    if (maj[j] == text.charAt(i)) {
                        codText += maj[(j + 13) % maj.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(text.charAt(i))) {
                for (int j = 0; j < min.length; j++) {
                    if (min[j] == text.charAt(i)) {
                        codText += min[(j + 13) % min.length];
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
                for (int j = 0; j < maj.length; j++) {
                    if (maj[j] == text.charAt(i)) {
                        codText += maj[(j - 13 + maj.length) % maj.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(text.charAt(i))) {
                for (int j = 0; j < min.length; j++) {
                    if (min[j] == text.charAt(i)) {
                        codText += min[(j - 13 + min.length) % min.length];
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
        String text = "La façana de l'edifici és molt bonica";
        System.out.println("Text: " + text);
        String xifrat = xifratRt13(text);
        System.out.println("Xifrat: " + xifrat);
        String desxifrat = desxifratRot13(xifrat);
        System.out.println("Desxifrat: " + desxifrat);
    }   
}
