package iticbcn.xifratge;

import java.util.Arrays;

public class TextXifrat {
    private byte[] byt;

    public byte[] getBytes() {
        return byt;
    }

    public TextXifrat(byte[] byt) {
        this.byt = byt;
    }

    @Override
    public String toString() {
        return new String(byt);
    }
}