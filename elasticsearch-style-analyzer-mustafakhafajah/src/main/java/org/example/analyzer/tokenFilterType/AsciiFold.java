package org.example.analyzer.tokenFilterType;

import org.example.analyzer.Token;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class AsciiFold implements TokenFilterTypeInterface {
    @Override
    public List<Token> token(List<Token> input) {
        List<Token> output = new ArrayList<>();
        for (Token t : input) {
            String normalized = Normalizer.normalize(t.text, Normalizer.Form.NFD);
            String cleaned = normalized.replaceAll("\\p{M}", "");
            output.add(new Token(cleaned));
        }
        return output;

    }
}
