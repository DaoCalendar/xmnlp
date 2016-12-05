package org.xm.xmnlp.demo;


import org.xm.xmnlp.Xmnlp;
import org.xm.xmnlp.seg.domain.Term;
import org.xm.xmnlp.tokenizer.NLPTokenizer;

import java.util.List;

/**
 * NLP分词
 */
public class DemoNLPSegment {
    public static void main(String[] args) {
        Xmnlp.Config.enableDebug();
        List<Term> termList = NLPTokenizer.segment("上外日本文化经济学院的陆晚霞教授正在教授泛读课程，这个研究生会五种语言");
        System.out.println(termList);
    }
}
