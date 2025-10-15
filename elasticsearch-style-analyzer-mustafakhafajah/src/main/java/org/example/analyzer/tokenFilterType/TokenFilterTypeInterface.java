package org.example.analyzer.tokenFilterType;

import org.example.analyzer.Token;
import java.util.List;

public interface TokenFilterTypeInterface {
    List<Token> token(List<Token> input);
}
