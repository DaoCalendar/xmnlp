package org.xm.xmnlp.test.seg;


import junit.framework.TestCase;
import org.xm.xmnlp.corpus.tag.NR;
import org.xm.xmnlp.dictionary.TransformMatrixDictionary;

/**
 * @author hankcs
 */
public class TestTRMDictionary extends TestCase {
    public void testLoad() throws Exception {
        TransformMatrixDictionary<NR> nrTransformMatrixDictionary = new TransformMatrixDictionary<NR>(NR.class);
        nrTransformMatrixDictionary.load("data/dictionary/person/nr.tr.txt");
        System.out.println(nrTransformMatrixDictionary.getFrequency(NR.A, NR.A));
        System.out.println(nrTransformMatrixDictionary.getFrequency("A", "A"));
        System.out.println(nrTransformMatrixDictionary.getTotalFrequency());
        System.out.println(nrTransformMatrixDictionary.getTotalFrequency(NR.Z));
        System.out.println(nrTransformMatrixDictionary.getTotalFrequency(NR.A));
    }
}
