package org.example.analyzer.registry;

import org.example.analyzer.Analyzer;
import org.example.analyzer.builder.AnalyzerBuilder;
import org.example.analyzer.enums.CharFilterType;
import org.example.analyzer.enums.TokenFilterType;
import org.example.analyzer.enums.TokenizerType;

import java.util.HashMap;
import java.util.Map;

public class AnalyzerRegistry {
    private static final Map<String, Analyzer> analyzers = new HashMap<>();

    static {
        Analyzer standard = AnalyzerBuilder.create()
                .addCharFilter(CharFilterType.HTML_STRIP)
                .tokenizer(TokenizerType.STANDARD)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .addTokenFilter(TokenFilterType.ASCII_FOLD)
                .build();

        Analyzer english = AnalyzerBuilder.create()
                .addCharFilter(CharFilterType.HTML_STRIP)
                .addCharFilter(CharFilterType.MAPPING_SMART_QUOTES)
                .tokenizer(TokenizerType.STANDARD)
                .addTokenFilter(TokenFilterType.ASCII_FOLD)
                .addTokenFilter(TokenFilterType.STOP_EN)
                .addTokenFilter(TokenFilterType.PORTER_LITE)
                .build();

        register("standard", standard);
        register("english", english);
    }

    public static void register(String name, Analyzer analyzer) {
        analyzers.put(name.toLowerCase(), analyzer);
    }

    public static Analyzer get(String name) {
        Analyzer analyzer = analyzers.get(name.toLowerCase());
        if (analyzer == null) {
            throw new IllegalArgumentException("Analyzer not found: " + name);
        }
        return analyzer;
    }
}
