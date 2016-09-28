package org.xm.ansj.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xm.ansj.domain.Nature;

import java.util.HashMap;

/**
 * @author xuming
 */
public class NatureDictionary {
    public static final Logger LOGGER = LoggerFactory.getLogger(NatureDictionary.class);
    private static final int YI =1;
    private static final int FYI = -1;
    private static final HashMap<String,Nature> NATURE_MAP = new HashMap<String, Nature>();
    private static int[][] NATURE_TABLE = null;
    static {
        init();
    }
    private static void init(){

    }

    public static Nature getNature(String nw) {
        return null;
    }
}
