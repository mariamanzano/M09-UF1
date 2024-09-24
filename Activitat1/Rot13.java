import java.util.Scanner;

public class Rot13 {
    public static char[] maj = {'A', 'B', 'C', 'Ç', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] min = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static String xifratRt13(String text) {
        char[] nouText = new char[text.length()]; 
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) { 
                for (int j = 0; j < maj.length; j++) {
                    if (text.charAt(i) == maj[j]) {
                        nouText[i] = maj[(j + 13) % maj.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(text.charAt(i))) {
                for (int j = 0; j < min.length; j++) {
                    if (text.charAt(i) == min[j]) {
                        nouText[i] = min[(j + 13) % min.length];
                        break;
                    }
                }
            } else {
                nouText[i] = text.charAt(i); 
            }
        }
        return new String(nouText);
    }

    public static String desxifratRot13(String text) {
        char[] nouText = new char[text.length()]; 
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) { 
                for (int j = 0; j < maj.length; j++) {
                    if (text.charAt(i) == maj[j]) {
                        nouText[i] = maj[(j - 13 + maj.length) % maj.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(text.charAt(i))) {
                for (int j = 0; j < min.length; j++) {
                    if (text.charAt(i) == min[j]) {
                        nouText[i] = min[(j - 13 + min.length) % min.length];
                        break;
                    }
                }
            } else {
                nouText[i] = text.charAt(i); 
            }
        }
        return new String(nouText);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriu un text: ");
        String text = scanner.nextLine();
        String xifrat = xifratRt13(text);
        System.out.println("Xifrat: " + xifrat);
        String desxifrat = desxifratRot13(xifrat);
        System.out.println("Desxifrat: " + desxifrat);
        scanner.close();
    }   
}
