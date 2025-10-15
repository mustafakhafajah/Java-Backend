package org.example.analyzer;

import org.example.analyzer.builder.AnalyzerBuilder;
import org.example.analyzer.enums.CharFilterType;
import org.example.analyzer.enums.TokenFilterType;
import org.example.analyzer.enums.TokenizerType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyzerStandardTest {
    @Test
    void standard_pipeline_basic() {
        String text = "<p>Héllo—World! It’s a test.</p>";
        Analyzer a = AnalyzerBuilder.create()
                .addCharFilter(CharFilterType.HTML_STRIP)
                .addCharFilter(CharFilterType.NORMALIZE_DASHES)
                .addCharFilter(CharFilterType.MAPPING_SMART_QUOTES)
                .tokenizer(TokenizerType.STANDARD)
                .addTokenFilter(TokenFilterType.LOWERCASE)
                .addTokenFilter(TokenFilterType.ASCII_FOLD)
                .build();

        List<String> terms = a.analyze(text).stream().map(t -> t.term).toList();

        assertEquals(List.of("hello","world","it's","a","test"), terms);
    }
}
