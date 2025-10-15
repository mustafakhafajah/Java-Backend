package org.example.analyzer.charFilter;

public class HtmlStrip implements CharFilterTypeInterface {
    @Override
    public String filter(String input) {
    input.replaceAll("<[^>]*>", "");
    return input;
    }
}
