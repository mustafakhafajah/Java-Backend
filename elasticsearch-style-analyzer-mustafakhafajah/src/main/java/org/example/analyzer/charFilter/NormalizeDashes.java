package org.example.analyzer.charFilter;

public class NormalizeDashes implements CharFilterTypeInterface {
    public String filter(String input) {
        input = input.replace("–" , "-");
        input = input.replace("—" , "'");
        return input;
    }
}
