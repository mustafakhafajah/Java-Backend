package org.example.analyzer.tokenizerType;

import org.example.analyzer.Token;

import java.util.ArrayList;
import java.util.List;

public class Standard implements TokenizerTypeInterface {
    @Override
    public List<Token> tokenize(String input) {
        if (input == null || input.isEmpty()){
            return null;}
        String[] input2 = input.split("\\p{L}[\\p{L}\\p{Mn}\\p{Nd}_']*");
        // في مشكلة في الاتبوت حلها
        List<Token> tokenList = new ArrayList<Token>();
        for (String s : input2) {
            tokenList.add(new Token(s));

        }
        return tokenList;
    }
}
