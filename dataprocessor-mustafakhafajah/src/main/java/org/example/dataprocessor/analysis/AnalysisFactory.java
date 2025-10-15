package org.example.dataprocessor.analysis;

import org.example.dataprocessor.enums.AnalysisType;

public class AnalysisFactory {

    public static AnalysisInterface getAnalysis(AnalysisType type) {
        switch (type) {
            case MEAN:
                return new Mean();
            case MEDIAN:
                return new Median();
            case P90_NEAREST_RANK:
                return new P90_Nearest_Rank();
            case STD_DEV:
                return new STD_DEV();
            case TOP3_FREQUENT_COUNT_SUM:
                return new Top3_Frequent_Count_Sum();
            default:
                throw new IllegalArgumentException("Unknown AnalysisType: " + type);
        }
    }
}
