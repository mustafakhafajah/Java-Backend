package org.example.analyzer.tokenizerType;

import org.example.analyzer.Token;

import java.util.List;

public interface TokenizerTypeInterface {
    List<Token> tokenize(String input);
}
