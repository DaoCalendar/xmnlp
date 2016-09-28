package org.xm.ansj.util;

import org.nlpcn.commons.lang.util.ObjConver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xm.xmnlp.util.FileFinder;
import org.xm.xmnlp.util.IOUtil;

import java.io.BufferedReader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author xuming
 */
public class AppStaticValue {
    public static final Logger LOGGER = LoggerFactory.getLogger(AppStaticValue.class);
    public static final String DIC_DEFAULT = "dic";
    public static final String CRF_DEFAULT = "crf";
    public static boolean isNameRecognition = true;
    public static boolean isNumRecognition = true;
    public static boolean isQuantifierRecognition = true;
    public static boolean isRealName = false;
    public static final Map<String, Object> DIC = new HashMap<String, Object>();
    public static final Map<String, Object> CRF = new HashMap<String, Object>();
    public static String ambiguityDictionary = "library/ambiguity.dic";
    public static String synonymsDictionary = "library/synonyms.dic";
    public static boolean isSkipUserDefine = false;

    static {
        ResourceBundle rb = null;
        try {
            rb = ResourceBundle.getBundle("ansj_library");
        } catch (Exception e) {
            try {
                File find = FileFinder.find("ansj_library.properties", 1);
                if (find != null && find.isFile()) {
                    rb = new PropertyResourceBundle(IOUtil.getReader(find.getAbsolutePath(), System.getProperty("file.encoding")));
                    LOGGER.info("load ansj_library {}.", find.getAbsolutePath());
                }
            } catch (Exception e1) {
                LOGGER.warn("not find ansj_library.properties.", e1.getMessage());
            }
        }
        if (rb == null) {
            try {
                rb = ResourceBundle.getBundle("library");
            } catch (Exception e) {
                try {
                    File find = FileFinder.find("ansj_library.properties", 2);
                    if (find != null && find.isFile()) {
                        rb = new PropertyResourceBundle(IOUtil.getReader(find.getAbsolutePath(), System.getProperty("file.encoding")));
                        LOGGER.info("load ansj_library {}.", find.getAbsolutePath());
                    }
                } catch (Exception e1) {
                    LOGGER.warn("not find ansj_library.properties.", e1.getMessage());
                }
            }
        }

        DIC.put(DIC_DEFAULT, "library/default.dic");
        if (rb == null) {
            LOGGER.warn("not find library.properties in classpath use it by default !");
        } else {

            for (String key : rb.keySet()) {

                if (key.equals("dic")) {
                    DIC.put(key, rb.getString(key));
                } else if (key.equals("crf")) {
                    CRF.put(key, rb.getString(key));
                } else if (key.startsWith("dic_")) {
                    if (DIC.containsKey(key)) {
                        LOGGER.warn("{} dic config repeat definition now overwrite it !", key);
                    }
                    DIC.put(key, rb.getString(key));
                } else if (key.startsWith("crf_")) {
                    if (CRF.containsKey(key)) {
                        LOGGER.warn("{} crf config repeat definition now overwrite it !", key);
                    }
                    CRF.put(key, rb.getString(key));
                } else {
                    try {
                        Field field = AppStaticValue.class.getField(key);
                        field.set(null, ObjConver.conversion(rb.getString(key), field.getType()));
                    } catch (NoSuchFieldException e) {
                        LOGGER.error("not find field by {}", key);
                    } catch (SecurityException e) {
                        LOGGER.error("安全异常", e);
                    } catch (IllegalArgumentException e) {
                        LOGGER.error("非法参数", e);
                    } catch (IllegalAccessException e) {
                        LOGGER.error("非法访问", e);
                    }
                }

            }

        }
    }


    public static BufferedReader getNatureMapReader() {
        return DicReaderUtil.getReader("nature/nature.map");
    }

    public static BufferedReader getNatureTableReader() {
        return DicReaderUtil.getReader("nature/nature.table");

    }
}
