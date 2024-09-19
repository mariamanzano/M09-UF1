public class Rot13 {
    public static char[] maj = {'A','B','C','Ç','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static char[] min = {'a','b','c','ç','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    public static String xifratRt13(String text) {
        char[] nouText = new char[text.length()]; 
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' || Character.isLetter(text.charAt(i))) {
                nouText[i] = text.charAt(i);
            }
        }

        for (int k = 0; k < nouText.length; k++) {
            for (int j = 0; j < maj.length; j++) {
                for (int i = 0; i < min.length; i++) {

                }
            }
        }
        return text;
    }

    public static String desxifratRot13(String text) {
        return text;
    }

    public static void main(String[] args) {
        String text = "Hola que tal!";
        xifratRt13(text);
    }   
}
