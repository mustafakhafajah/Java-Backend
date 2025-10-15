package org.example.analyzer;

import org.example.analyzer.charFilter.CharFilterFactory;
import org.example.analyzer.charFilter.CharFilterTypeInterface;
import org.example.analyzer.enums.CharFilterType;
import org.example.analyzer.enums.TokenFilterType;
import org.example.analyzer.enums.TokenizerType;
import org.example.analyzer.tokenFilterType.TokenFilterTypeFactory;
import org.example.analyzer.tokenFilterType.TokenFilterTypeInterface;
import org.example.analyzer.tokenizerType.TokenizerTypeFactory;
import org.example.analyzer.tokenizerType.TokenizerTypeInterface;

import java.util.ArrayList;
import java.util.List;

public class AnalyzerImpl implements Analyzer {
    private final List<CharFilterType> charFilters;
    private final TokenizerType tokenizer;
    private final List<TokenFilterType> tokenFilters;

    public AnalyzerImpl(List<CharFilterType> charFilters,
                        TokenizerType tokenizer,
                        List<TokenFilterType> tokenFilters) {
        this.charFilters = charFilters;
        this.tokenizer = tokenizer;
        this.tokenFilters = tokenFilters;
    }

    @Override
    public List<Token> analyze(String text) {
        String processed = text;

        for (CharFilterType cf : charFilters) {
            CharFilterTypeInterface charFilter = CharFilterFactory.getCharFilterType(cf);
            processed = charFilter.filter(processed);
        }

        TokenizerTypeInterface tokenizerImpl = TokenizerTypeFactory.getTokenizer(tokenizer);
        List<Token> tokens = tokenizerImpl.tokenize(processed);

        for (TokenFilterType tf : tokenFilters) {
            TokenFilterTypeInterface tokenFilter = TokenFilterTypeFactory.createTokenFilterType(tf);
            tokens = tokenFilter.token(tokens);  // method name 'token' from your interface
        }

        return new ArrayList<>(tokens);
    }
}
