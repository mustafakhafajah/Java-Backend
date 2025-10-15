package org.example.analyzer.tokenizerType;


import org.example.analyzer.Token;

import java.util.ArrayList;
import java.util.List;

public class Whitespace implements TokenizerTypeInterface {
    @Override
    public List<Token> tokenize(String input) {
        if (input ==null || input.isEmpty()){
            return null;
        }
        String[] input2 = input.split("\\s+");
        List<Token> TokenList = new ArrayList<Token>();
        for (String s : input2) {
            TokenList.add(new Token(s));
        }
        return TokenList;
    }
}
