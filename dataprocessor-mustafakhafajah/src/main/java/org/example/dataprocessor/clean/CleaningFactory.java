package org.example.dataprocessor.clean;

import org.example.dataprocessor.enums.CleaningType;

public class CleaningFactory {

        public static CleanningInterface getCleaning(CleaningType type) {
            switch (type) {
                case REMOVE_ZEROS:
                    return new RemoveZeros();
                case REPLACE_NEGATIVES_WITH_ZEROS:
                    return new ReplaceNegativesWithZeros();
                default:
                    throw new IllegalArgumentException("Unknown CleaningType: " + type);
            }
        }
}

