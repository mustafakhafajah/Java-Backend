package org.example.analyzer;

import org.example.analyzer.builder.AnalyzerBuilder;
import org.example.analyzer.enums.TokenFilterType;
import org.example.analyzer.enums.TokenizerType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyzerNgramTest {
    @Test
    void whitespace_plus_ngrams() {
        Analyzer a = AnalyzerBuilder.create()
                .tokenizer(TokenizerType.WHITESPACE)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .addTokenFilter(TokenFilterType.EDGE_NGRAM_1_3)
                .build();

        List<String> terms = a.analyze("Flow").stream().map(t -> t.term).toList();

        assertEquals(List.of("f","fl","flo"), terms);
    }
}
