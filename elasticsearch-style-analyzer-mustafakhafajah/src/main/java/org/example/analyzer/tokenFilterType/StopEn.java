package org.example.analyzer.tokenFilterType;
import org.example.analyzer.Token;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopEn implements TokenFilterTypeInterface {
    @Override
    public List<Token> token(List<Token> input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> stopList = Arrays.asList(
                "the", "and", "of", "to", "in", "a", "an", "is", "it", "for", "on"
        );
        List<Token> output = new ArrayList<>();
        for (Token t : input) {
            if (!stopList.contains(t.text.toLowerCase())) {
                output.add(t);
            }
        }
        return output;
    }
}
