package org.example.dataprocessor.analysis;

import java.util.List;

public class STD_DEV implements AnalysisInterface {

    @Override
    public double clean(List<Integer> data) {
        if (data == null || data.isEmpty()) {
            return Integer.parseInt(null);
        }

        int N = data.size();

        double sum = 0.0;
        for (int value : data) {
            sum += value;
        }
        double mean = sum / N;

        double squaredDiffSum = 0.0;
        for (int value : data) {
            squaredDiffSum += Math.pow(value - mean, 2);
        }

        double variance = squaredDiffSum / N;

        double stdDev = Math.sqrt(variance);

        return (int) stdDev;
    }
}
