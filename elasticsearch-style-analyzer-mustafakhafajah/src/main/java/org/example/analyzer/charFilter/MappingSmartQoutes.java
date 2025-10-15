package org.example.analyzer.charFilter;

public class MappingSmartQoutes implements CharFilterTypeInterface {
    @Override
    public String filter(String input) {
        input = input.replace("’" , "'");
        input = input.replace("“" , "'");
        input = input.replace("”" , "'");
        return input;
    }
}
