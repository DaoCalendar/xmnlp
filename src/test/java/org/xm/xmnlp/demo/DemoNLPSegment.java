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
        List<Term> termList = NLPTokenizer.segment("上外日本文化经济学院的陆晚霞教授正在教授泛读课程，nlp是由一系列模型与算法组成的Java工具包，目标是普及自然语言处理在生产环境中的应用。");
        System.out.println(termList);
    }
}
