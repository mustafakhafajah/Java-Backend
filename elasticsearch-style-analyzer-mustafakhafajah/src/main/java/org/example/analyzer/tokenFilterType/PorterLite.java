package org.example.analyzer.tokenFilterType;
import org.example.analyzer.Token;
import java.util.ArrayList;
import java.util.List;

public class PorterLite implements TokenFilterTypeInterface {
    @Override
    public List<Token> token(List<Token> input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> suffixes = List.of("ing", "ed", "es", "s", "ly");
        List<Token> output = new ArrayList<>();
        for (Token t : input) {
            String word = t.text;
            for (String suffix : suffixes) {
                if (word.endsWith(suffix) && word.length() > suffix.length()) {
                    word = word.substring(0, word.length() - suffix.length());
                    break;
                }
            }
            output.add(t);
        }

        return output;
    }
}
