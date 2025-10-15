package org.example.dataprocessor.outpot;

public class Console implements OutpotInterface {
    @Override
    public void outpot(String Rersult, String resultText) {
        System.out.println(Rersult);
    }
}
