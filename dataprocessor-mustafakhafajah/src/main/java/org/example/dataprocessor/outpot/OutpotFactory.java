package org.example.dataprocessor.outpot;

import org.example.dataprocessor.enums.OutputType;

public class OutpotFactory {
    public static OutpotInterface getOutput(OutputType type) {
        switch (type) {
            case CONSOLE:
                return new Console();
            case TEXT_FILE:
                return new TextFile();
            default:
                throw new IllegalArgumentException("Unknown OutputType: " + type);
        }
    }
}
