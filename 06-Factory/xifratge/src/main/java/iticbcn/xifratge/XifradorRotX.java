package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    public static final char[] MAJ = {'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static final char[] MIN = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'ì', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ù', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};

    public String xifratRotX(String text, int desp) {
        StringBuilder codText = new StringBuilder();
        int desplacamentNormalitzat = ((desp % MAJ.length) + MAJ.length) % MAJ.length; // Normalitza el desplaçament
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isUpperCase(currentChar)) { 
                for (int j = 0; j < MAJ.length; j++) {
                    if (MAJ[j] == currentChar) {
                        codText.append(MAJ[(j + desplacamentNormalitzat) % MAJ.length]);
                        break;
                    }
                }
            } else if (Character.isLowerCase(currentChar)) {
                for (int j = 0; j < MIN.length; j++) {
                    if (MIN[j] == currentChar) {
                        codText.append(MIN[(j + desplacamentNormalitzat) % MIN.length]);
                        break;
                    }
                }
            } else {
                codText.append(currentChar); 
            }
        }
        return codText.toString();
    }
    
    public String desxifratRotX(String text, int desp) {
        StringBuilder codText = new StringBuilder();
        int desplacamentNormalitzat = ((desp % MAJ.length) + MAJ.length) % MAJ.length; // Normalitza el desplaçament
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isUpperCase(currentChar)) { 
                for (int j = 0; j < MAJ.length; j++) {
                    if (MAJ[j] == currentChar) {
                        codText.append(MAJ[(j - desplacamentNormalitzat + MAJ.length) % MAJ.length]);
                        break;
                    }
                }
            } else if (Character.isLowerCase(currentChar)) {
                for (int j = 0; j < MIN.length; j++) {
                    if (MIN[j] == currentChar) {
                        codText.append(MIN[(j - desplacamentNormalitzat + MIN.length) % MIN.length]);
                        break;
                    }
                }
            } else {
                codText.append(currentChar); 
            }
        }
        return codText.toString();
    }    

    public void forçaBrutaRotX(String text) {
        for (int i = 0; i < MAJ.length; i++) {
            String des = desxifratRotX(text, i);
            System.out.println("Desplaçament de " + i + " posicions: " + des);
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
            if (desplacament < 0 || desplacament > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    
        String msgXifrat = xifratRotX(msg, desplacament);
        return new TextXifrat(msgXifrat.getBytes());
    }
    
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
            if (desplacament < 0 || desplacament > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    
        String msgXifrat = new String(xifrat.getBytes());
        return desxifratRotX(msgXifrat, desplacament);
    }    
}