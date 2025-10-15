package org.example.analyzer.enums;

public enum TokenFilterType {
    LOWERCASE,
    STOP_EN,                // stopwords: the,and,of,to,in,a,an,is,it,for,on
    ASCII_FOLD,             // remove diacritics
    PORTER_LITE,            // strip (ing|ed|es|s|ly) suffixes
    EDGE_NGRAM_1_3          // emit grams of length 1..3 for each term
}