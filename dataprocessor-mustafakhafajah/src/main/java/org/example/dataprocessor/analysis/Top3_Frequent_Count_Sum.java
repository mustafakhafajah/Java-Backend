package org.example.dataprocessor.analysis;

import java.util.*;

public class Top3_Frequent_Count_Sum implements AnalysisInterface {

    @Override
    public double clean(List<Integer> data) {
        if (data == null || data.isEmpty()) {
            return 0;
        }

        // Count frequencies using a Map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : data) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }


        List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());


        frequencyList.sort((a, b) -> {
            int cmp = b.getValue().compareTo(a.getValue());
            if (cmp == 0) {
                return a.getKey().compareTo(b.getKey());
            }
            return cmp;
        });

        int sum = 0;
        for (int i = 0; i < Math.min(3, frequencyList.size()); i++) {
            sum += frequencyList.get(i).getValue();
        }

        return sum;
    }
}
