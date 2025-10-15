package org.example.dataprocessor;

import org.example.dataprocessor.analysis.*;
import org.example.dataprocessor.outpot.OutpotInterface;
import org.example.dataprocessor.outpot.TextFile;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example dataset
        List<Integer> data = Arrays.asList(5, 5, 2, 2, 2, 3, 3, 4, 10, 20);

        // Run analyses
        AnalysisInterface meanAnalysis = new Mean();
        AnalysisInterface p90Analysis = new P90_Nearest_Rank();
        AnalysisInterface top3Analysis = new Top3_Frequent_Count_Sum();

        double meanResult = meanAnalysis.clean(data);
        double p90Result = p90Analysis.clean(data);
        double top3Result = top3Analysis.clean(data);

        // Prepare output as text
        String resultText = ""
                + "Input Data: " + data + "\n"
                + "Mean = " + meanResult + "\n"
                + "P90 (Nearest Rank) = " + p90Result + "\n"
                + "Top3 Frequent Count Sum = " + top3Result + "\n";

        // Print to console
        System.out.println(resultText);

        // Save results to file
        OutpotInterface fileOutput = new TextFile();
        fileOutput.outpot("analysis_output.txt", resultText);
    }
}
