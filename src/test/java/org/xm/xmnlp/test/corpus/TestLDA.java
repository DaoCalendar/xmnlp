package org.xm.xmnlp.test.corpus;


import junit.framework.TestCase;
import org.xm.xmnlp.Xmnlp;
import org.xm.xmnlp.dictionary.stopword.CoreStopWordDictionary;
import org.xm.xmnlp.seg.domain.Result;
import org.xm.xmnlp.seg.domain.Term;
import org.xm.xmnlp.util.IOUtil;

import java.io.File;
import java.util.List;

public class TestLDA extends TestCase {
    public void testSegmentCorpus() throws Exception {
        String fileFolderPath = "data/test";
        long start = System.currentTimeMillis();
        File root = new File(fileFolderPath);
        for (File folder : root.listFiles()) {
            if (folder.isDirectory()) {
                for (File file : folder.listFiles()) {
                    System.out.println(file.getAbsolutePath());
                    Result termList = Xmnlp.segment(IOUtil.readTxt(file.getAbsolutePath()));
                    StringBuilder sbOut = new StringBuilder();
                    for (Term term : termList) {
                        if (CoreStopWordDictionary.shouldInclude(term)) {
                            sbOut.append(term.word).append(" ");
                        }
                    }
                    IOUtil.saveTxt("data\\" + folder.getName() + "_segment_" + file.getName(), sbOut.toString());
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("共用时:" + (end - start) + "ms");
    }

    public void testSummaryCorpus() throws Exception {
        String fileFolderPath = "data/test";
        long start = System.currentTimeMillis();
        File root = new File(fileFolderPath);
        for (File folder : root.listFiles()) {
            if (folder.isDirectory()) {
                for (File file : folder.listFiles()) {
                    System.out.println(file.getAbsolutePath());
                    List<String> sentenceList = Xmnlp.extractSummary(IOUtil.readTxt(file.getAbsolutePath()), 3);
                    StringBuilder sbOut = new StringBuilder();
                    for (String term : sentenceList) {
                        sbOut.append(term).append("\n");
                    }
                    IOUtil.saveTxt("data\\" + folder.getName() + "_summary_" + file.getName(), sbOut.toString());
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("共用时:" + (end - start) + "ms");
    }
}
