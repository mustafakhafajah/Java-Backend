package org.example.dataprocessor.outpot;

import java.io.FileWriter;
import java.io.IOException;

public class TextFile implements OutpotInterface {
    @Override
    public void outpot(String Rersult, String resultText) {
        try {
            FileWriter outpot = new FileWriter("output.txt");
            outpot.append((Rersult));
            outpot.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred while saving file.");
            e.printStackTrace();
        }

    }
}
