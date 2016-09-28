package org.xm.ansj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author xuming
 */
public class DicReaderUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(DicReaderUtil.class);
    public static BufferedReader getReader(String name){
        InputStream is = DicReaderUtil.class.getResourceAsStream("/ansj/"+name);
        try {
            return new BufferedReader(new InputStreamReader(is,"UTF-8"));
        }catch (UnsupportedEncodingException e){
            LOGGER.warn("not support encoding"+e);
        }
        return null;
    }
    public static InputStream getInputStream(String name){
        InputStream is = DicReaderUtil.class.getResourceAsStream("/ansj/"+name);
        return is;
    }
}
