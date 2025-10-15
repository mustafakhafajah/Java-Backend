package org.example.dataprocessor.clean;

import java.util.ArrayList;
import java.util.List;

public class  ReplaceNegativesWithZeros implements CleanningInterface {

    @Override
    public List<Integer> clean(List<Integer> data) {
        List<Integer> listWithoutNegatives = new ArrayList<>();
        for (Integer i : data) {
            if (i >= 0) {
                listWithoutNegatives.add(i);
            }
            else {
                listWithoutNegatives.add(0);
            }
        }
        return listWithoutNegatives;
    }
}
