package org.example.analyzer;

import org.example.analyzer.builder.AnalyzerBuilder;
import org.example.analyzer.enums.CharFilterType;
import org.example.analyzer.enums.TokenFilterType;
import org.example.analyzer.enums.TokenizerType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MixAnalyzerTest {

    @Test
    void html_mapping_dash_standard_lower_ascii_stop() {
        String text = "<p>Résumé—it’s THE best!</p>";
        Analyzer a = AnalyzerBuilder.create()
                .addCharFilter(CharFilterType.HTML_STRIP)
                .addCharFilter(CharFilterType.NORMALIZE_DASHES)
                .addCharFilter(CharFilterType.MAPPING_SMART_QUOTES)
                .tokenizer(TokenizerType.STANDARD)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .addTokenFilter(TokenFilterType.ASCII_FOLD)
                .addTokenFilter(TokenFilterType.STOP_EN) // removes "the"
                .build();

        List<String> terms = a.analyze(text).stream().map(t -> t.term).toList();
        assertEquals(List.of("resume", "it's", "best"), terms);
    }

    @Test
    void whitespace_then_stop_then_ngrams() {
        Analyzer a = AnalyzerBuilder.create()
                .tokenizer(TokenizerType.WHITESPACE)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .addTokenFilter(TokenFilterType.STOP_EN)          // removes "the"
                .addTokenFilter(TokenFilterType.EDGE_NGRAM_1_3)    // ngrams on remaining terms
                .build();

        List<String> terms = a.analyze("The Flow").stream().map(t -> t.term).toList();
        // "The" removed, "Flow" -> "flow" -> edge ngrams: f, fl, flo
        assertEquals(List.of("f", "fl", "flo"), terms);
    }

    @Test
    void standard_porter_and_ascii_fold() {
        Analyzer a = AnalyzerBuilder.create()
                .tokenizer(TokenizerType.STANDARD)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .addTokenFilter(TokenFilterType.ASCII_FOLD)
                .addTokenFilter(TokenFilterType.PORTER_LITE)
                .build();

        List<String> terms = a.analyze("Running runners tested").stream().map(t -> t.term).toList();
        // With lite rules: running->run, runners->runner (strip trailing 's'), tested->test
        assertEquals(List.of("run", "runner", "test"), terms);
    }

    @Test
    void mapping_quotes_effect_with_stopwords() {
        Analyzer a = AnalyzerBuilder.create()
                .addCharFilter(CharFilterType.MAPPING_SMART_QUOTES)
                .tokenizer(TokenizerType.STANDARD)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .addTokenFilter(TokenFilterType.ASCII_FOLD)
                .addTokenFilter(TokenFilterType.STOP_EN) // will NOT remove "it's" (single token)
                .build();

        List<String> terms = a.analyze("He said: “Hello” — It’s fine.").stream().map(t -> t.term).toList();
        // After mapping: quotes become straight; stop_en removes only standalone stopwords (none here)
        assertEquals(List.of("he", "said", "hello", "it's", "fine"), terms);
    }

    @Test
    void null_and_empty_inputs_return_empty_list() {
        Analyzer a = AnalyzerBuilder.create()
                .tokenizer(TokenizerType.WHITESPACE)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .build();

        assertEquals(List.of(), a.analyze(null).stream().map(t -> t.term).toList());
        assertEquals(List.of(), a.analyze("").stream().map(t -> t.term).toList());
        assertEquals(List.of(), a.analyze("   \t\n ").stream().map(t -> t.term).toList());
    }
}
