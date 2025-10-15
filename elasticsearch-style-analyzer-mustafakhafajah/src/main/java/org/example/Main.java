package org.example;

import org.example.analyzer.Analyzer;
import org.example.analyzer.Token;
import org.example.analyzer.builder.AnalyzerBuilder;
import org.example.analyzer.enums.CharFilterType;
import org.example.analyzer.enums.TokenFilterType;
import org.example.analyzer.enums.TokenizerType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Build analyzer
        Analyzer a = AnalyzerBuilder.create()
                .addCharFilter(CharFilterType.HTML_STRIP)
                .addCharFilter(CharFilterType.NORMALIZE_DASHES)
                .addCharFilter(CharFilterType.MAPPING_SMART_QUOTES)
                .tokenizer(TokenizerType.STANDARD)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .addTokenFilter(TokenFilterType.ASCII_FOLD)
                .build();

        // Analyze text
        List<Token> tokens = a.analyze("<p>Héllo—World! It’s a test.</p>");

        // Print tokens
        System.out.println("Tokens:");
        for (Token t : tokens) {
            System.out.println(t.getText());
        }
    }
}
