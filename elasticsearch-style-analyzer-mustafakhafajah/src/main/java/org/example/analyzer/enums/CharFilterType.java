package org.example.analyzer.enums;

public enum CharFilterType {
    HTML_STRIP,             // remove <tags>
    MAPPING_SMART_QUOTES,   // ’ → ', “ ” → "
    NORMALIZE_DASHES        // – — → -
}
