package org.example.analyzer.tokenizerType;

import org.example.analyzer.enums.TokenizerType;

public class TokenizerTypeFactory {

    public static TokenizerTypeInterface getTokenizer(TokenizerType input) {
        if (input == null) return null;

        return switch (input) {
            case STANDARD -> new Standard();
            case WHITESPACE -> new Whitespace();
        };
    }
}
