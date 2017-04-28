package org.xm.xmnlp.test.algorithm;


import junit.framework.TestCase;
import org.xm.xmnlp.collection.DartMap;
import org.xm.xmnlp.collection.trie.DoubleArrayTrie;
import org.xm.xmnlp.util.IOUtil;

import java.util.*;

public class DartMapTest extends TestCase {
    private static final String DATA_TEST_OUT_BIN = "data/out.bin";
    Set<String> validKeySet;
    Set<String> invalidKeySet;
    private DartMap<Integer> dartMap;

    public void setUp() throws Exception {
        IOUtil.LineIterator iterator = new IOUtil.LineIterator("data/dictionary/CoreNatureDictionary.ngram.txt");
        validKeySet = new TreeSet<String>();
        while (iterator.hasNext()) {
            validKeySet.add(iterator.next().split("\\s")[0]);
        }
    }

    public void testGenerateInvalidKeySet() throws Exception {
        invalidKeySet = new TreeSet<String>();
        Random random = new Random(System.currentTimeMillis());
        while (invalidKeySet.size() < validKeySet.size()) {
            int length = random.nextInt(10) + 1;
            StringBuilder key = new StringBuilder(length);
            for (int i = 0; i < length; ++i) {
                key.append(random.nextInt(Character.MAX_VALUE));
            }
            if (validKeySet.contains(key.toString())) continue;
            invalidKeySet.add(key.toString());
        }
    }

    public void testBuild() throws Exception {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        for (String key : validKeySet) {
            map.put(key, key.length());
        }

        dartMap = new DartMap<Integer>(map);
    }

    public void testContainsAndNoteContains() throws Exception {
        testBuild();
        for (String key : validKeySet) {
            assertEquals(key.length(), (int) dartMap.get(key));
        }

        testGenerateInvalidKeySet();
        for (String key : invalidKeySet) {
            assertEquals(null, dartMap.get(key));
        }
    }

    public void testCommPrefixSearch() throws Exception {
        testBuild();
        System.out.println(dartMap.commonPrefixSearch("一举一动"));
    }

    public void testBenchmark() throws Exception {
        testBuild();
        long start;
        {

        }
        DoubleArrayTrie<Integer> trie = new DoubleArrayTrie<Integer>();
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        for (String key : validKeySet) {
            map.put(key, key.length());
        }
        trie.build(map);

        // TreeMap
        start = System.currentTimeMillis();
        for (String key : validKeySet) {
            assertEquals(key.length(), (int) map.get(key));
        }
        System.out.printf("TreeMap: %d ms\n", System.currentTimeMillis() - start);
        map = null;
        // DAT
        start = System.currentTimeMillis();
        for (String key : validKeySet) {
            assertEquals(key.length(), (int) trie.get(key));
        }
        System.out.printf("DAT: %d ms\n", System.currentTimeMillis() - start);
        trie = null;
        // DAWG
        start = System.currentTimeMillis();
        for (String key : validKeySet) {
            assertEquals(key.length(), (int) dartMap.get(key));
        }
        System.out.printf("DAWG: %d ms\n", System.currentTimeMillis() - start);

        /**
         * result:
         * TreeMap: 677 ms
         * DAT: 310 ms
         * DAWG: 858 ms
         *
         * 结论，虽然很省内存，但是速度不理想
         */
    }

    public void testTrie() throws Exception {
        validKeySet = new TreeSet<String>();
        IOUtil.LineIterator iterator = new IOUtil.LineIterator("data/dictionary/CoreNatureDictionary.mini.txt");
        TreeMap<String, String[]> map = new TreeMap<>();
        while (iterator.hasNext()) {
            String[] split = iterator.next().split("\\s");
            String[] value = new String[3];
            value[0] = split[1];
            value[1] = split[2];
            value[2] = split[0];
            map.put(split[0], value);
            if (validKeySet.size() < 200)
                validKeySet.add(split[0]);
        }

        long start = System.currentTimeMillis();
        DoubleArrayTrie<String[]> trie = new DoubleArrayTrie<String[]>();
        trie.build(map);
        System.out.printf("build map: %d ms\n", System.currentTimeMillis() - start);

        // TreeMap one word
        start = System.currentTimeMillis();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if (entry.getKey().equals("龟甲")) {
                String[] val = entry.getValue();
                System.out.println(val[0] + val[1] + val[2]);
            }
        }
        System.out.printf("TreeMap one word: %d ms\n", System.currentTimeMillis() - start);
        // TreeMap
        start = System.currentTimeMillis();
        int count = 0;
        for (String key : validKeySet) {
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                if (entry.getKey().equals(key)) {
                    if (entry.getValue() != null && entry.getValue().length > 1)
                        count++;
                }
            }

        }
        System.out.println("TreeMap count:" + count);
        System.out.printf("TreeMap: %d ms\n", System.currentTimeMillis() - start);

        // DAT one word
        start = System.currentTimeMillis();
        String[] val = trie.get("龟甲");
        System.out.println(val[0] + val[1] + val[2]);
        System.out.printf("DAT one word: %d ms\n", System.currentTimeMillis() - start);

        // DAT
        start = System.currentTimeMillis();
        count = 0;
        for (String key : validKeySet) {
            String[] value = trie.get(key);
            if (value != null && value.length > 1) {
                System.out.println(value[0] + value[1] + value[2]);
                count++;
            }
        }
        System.out.println("DAT count:" + count);
        System.out.printf("DAT : %d ms\n", System.currentTimeMillis() - start);

        /**
         * result:
         * build map: 216 ms
         * n1龟甲
         * TreeMap: 10 ms
         * n1龟甲
         * DAT: 0 ms
         *
         * 结论，很省内存，速度理想
         */
    }

}