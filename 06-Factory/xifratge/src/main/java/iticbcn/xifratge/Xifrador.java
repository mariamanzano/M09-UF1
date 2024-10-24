package iticbcn.xifratge;

public interface Xifrador {
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada; 

    public TextXifrat desxifra(String msg, String clau) throws ClauNoSuportada;
}
