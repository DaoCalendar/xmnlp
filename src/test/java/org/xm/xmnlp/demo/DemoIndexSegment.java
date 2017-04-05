package org.xm.xmnlp.demo;


import org.xm.xmnlp.seg.domain.Result;
import org.xm.xmnlp.seg.domain.Term;
import org.xm.xmnlp.tokenizer.IndexTokenizer;

/**
 * 索引分词
 */
public class DemoIndexSegment {
    public static void main(String[] args) {
        Result termList = IndexTokenizer.segment("主副食品");
        for (Term term : termList) {
            System.out.println(term + " [" + term.offset + ":" + (term.offset + term.word.length()) + "]");
        }
    }
}
