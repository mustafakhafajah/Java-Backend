package org.example.analyzer.charFilter;

import org.example.analyzer.enums.CharFilterType;

public class CharFilterFactory {
    public static CharFilterTypeInterface getCharFilterType(CharFilterType  input) {
        if  (input == null) {
            return null;
        }
        return switch (input) {
            case HTML_STRIP -> new HtmlStrip();
            case MAPPING_SMART_QUOTES -> new MappingSmartQoutes();
            case NORMALIZE_DASHES -> new NormalizeDashes();
            default ->
                    throw new IllegalArgumentException(String.format("Input '%s' is not a valid CharFilterType.", input));
        };

    }
}
