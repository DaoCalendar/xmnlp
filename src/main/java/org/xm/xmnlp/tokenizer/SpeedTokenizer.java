package org.xm.xmnlp.tokenizer;

import org.xm.xmnlp.seg.Segment;
import org.xm.xmnlp.seg.domain.Result;
import org.xm.xmnlp.seg.domain.Term;
import org.xm.xmnlp.seg.other.DoubleArrayTrieSegment;

import java.util.List;

/**
 * 快速分词
 * 基于Double Array Trie实现的词典分词，适用于“高吞吐量”“精度一般”的场合
 *
 * @author xuming
 */
public class SpeedTokenizer {
    public static final Segment SEGMENT = new DoubleArrayTrieSegment();

    public static Result segment(String text) {
        return segment(text.toCharArray());
    }

    /**
     * 分词
     *
     * @param text 文本
     * @return 分词结果
     */
    public static Result segment(char[] text) {
        return new Result(SEGMENT.seg(text));
    }

    /**
     * 切分为句子形式
     *
     * @param text 文本
     * @return 句子列表
     */
    public static List<List<Term>> seg2sentence(String text) {
        return SEGMENT.seg2sentence(text);
    }

}
