package org.example.analyzer.tokenFilterType;

import org.example.analyzer.enums.TokenFilterType;

public class TokenFilterTypeFactory {

    public static TokenFilterTypeInterface createTokenFilterType(TokenFilterType type) {
        return switch (type) {
            case LOWERCASE -> new Lowercase();
            case ASCII_FOLD -> new AsciiFold();
            case STOP_EN -> new StopEn();
            case PORTER_LITE -> new PorterLite();
            case EDGE_NGRAM_1_3 -> new OneToThreeEdgeNGram();
        };
    }
}
