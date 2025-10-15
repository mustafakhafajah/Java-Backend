package org.example.analyzer.tokenFilterType;

import org.example.analyzer.Token;

import java.util.ArrayList;
import java.util.List;

public class Lowercase implements TokenFilterTypeInterface {
    @Override
    public List<Token> token(List<Token> input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        List<Token> output = new ArrayList<>();
        for (Token t : input) {
            String lower = t.text.toLowerCase();
            output.add(new Token(lower));
        }
        return output;
    }

}
