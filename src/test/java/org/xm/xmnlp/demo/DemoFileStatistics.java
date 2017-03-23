package org.xm.xmnlp.demo;

import org.xm.xmnlp.Xmnlp;
import org.xm.xmnlp.seg.Segment;
import org.xm.xmnlp.seg.domain.Term;
import org.xm.xmnlp.util.IOUtil;
import org.xm.xmnlp.util.WordFreqStatistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 对文件分词并统计
 *
 * @author xuming
 */
public class DemoFileStatistics {
    public static final Segment SEGMENT = Xmnlp.newSegment().enableNameRecognize(true)
            .enablePlaceRecognize(true).enablePartOfSpeechTagging(true).enableMultithreading(4);


    public static void main(String[] args) throws IOException {
        String filePath = "data/test/corpus-bajin-1942-novel.txt";
        statistics(filePath);

        BufferedReader reader = IOUtil.getReader(filePath, "utf-8");
        long allCount = 0;
        long lexCount = 0;
        long start = System.currentTimeMillis();
        String temp;
        FileOutputStream fos = new FileOutputStream(new File(filePath.replace(".txt","") +"-Segmented-Result.txt"));
        while ((temp = reader.readLine()) != null) {
            List<Term> parse = SEGMENT.seg(temp);
            StringBuilder sb = new StringBuilder();
            for (Term term : parse) {
                sb.append(term.toString() + "\t");
                if (term.word.trim().length() > 0) {
                    allCount += term.length();
                    lexCount += 1;
                }
            }
            fos.write(sb.toString().trim().getBytes());
            fos.write("\n".getBytes());
        }

        fos.flush();
        fos.close();
        reader.close();
        long end = System.currentTimeMillis();
        System.out.println("共 " + allCount + " 个字符，共 " + lexCount + " 个词语，每秒处理了:" + (allCount * 1000 / (end - start)));
    }

    public static void statistics(String filePath) throws IOException {

        //词频统计
        WordFreqStatistics statistic = new WordFreqStatistics(SEGMENT);

        BufferedReader reader = IOUtil.getReader(filePath, "utf-8");
        String t;
        StringBuilder s = new StringBuilder();
        while ((t = reader.readLine()) != null) {
            s.append(t);
        }
        statistic.seg(s.toString());
        statistic.setResultPath(filePath.replace(".txt","") + "-WordFrequencyStatistics-Result.txt");
        statistic.dump();
        reader.close();
    }
}
