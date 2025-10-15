package org.example.analyzer.builder;

import org.example.analyzer.Analyzer;
import org.example.analyzer.AnalyzerImpl;
import org.example.analyzer.enums.CharFilterType;
import org.example.analyzer.enums.TokenFilterType;
import org.example.analyzer.enums.TokenizerType;

import java.util.ArrayList;
import java.util.List;

public class AnalyzerBuilder {
    private final List<CharFilterType> charFilters = new ArrayList<>();
    private final List<TokenFilterType> tokenFilters = new ArrayList<>();
    private TokenizerType tokenizer;

    private AnalyzerBuilder() {}

    public static AnalyzerBuilder create() {
        return new AnalyzerBuilder();
    }

    public AnalyzerBuilder addCharFilter(CharFilterType type) {
        this.charFilters.add(type);
        return this;
    }

    public AnalyzerBuilder tokenizer(TokenizerType type) {
        this.tokenizer = type;
        return this;
    }

    public AnalyzerBuilder addTokenFilter(TokenFilterType type) {
        this.tokenFilters.add(type);
        return this;
    }

    /** Build an Analyzer that executes: charFilters -> tokenizer -> tokenFilters */
    public Analyzer build() {
        if (tokenizer == null) {
            throw new IllegalStateException("Tokenizer must be set before building Analyzer");
        }
        return new AnalyzerImpl(charFilters, tokenizer, tokenFilters);
    }
}
