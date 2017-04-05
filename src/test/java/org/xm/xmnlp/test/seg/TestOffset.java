package org.xm.xmnlp.test.seg;


import junit.framework.TestCase;
import org.xm.xmnlp.seg.domain.Result;
import org.xm.xmnlp.seg.domain.Term;
import org.xm.xmnlp.tokenizer.IndexTokenizer;

public class TestOffset extends TestCase {
    public void testOffset() throws Exception {
        String text = "中华人民共和国在哪里";
        for (int i = 0; i < text.length(); ++i) {
            System.out.print(text.charAt(i) + "" + i + " ");
        }
        System.out.println();
        Result termList = IndexTokenizer.segment(text);
        for (Term term : termList) {
            System.out.println(term.word + " " + term.offset + " " + (term.offset + term.length()));
        }
    }
}
