package org.xm.xmnlp.test.corpus;


import junit.framework.TestCase;
import org.xm.xmnlp.Xmnlp;
import org.xm.xmnlp.corpus.dictionary.maker.TFDictionary;

/**
 * 测试合并多个NGram词典
 *
 * @author hankcs
 */
public class TestCombineNGramDictionary extends TestCase {
    public void testCombine() throws Exception {
        System.out.println(TFDictionary.combine(Xmnlp.Config.BiGramDictionaryPath, "XXXDictionary.ngram.txt"));
    }
}
