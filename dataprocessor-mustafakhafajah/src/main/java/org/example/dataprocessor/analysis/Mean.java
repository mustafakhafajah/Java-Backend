package org.example.dataprocessor.analysis;

import java.util.List;

public class Mean implements AnalysisInterface {

    @Override
    public double clean(List<Integer> data) {
        if (data == null || data.isEmpty()) {
            return (int) Double.NaN;
        }

        int sum = 0;
        for (Integer i : data) {
            sum += i;
        }
        int mean = sum / data.size();
        System.out.println("Mean = " + mean);

        return mean;
    }

}
