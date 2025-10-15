package org.example.dataprocessor.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
AnalysisType type
if(type==Median){return new Median();

 */
public class Median implements AnalysisInterface {
    @Override
    public double clean(List<Integer> data) {
        if (data == null || data.isEmpty()) {
            return 0;
        }
        List<Integer> listForMean=new ArrayList<>(data);
        Collections.sort(listForMean);
        double median1 =listForMean.get((listForMean.size()/2)-1);
        double median2 =listForMean.get(listForMean.size()/2);
        if (listForMean.size()%2==0){
            return ((median1 + median2 )/2.0);
        }
        else {
            return (median2);
        }

    }
}
