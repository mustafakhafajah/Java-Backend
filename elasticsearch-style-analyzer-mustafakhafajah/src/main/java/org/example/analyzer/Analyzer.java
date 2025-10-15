package org.example.analyzer;

import java.util.List;

public interface Analyzer {
    List<Token> analyze(String text);
}
