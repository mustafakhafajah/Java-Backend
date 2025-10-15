package org.example.dataprocessor.analysis;

import java.util.ArrayList;
import java.util.List;

public class P90_Nearest_Rank implements AnalysisInterface {

    @Override
    public double clean(List<Integer> data) {
        List<Integer> listForRanking=new ArrayList<>(data);
        listForRanking.sort(null);
        int N = data.size();
        double rank = N * .9;
        int p90_nearest_rank = (int) Math.ceil(rank);
        return p90_nearest_rank;

    }
}
