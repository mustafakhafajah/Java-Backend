package org.example.analyzer.enums;

public enum TokenizerType {
    WHITESPACE,             // split on \\s+
    STANDARD                // regex words: \\p{L}[\\p{L}\\p{Mn}\\p{Nd}_']*
}
