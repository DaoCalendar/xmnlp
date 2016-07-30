package org.xm.xmnlp.test.algorithm;


import junit.framework.TestCase;
import org.xm.xmnlp.collection.trie.bintrie.BinTrie;

/**
 * 测试二进制存储
 */
public class TestBinTrie extends TestCase {
    public void testPut() throws Exception {
        BinTrie<Boolean> trie = new BinTrie<Boolean>();
        trie.put("加入", true);
        trie.put("加入", false);

        assertEquals(new Boolean(false), trie.get("加入"));
    }

    public void testArrayIndexOutOfBoundsException() throws Exception {
        BinTrie<Boolean> trie = new BinTrie<Boolean>();
        trie.put(new char[]{'\uffff'}, true);
    }
}
