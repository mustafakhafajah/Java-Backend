package org.example.analyzer.tokenFilterType;

import org.example.analyzer.Token;
import java.util.ArrayList;
import java.util.List;

public class OneToThreeEdgeNGram implements TokenFilterTypeInterface {
    @Override
    public List<Token> token(List<Token> input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        List<Token> output = new ArrayList<>();
        for (Token t : input) {
            String text = t.text;
            String result = "";
            for (int i = 0; i < text.length() && i < 3; i++) {
                result = result + text.charAt(i);
                output.add(new Token(result));
            }
        }
        return output;
    }


}
