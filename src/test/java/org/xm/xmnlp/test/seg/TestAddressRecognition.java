package org.xm.xmnlp.test.seg;

import junit.framework.TestCase;
import org.xm.xmnlp.seg.NShortSegment;

public class TestAddressRecognition extends TestCase {
    public static void main(String[] args) {
        System.out.println(NShortSegment.parse("地址：乌鲁木齐南路218、228"));
    }
}
