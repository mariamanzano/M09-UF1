package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeAES {

    public Xifrador creaXifrador() {
        return new XifradorPolialfabetic();
    }
}