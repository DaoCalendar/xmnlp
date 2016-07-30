package org.xm.xmnlp.test.seg;


import junit.framework.TestCase;
import org.xm.xmnlp.dictionary.BaseSearcher;
import org.xm.xmnlp.dictionary.CustomDictionary;

import java.util.Map;

public class TestCustomDictionary extends TestCase {
    public static void main(String[] args) {
        BaseSearcher searcher = CustomDictionary.getSearcher("我是一个码农");
        Map.Entry entry;
        while ((entry = searcher.next()) != null) {
            System.out.println(entry);
        }
    }

    public void testAdd() throws Exception {
        String word = "裸婚";
        System.out.println(CustomDictionary.add(word, "nz 1 v 1"));
        System.out.println(CustomDictionary.get(word));
    }
}
