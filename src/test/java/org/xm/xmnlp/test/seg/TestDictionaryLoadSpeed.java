package org.xm.xmnlp.test.seg;


import junit.framework.TestCase;
import org.xm.xmnlp.dictionary.CoreDictionary;

/**
 * @author hankcs
 */
public class TestDictionaryLoadSpeed extends TestCase {
    public void testCoreDictionary() throws Exception {
        System.out.println(CoreDictionary.get("速度"));
    }

}
