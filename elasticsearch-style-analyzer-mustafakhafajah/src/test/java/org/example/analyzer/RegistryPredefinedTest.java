package org.example.analyzer;

import org.example.analyzer.registry.AnalyzerRegistry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RegistryPredefinedTest {
    @Test
    void default_registry_has_standard_and_english() {
        Analyzer standard = AnalyzerRegistry.get("standard");
        Analyzer english  = AnalyzerRegistry.get("english");

        List<String> t1 = standard.analyze("<h1>Résumé—Examples</h1>")
                .stream().map(t -> t.term).toList();
        assertEquals(List.of("resume","examples"), t1);

        List<String> t2 = english.analyze("It’s the running tests")
                .stream().map(t -> t.term).toList();
        assertEquals(List.of("it's","run","test"), t2);
    }
}
