package org.example.dataprocessor.clean;

import java.util.ArrayList;
import java.util.List;

public class RemoveZeros implements CleanningInterface {

    @Override
    public List<Integer> clean(List<Integer> data) {
        List <Integer> listWithoutZeros =  new ArrayList<Integer>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) != 0) {
                listWithoutZeros.add(data.get(i));
            }

        }
        return listWithoutZeros;

    }
}
