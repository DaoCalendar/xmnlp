package org.xm.xmnlp.test.algorithm;


import junit.framework.TestCase;
import org.xm.xmnlp.math.LongestCommonSubsequence;

public class LongestCommonSubsequenceTest extends TestCase {
    String a = "Tom fff";
    String b = "fff";

    public void testCompute() throws Exception {
        System.out.println(LongestCommonSubsequence.compute(a, b));
    }
}